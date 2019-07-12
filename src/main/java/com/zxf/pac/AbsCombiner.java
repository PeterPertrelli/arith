package com.zxf.pac;

import com.zxf.pac.counter.Digit;
import com.zxf.pac.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 组合器抽象类
 * 每个待组合的List，放入时确定取值方式，且不可更改，且不可中途再添加List
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/29
 */
public abstract class AbsCombiner {

    /**
     * 产生组合后，generateLock为true，不可再添加List
     */
    protected boolean generateLock = false;

    /**
     * 若指定了不可产生为空的结果集，但是传入的list是空的，hasNext为false
     */
    protected boolean hasNotIncludeNoneButEmptyList = false;

    /**
     * 需要产生组合的集合数量，用于统一storage, counter, extractor的循环
     */
    protected int bitCount = 0;

    /**
     * 所有待组合的List，将会从里面的List里按指定方式产生所有的组合
     */
    protected List<List<Object>> storage = new ArrayList<>();

    /**
     * 计数器，每个待组合的List占一位，位Digit代表里里面当前取值数
     * 并定义取值方式
     */
    protected List<Digit> counter = new ArrayList<>();

    protected void pressIn(List<Object> list){
        if(generateLock){
            return;
        }
        //使用新的list承载元素，不受原list的元素增删影响
        List bitIn = new ArrayList();
        bitIn.addAll(list);
        storage.add(bitIn);
        bitCount++;
    }


    /**
     * 是否还有未遍历的组合
     *
     * @return
     */
    public boolean hasNext() {
        if(bitCount == 0){
            return false;
        }
        if(!generateLock){
            return !hasNotIncludeNoneButEmptyList;
        }
        if(bitCount > 0){
            boolean hasNext = false;
            for(Digit digit : counter){
                if(!digit.isMax()){
                    hasNext = true;
                }
            }
            return hasNext;
        }else{
            //没有元素list，肯定没有
            return false;
        }
    }

    /**
     * 获取下一个组合
     *
     * @return
     */
    public List<List<Object>> next() {

        List<List<Object>> result = new ArrayList<>();
        //若之前全都到达了最大位，也就遍历完全了，返回空list
        if(!hasNext()){
            return result;
        }

        //说明是第一次取，直接取当前组合集合
        if(!generateLock){
            for(int i = bitCount -1; i >= 0; i--){
                Digit digit = counter.get(i);
                result.add(digit.get(storage.get(i)));
            }
            generateLock = true;
            Collections.reverse(result);
            return result;
        }
        //若还有没到达最大位的，那么仍然可以increase
        if(bitCount > 0){
            //从右往左循环，如果上一个已经到达了最大位，那么接下来increase之后，当前位回归最小值，左边一位increase
            boolean isCarryBit = false;
            for(int i = bitCount-1; i >= 0; i--){
                Digit digit = counter.get(i);

                //若当前数字已经是最大值，需要向左进位
                //从右开始第一位一定要加，若是进位来的，也加
                if(i == bitCount-1){
                    isCarryBit = digit.isMax();
                    digit.increase();
                }else{
                    if(isCarryBit){
                        digit.increase();
                        isCarryBit = false;
                    }
                }
                result.add(digit.get(storage.get(i)));
            }
        }

        Collections.reverse(result);
        return result;
    }

    /**
     * 在一个list里获取下一个组合
     * @return
     */
    public List<Object> nextInOneList(){
        List<List<Object>> list = next();
        List<Object> result = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(list)){
            for(List<Object> items : list){
                if(CollectionUtil.isNotEmpty(items)){
                    result.addAll(items);
                }
            }
        }
        return result;
    }
}
