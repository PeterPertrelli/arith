package com.zxf.combin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 二进制取数的升级版
 * 比如一个list，要取固定个数的组合
 * 转换成二进制取值，就是二进制数中包含固定个数的1，再按位去取
 *
 * 其实就是构建一个数
 * 比如取三个元素，那么就是一个三位数
 * ABC
 * 满足   A>B>C
 * A<size
 * B为C的进制，A为B的进制
 * 最大数是(size-1)(size-2)(size-3)
 *
 *  876543210
 *  010010010
 *  741
 *  计数741，实际去取元素，去取7,4,1位置的元素
 * @author zhuxiangfei
 * @Description:
 * @date 2019/7/11
 */
public class SelectSomeFromListCombiner<T> {

    private List<T> list = new ArrayList<>();

    /**
     * 元素从0开始，代表数字从左往右
     * 01234   list中的位置
     * 43210   代表的数字
     */
    private LinkedNumber digitList = new LinkedNumber();

    private boolean isFirstUsed = false;

    public SelectSomeFromListCombiner(List<T> list, int selectNum){
        this.list = list;

        int i = selectNum;
        do{
            if(null == digitList.getLeft()){
                NumberDigit numberDigit = new NumberDigit(list.size()-1,i-1,i-1);
                digitList.appendRight(numberDigit);
            }else{
                //本位要生成的时候，取当前最右，为本位的左边
                NumberDigit left = digitList.getRight();
                NumberDigit numberDigit = new NumberDigit(left.getMinNumber()-1,left.getMinNumber()-1,left.getMinNumber()-1);
                digitList.appendRight(numberDigit);
            }
            --i;
        }while(i >0);

    }

    public boolean hasNext(){

        NumberDigit tmp = digitList.getLeft();
        boolean has = false;
        do{
            if(!tmp.isMax()){
                has = true;
                break;
            }
            tmp = tmp.getRight();

        }while(tmp != null);

        return has;
    }

    public List<T> getNext(){

        if(isFirstUsed){
            //第一个已经使用了，就开始累加取值

            NumberDigit tmp = digitList.getRight();
            tmp.inscrease();

        }else{
            //第一个还没使用，就返回第一个就行
            isFirstUsed = true;
        }


//        System.out.println(digitList.toString());

        return digitList.getList(list);
    }

    public List<Integer> getCounterValue(){
        return this.digitList.getValue();
    }



    public static void main(String[] args){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
//        list.add("e");
//        list.add("f");


        SelectSomeFromListCombiner<String> s = new SelectSomeFromListCombiner<>(list, 3);

        int i = 0;

        while(s.hasNext()){
//            s.getNext();
            System.out.println( s.getNext());
            ++i;
        }

        System.out.println("共  "+i);

    }



}
