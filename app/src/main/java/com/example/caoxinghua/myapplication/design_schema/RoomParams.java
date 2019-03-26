package com.example.caoxinghua.myapplication.design_schema;

/**
 * Created by hua on 2019/3/25.
 */

public class RoomParams {
    public String floor,window,door;
    public RoomParams makeFloor(String floor){
        this.floor=floor;
        return this;
    }
    public RoomParams makeWindow(String window){
        this.window=window;
        return this;
    }
    public RoomParams makeDoor(String door){
        this.door=door;
        return this;
    }
}
