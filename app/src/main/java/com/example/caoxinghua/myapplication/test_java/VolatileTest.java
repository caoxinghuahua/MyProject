package com.example.caoxinghua.myapplication.test_java;

public class VolatileTest{
    private  int count=100;

    public void add(){
        count+=100;
    }
    public void addB(){
        count+=200;
    }
    public void print(){
        System.out.println("count:"+count);
    }
}
