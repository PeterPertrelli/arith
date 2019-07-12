package com.zxf.pac.counter;

import java.util.ArrayList;
import java.util.List;

/**
 * 按位获取元素，取第n个元素
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/29
 */
public class NaturalNumberSelectDigit implements Digit {

    /**
     * 当前数字是多少
     */
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    /**
     * 最大数字
     * 按位获取时，为list.size()-1，取到最后一个元素
     */
    private int maxNumber;

    /**
     * 最小数字
     * 按位获取，若不包含none，为0，即取第0个，若包含none，为-1，此时不取任何元素
     */
    private int minNumber;

    /**
     * @param size         传入lsit.size
     * @param includeNone
     */
    public NaturalNumberSelectDigit(int size, boolean includeNone){
        maxNumber = size-1;
        minNumber = includeNone ? -1 : 0;
        number = minNumber;
    }

    /**
     * @param max   最大值，可以显示的最大值，对应list.get(i)里的i
     * @param min
     */
    public NaturalNumberSelectDigit(int max, int min){
        maxNumber = max;
        minNumber = min;
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

    @Override
    public boolean willIncrease(){
        return number == (maxNumber-1);
    }

    /**
     * 根据digit的取值规则获取组合
     * @param list
     * @return
     */
    @Override
    public List<Object> get(List<Object> list) {
        List<Object> result = new ArrayList<>();
        if(number != -1){
            result.add(list.get(number));
        }
        return result;
    }
}
