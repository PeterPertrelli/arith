package com.zxf.combin;

/**
 * 为方便从list中取值，定义该类，number直接对应元素在list中的位置，可用list.get(number)获取元素
 * @author zhuxiangfei
 * @Description:
 * @date 2019/7/11
 */
public class NumberDigit {


    private NumberDigit left;
    private NumberDigit right;

    public NumberDigit getLeft() {
        return left;
    }

    public void setLeft(NumberDigit left) {
        this.left = left;
    }

    public NumberDigit getRight() {
        return right;
    }

    public void setRight(NumberDigit right) {
        this.right = right;
    }

    /**
     * 最大数，比如十进制，最大就是9
     */
    private int maxNumber;
    /**
     * 最小数
     */
    private int minNumber;
    /**
     * 当前数
     */
    private int number;

    public NumberDigit(int maxNumber, int minNumber, int number){
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.number = number;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getNumber() {
        return number;
    }

    public boolean isMax(){
        return number == maxNumber;
    }

    public void inscrease(){
        if(number == maxNumber){
            number = minNumber;
            //本位当前已经是最大值，本位+1归最小，左边一位加1，本位最大值跟上左边一位
            if(left != null){
                left.inscrease();
                maxNumber = left.getNumber()-1;
            }else{
                //已经是最左了
            }
        }else{
            ++number;
        }
    }


}
