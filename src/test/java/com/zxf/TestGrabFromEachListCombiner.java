package com.zxf;

import com.zxf.pac.GrabFromEachListCombiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/30
 */
public class TestGrabFromEachListCombiner {

    /**
     * 没有list，不要空结果
     */
    @Test
    public void testNoneList(){
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        assertFalse(s1.hasNext());
    }

    /**
     * 一个空list，不要空结果
     */
    @Test
    public void testOneListEmpty1(){
        List<Object> listA = new ArrayList<>();
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, false);
        assertFalse(s1.hasNext());
    }

    /**
     * 一个空list，要空结果
     */
    @Test
    public void testOneListEmpty2(){
        List<Object> listA = new ArrayList<>();
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, true);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        while(s1.hasNext()){
            result = s1.next();
        }

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        shouldResult.add(list);

        assertEquals(shouldResult, result);
    }

    /**
     * 一个list，不要空结果
     */
    @Test
    public void testOneListHasOne1(){
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, false);

        List<List<Object>> result = null;
        while(s1.hasNext()){
            result = s1.next();
        }

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add("a");
        shouldResult.add(list);

        assertEquals(shouldResult, result);
    }

    /**
     * 一个list，要空结果
     */
    @Test
    public void testOneListHasOne2(){
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, true);

        List<List<List<Object>>> result = new ArrayList<>();
        while(s1.hasNext()){
            List<List<Object>> r = s1.next();
            result.add(r);
        }

        List<List<Object>> result1 = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        result1.add(list1);

        List<List<Object>> result2 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        list2.add("a");
        result2.add(list2);

        List<List<List<Object>>> shouldResult = new ArrayList<>();
        shouldResult.add(result1);
        shouldResult.add(result2);

        assertEquals(shouldResult, result);
    }

    /**
     * 两个空list，不要空结果
     */
    @Test
    public void testTwoListEmpty1(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, false);
        assertFalse(s1.hasNext());
    }
    /**
     * 两个空list，一个要空结果
     */
    @Test
    public void testTwoListEmpty2(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, false);
        assertFalse(s1.hasNext());
    }
    /**
     * 两个空list，一个要空结果
     */
    @Test
    public void testTwoListEmpty3(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, true);
        assertFalse(s1.hasNext());
    }

    /**
     * 两个空list，要空结果
     */
    @Test
    public void testTwoListEmpty4(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        GrabFromEachListCombiner s1 = new GrabFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, true);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        while(s1.hasNext()){
            result = s1.next();
        }

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);
    }
}
