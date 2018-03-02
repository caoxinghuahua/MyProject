// Book.aidl.aidl
package com.example.caoxinghua.myapplication.aidl;

// Declare any non-default types here with import statements
//这个文件的作用是引入了一个序列化对象 Book 供其他的AIDL文件使用
//注意：Book.aidl与Book.java的包名应当是一样的
parcelable Book;