package com.example.caoxinghua.myapplication.ndk;

public class JNIUtil {
    public  native String getWorld();
    static {
        System.loadLibrary("JNISample");
    }
    public native String test();
}
