package com.example.caoxinghua.myapplication.javatest;

import junit.framework.Assert;

public class Main {
    public static void main(String[] args){
        C2 c2=new C2();
        C1 c1=new C1(10,"tim");
        c2.c=c1;
        c2.m=3;
        C2 c22 = null;
        try {
            c22= (C2) c2.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.print("t0:"+(c2==c22));
        System.out.print("t1:"+(c2.c.getClass()==c22.c.getClass()));
        System.out.print("t2:"+(c2.c.getAge()==c22.c.getAge()));
        System.out.print("t3:"+(c2.c.getName()==c22.c.getName()));
        System.out.print("t4:"+(c2.m==c22.m));
        c2.c.setName("cc");
        System.out.print("t5:"+c2.c.getName()+"//"+c22.c.getName());
        System.out.print("t6:"+c2.m+"//"+c22.m);

    }
}
