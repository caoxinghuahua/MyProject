package com.example.caoxinghua.myapplication.javatest;

/**
 * Created by caoxinghua on 2017/2/23.
 */

public class B extends A {
    public B() {
        System.out.print("B");
    }

    {
        System.out.print("{B}");
    }

    static {
        System.out.print("static B");
    }

}
