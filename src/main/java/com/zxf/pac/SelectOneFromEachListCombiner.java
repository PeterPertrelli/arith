package com.zxf.pac;

import com.zxf.pac.counter.Digit;
import com.zxf.pac.counter.NaturalNumberSelectDigit;
import com.zxf.pac.utils.CollectionUtil;

import java.util.List;

/**
 * 适用于有多个list，每个list至多选择一个元素产生组合
 * 例：
 * listA:米饭，馒头
 * includeNone
 * true:包含None的时候，取值情况有{},{米饭},{馒头}
 * false:不包含None的时候，取值情况有{米饭},{馒头}
 * listB:苹果，香蕉
 * includeNone
 * true:包含None的时候，取值情况有{},{苹果},{香蕉}
 * false:不包含None的时候，取值情况有{苹果},{香蕉}
 *
 * 该类就是将listA, listB的取值情况相乘组合产生笛卡尔集
 * 只不过并不是一次生成，而是由next()遍历每一种组合
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/29
 */
public class SelectOneFromEachListCombiner extends AbsCombiner {

    /**
     * 压入元素
     * @param list
     * @param includeNone true:包含取值个数为0的时候,false:每次必取1个，若压入的list为空，也会返回0个
     */
    public void pressIn(List<Object> list, boolean includeNone){
        if(super.generateLock){
            return;
        }
        NaturalNumberSelectDigit digit = new NaturalNumberSelectDigit(list.size(), includeNone);
        super.pressIn(list);
        super.counter.add(digit);
        super.hasNotIncludeNoneButEmptyList = includeNone ? super.hasNotIncludeNoneButEmptyList : CollectionUtil.isEmpty(list);
    }

}
