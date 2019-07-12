package com.zxf.pac.counter;

import java.util.ArrayList;
import java.util.List;

/**
 * 取n个元素，list中的元素同类型，取哪几个都一样
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/29
 */
public class NaturalNumberGrabDigit implements Digit {

    /**
     * 当前数字是多少
     */
    private int number;

    /**
     * 最大数字
     * 取多少个元素，为list.size()，取所有元素
     */
    private int maxNumber;

    /**
     * 最小数字
     * 取多少个元素时，若包含none，为0，此时不取任何元素，若不包含none，为1，即取1个，
     */
    private int minNumber;

    /**
     * @param size         传入lsit.size
     * @param includeNone
     */
    public NaturalNumberGrabDigit(int size, boolean includeNone){
        maxNumber = size;
        minNumber = includeNone ? 0 : 1;
        //若list为空，minNumber设为0，因为无论如何都获取不到元素
        minNumber = maxNumber == 0 ? 0 : minNumber;
        number = minNumber;
    }

    /**
     * 自增
     */
    @Override
    public void increase() {
        if(isMax()){
            //已经到达了最大位，从头循环
            number = minNumber;
        }else {
            number++;
        }
    }

    /**
     * 判断当前是否到达了最大值，用于判断是否进位
     * @return
     */
    @Override
    public boolean isMax() {
        return number == maxNumber;
    }

    /**
     * 根据digit的取值规则获取组合
     * @param list
     * @return
     */
    @Override
    public List<Object> get(List<Object> list) {
        return list.subList(0, number);
    }

    /**
     * 即将进位
     *
     * @return
     */
    @Override
    public boolean willIncrease() {
        return number == (maxNumber-1);
    }
}
