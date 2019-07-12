package com.zxf.pac.counter;

import java.util.List;

/**
 * 计数器的位，以及该位的取值方式
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/29
 */
public interface Digit {

    /**
     * 自增
     */
    public void increase();

    /**
     * 判断当前是否到达了最大值，用于判断是否进位
     * @return
     */
    public boolean isMax();

    /**
     * 根据digit的取值规则获取组合
     * @param list
     * @return
     */
    public List<Object> get(List<Object> list);


    /**
     * 即将进位
     * @return
     */
    public boolean willIncrease();
}
