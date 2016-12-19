package com.example.yilunwu.abcworry;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testMethod(){
        int a=1;
        int b=5;

        System.out.println(a + b);
    }

    public void getMis(){
        int a=10;
        int b=5;

        System.out.println(a-b);
    }

    public void testgetMis(){
        int a=10;
        int b=5;

        System.out.println(a-b);
    }
}