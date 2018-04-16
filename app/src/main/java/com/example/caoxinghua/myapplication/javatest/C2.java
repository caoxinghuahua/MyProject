package com.example.caoxinghua.myapplication.javatest;

public class C2 implements Cloneable{
    public C1 c;
    public int m;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
