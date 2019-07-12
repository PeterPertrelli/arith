package com.zxf;

import static org.junit.Assert.assertEquals;
import com.zxf.pac.GrabFromEachListCombiner;
import com.zxf.pac.SelectOneFromEachListCombiner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/29
 */
public class Test {
    public static void main(String[] args){
        testSelectOneFromEachListCombiner();
//        testGrabFromEachListCombiner();
    }

    private static void testSelectOneFromEachListCombiner() {
        List<Object> listA = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, true);

        System.out.println(s1.hasNext());
        List<List<Object>> result = null;
        while(s1.hasNext()){
            result = s1.next();
            System.out.println(result);
        }

    }


    private static void testGrabFromEachListCombiner() {
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        listA.add("a");

        List<Object> listB = new ArrayList<>();
        listB.add("b");
        listB.add("b");

        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, false);
//        s1.pressIn(listB, false);

        System.out.println(s1.hasNext());

        while(s1.hasNext()){
            System.out.println(s1.next());
        }
    }
}
