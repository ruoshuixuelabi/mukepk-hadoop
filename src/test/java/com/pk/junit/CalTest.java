package com.pk.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * MapReduce时要讲解模板模式
 */
public class CalTest {
    Cal cal;

    /**
     * 每个单元测试方法执行之前
     * 准备工作：初始化....
     */
    @Before
    public void setUp(){
        cal = new Cal();
        System.out.println("setUp");
    }

    /**
     * 每个单元测试方法执行之后
     * 资源释放操作： 关闭
     */
    @After
    public void tearDown(){
        if(null != cal) cal = null;
        System.out.println("tearDown");
    }


    @Test
    public void testAdd() {
        System.out.println("testAdd");
        int result = cal.add(2, 5);
        assertEquals(7, result);
    }

    @Test
    public void testDivide() {
        System.out.println("testDivide");
        int result = cal.divide(10, 5);
        assertEquals(2, result);
    }


    @Test(expected = ArithmeticException.class)
    public void testDivide02() {
        int result = cal.divide(10, 0);
        assertEquals(2, result);
    }

}
