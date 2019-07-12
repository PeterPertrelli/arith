package com.zxf.combin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2019/7/11
 */
public class LinkedNumber<T> {

    /**
     * 最左元素
     */
    private NumberDigit left;
    /**
     * 最右元素
     */
    private NumberDigit right;

    public NumberDigit getLeft() {
        return left;
    }

    public NumberDigit getRight() {
        return right;
    }


    public void appendRight(NumberDigit digit){
        if(left == null){
            left = digit;
            right = digit;
        }else{
            right.setRight(digit);
            digit.setLeft(right);
            right = digit;
        }

    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        NumberDigit tmp = left;
        do {
            sb.append(tmp.getNumber());
            tmp = tmp.getRight();
        }while(tmp != null);

        return sb.toString();

    }

    public List<Integer> getValue(){

        List<Integer> sb = new ArrayList<>();
        NumberDigit tmp = left;
        do {
            sb.add(tmp.getNumber());
            tmp = tmp.getRight();
        }while(tmp != null);

        return sb;

    }

    public List<T> getList(List<T> list){
        List<T> result = new ArrayList<>();


        NumberDigit tmp = left;
        do {

            result.add(list.get(tmp.getNumber()));


            tmp = tmp.getRight();
        }while(tmp != null);

        return result;
    }


}
