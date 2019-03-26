package com.example.caoxinghua.myapplication.design_schema;

/**
 * Created by hua on 2019/3/25.
 */

public class MyTest {
    public static void main(String []args){
        Room.Builder builder=new Room.Builder();
        builder.makeDoor("door")
                .makeFloor("floor")
                .makeWindow("window")
                .build();
        Integer.parseInt("sss");
    }

}
