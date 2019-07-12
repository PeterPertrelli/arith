package com.zxf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.zxf.pac.SelectOneFromEachListCombiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2019/1/30
 */
public class TestSelectOneFromEachListCombiner {
    /**
     * 没有list
     */
    @Test
    public void testNoneList(){
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        assertFalse(s1.hasNext());
    }

    /**
     * 一个list
     * 一个空list，不要空结果
     */
    @Test
    public void testOneListEmpty1(){
        List<Object> listA = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        assertFalse(s1.hasNext());
    }

    /**
     * 一个list
     * 一个空list，要空结果
     */
    @Test
    public void testOneListEmpty2(){
        List<Object> listA = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
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
     * 一个list
     * 一个list，不要空结果
     */
    @Test
    public void testOneListHasOne1(){
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
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
     * 一个list
     * 一个list，要空结果
     */
    @Test
    public void testOneListHasOne2(){
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
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
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 空     空
     * false  false
     */
    @Test
    public void testTwoListEmpty1(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, false);
        assertFalse(s1.hasNext());
    }
    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 空     空
     * true   false
     */
    @Test
    public void testTwoListEmpty2(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, false);
        assertFalse(s1.hasNext());
    }
    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 空     空
     * false  true
     */
    @Test
    public void testTwoListEmpty3(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, true);
        assertFalse(s1.hasNext());
    }

    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 空     空
     * true   true
     */
    @Test
    public void testTwoListEmpty4(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
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

    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况，一个空一个非空的情况与一个非空一个空对称，所以减少4种
     * 非空     空
     * true    true
     */
    @Test
    public void testTwoListOneEmpty1(){
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        List<Object> listB = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, true);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        result = s1.next();

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);

        assertTrue(s1.hasNext());

        result = s1.next();

        List<List<Object>> shouldResult2 = new ArrayList<>();
        List<Object> list11 = new ArrayList<>();
        list11.add("a");
        List<Object> list22 = new ArrayList<>();
        shouldResult2.add(list11);
        shouldResult2.add(list22);

        assertEquals(shouldResult2, result);

        assertFalse(s1.hasNext());
    }


    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     空
     * false   true
     */
    @Test
    public void testTwoListOneEmpty2(){
        List<Object> listA = new ArrayList<>();
        listA.add("a");
        List<Object> listB = new ArrayList<>();
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, true);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        result = s1.next();


        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        List<Object> list2 = new ArrayList<>();
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);

        assertFalse(s1.hasNext());
    }


    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     空
     * true     false
     */
    @Test
    public void testTwoListOneEmpty3(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        listA.add("a");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, false);

        assertFalse(s1.hasNext());
    }

    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     空
     * false    false
     */
    @Test
    public void testTwoListOneEmpty4(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        listA.add("a");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, false);

        assertFalse(s1.hasNext());
    }

    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     非空
     * false    false
     */
    @Test
    public void testTwoList1(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        listA.add("a");
        listB.add("1");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, false);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        result = s1.next();

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        List<Object> list2 = new ArrayList<>();
        list2.add("1");
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);

        assertFalse(s1.hasNext());
    }


    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     非空
     * true    false
     */
    @Test
    public void testTwoList2(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        listA.add("a");
        listB.add("1");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, false);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        result = s1.next();

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        list2.add("1");
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);

        assertTrue(s1.hasNext());
        result = s1.next();

        List<List<Object>> shouldResult2 = new ArrayList<>();
        List<Object> list12 = new ArrayList<>();
        List<Object> list22 = new ArrayList<>();
        list12.add("a");
        list22.add("1");
        shouldResult2.add(list12);
        shouldResult2.add(list22);

        assertEquals(shouldResult2, result);

        assertFalse(s1.hasNext());
    }


    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     非空
     * false    true
     */
    @Test
    public void testTwoList3(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        listA.add("a");
        listB.add("1");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, false);
        s1.pressIn(listB, true);

        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        result = s1.next();

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        list1.add("a");
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);

        assertTrue(s1.hasNext());
        result = s1.next();

        List<List<Object>> shouldResult2 = new ArrayList<>();
        List<Object> list12 = new ArrayList<>();
        List<Object> list22 = new ArrayList<>();
        list12.add("a");
        list22.add("1");
        shouldResult2.add(list12);
        shouldResult2.add(list22);

        assertEquals(shouldResult2, result);

        assertFalse(s1.hasNext());
    }
    /**
     * 两个list
     * 每个list有四种情况，两两相乘16种情况
     * 非空     非空
     * true    true
     */
    @Test
    public void testTwoList4(){
        List<Object> listA = new ArrayList<>();
        List<Object> listB = new ArrayList<>();
        listA.add("a");
        listB.add("1");
        SelectOneFromEachListCombiner s1 = new SelectOneFromEachListCombiner();
        s1.pressIn(listA, true);
        s1.pressIn(listB, true);

        // 空 空
        assertTrue(s1.hasNext());

        List<List<Object>> result = null;
        result = s1.next();

        List<List<Object>> shouldResult = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        shouldResult.add(list1);
        shouldResult.add(list2);

        assertEquals(shouldResult, result);

        // 空 1
        assertTrue(s1.hasNext());
        result = s1.next();

        List<List<Object>> shouldResult2 = new ArrayList<>();
        List<Object> list12 = new ArrayList<>();
        List<Object> list22 = new ArrayList<>();
        list22.add("1");
        shouldResult2.add(list12);
        shouldResult2.add(list22);

        assertEquals(shouldResult2, result);
        // a 空
        assertTrue(s1.hasNext());
        result = s1.next();

        List<List<Object>> shouldResult3 = new ArrayList<>();
        List<Object> list13 = new ArrayList<>();
        List<Object> list23 = new ArrayList<>();
        list13.add("a");
        shouldResult3.add(list13);
        shouldResult3.add(list23);

        assertEquals(shouldResult3, result);

        // a 1
        assertTrue(s1.hasNext());
        result = s1.next();

        List<List<Object>> shouldResult4 = new ArrayList<>();
        List<Object> list14 = new ArrayList<>();
        List<Object> list24 = new ArrayList<>();
        list14.add("a");
        list24.add("1");
        shouldResult4.add(list14);
        shouldResult4.add(list24);

        assertEquals(shouldResult4, result);

        assertFalse(s1.hasNext());
    }
}
