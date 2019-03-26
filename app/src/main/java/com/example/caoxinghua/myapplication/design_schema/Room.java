package com.example.caoxinghua.myapplication.design_schema;

import android.support.v7.app.AlertDialog;

/**
 * Created by hua on 2019/3/25.
 * 建造者模式
 */

public class Room {
    private String floor,window,door;
    public void apply(RoomParams params){
        floor=params.floor;
        window=params.window;
        door=params.door;

    }

    /**
     * Created by hua on 2019/3/25.
     */

    public static class Builder {
        private RoomParams roomParams=new RoomParams();
        public Builder makeFloor(String floor){
            roomParams.floor=floor;
            return this;
        }
        public Builder makeWindow(String window){
            roomParams.window=window;
            return this;
        }
        public Builder makeDoor(String door){
            roomParams.door=door;
            return this;
        }
        public Room build(){
            Room room=new Room();
            room.apply(roomParams);
            return room;
        }
    }
}
