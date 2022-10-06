package com.example.bookreview.test;

import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("## AfterAll Annotation 호출 ##");
        System.out.println();
    }

    @Before
    public void before(){
        System.out.println("## beforeEach Annotation 호출 ##");
        System.out.println();
    }

    @After
    public void after(){
        System.out.println("## AfterEach Annotation 호출 ##");
        System.out.println();
    }

    @Test
    public void test1(){
        System.out.println("## test1 시작##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case2!!!")
    public void test2(){
        System.out.println("## test2 시작##");
        System.out.println();
    }

    @Test
    @Ignore//테스트를 실행하지 않게 설정하는 어노테이션
    public void test3(){
        System.out.println("## test3 시작##");
        System.out.println();
    }
}
