package com.zxf.pac;

import com.zxf.pac.counter.NaturalNumberGrabDigit;
import com.zxf.pac.utils.CollectionUtil;

import java.util.List;

/**
 * 适用于有多个集合，集合内的元素均一样，只要数量一致，抓哪几个都一样
 * 一个集合listA{a,a,a,a,a}
 * includeNone:true
 * 抓取的结果集{},{a},{a,a},{a,a,a},{a,a,a,a},{a,a,a,a,a}
 * includeNone:false
 * 抓取的结果集{a},{a,a},{a,a,a},{a,a,a,a},{a,a,a,a,a}
 *
 * 压入多个list后，就是上面每个结果集的笛卡尔乘积
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/30
 */
public class GrabFromEachListCombiner extends AbsCombiner {

    /**
     * 压入元素
     * @param list
     * @param includeNone true:包含取值个数为0的时候,false:每次必取1个，若压入的list为空，也会返回0个
     */
    public void pressIn(List<Object> list, boolean includeNone){
        if(super.generateLock){
            return;
        }
        NaturalNumberGrabDigit digit = new NaturalNumberGrabDigit(list.size(), includeNone);
        super.pressIn(list);
        super.counter.add(digit);
        super.hasNotIncludeNoneButEmptyList = includeNone ? super.hasNotIncludeNoneButEmptyList : CollectionUtil.isEmpty(list);
    }






}
