package com.example.caoxinghua.myapplication.test_java;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        List list1=new ArrayList();
        List list2=new ArrayList(){};
        List list3=new ArrayList(){{}};
        List list4=new ArrayList(){{}{}};
        System.out.println(list1.getClass()==list2.getClass());
        System.out.println(list1.getClass()==list3.getClass());
        System.out.println(list1.getClass()==list4.getClass());
        System.out.println(list2.getClass()==list3.getClass());
        System.out.println(list2.getClass()==list4.getClass());
        System.out.println(list3.getClass()==list4.getClass());
        System.out.println(list1.getClass().getName());
        System.out.println(list2.getClass().getName());
        System.out.println(list3.getClass().getName());
        System.out.println(list4.getClass().getName());

    }
}
