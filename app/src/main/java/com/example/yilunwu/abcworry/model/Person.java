package com.example.yilunwu.abcworry.model;

import java.io.Serializable;

/**
 * Created by yilunwu on 16/12/9.
 */
public class Person implements Serializable {

    private int age;
    private String name;
    private String address;
    private String id;


    public Person(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "[name="+name+"age="+age+"address="+address+"]";
    }
}
