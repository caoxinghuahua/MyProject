package com.example.caoxinghua.myapplication.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="test.db";
    private static final int DB_VERSION=1;
    public static final String TABLE_FIRST_NAME="first";
    public static final String TABLE_SECOND_NAME="second";
    private static final String CREATE_TABLE_FIRST="CREATE TABLE "+TABLE_FIRST_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(50),age INTEGER,address varchar(100))";
    private static final String CREATE_TABLE_SECOND="CREATE TABLE IF NOT EXISTS "+TABLE_SECOND_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(50),age INTEGER,address varchar(100))";

    public DataBaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("xxx","create db");
        db.execSQL(CREATE_TABLE_FIRST);
        db.execSQL(CREATE_TABLE_SECOND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("xxx","upgrade db");
        db.execSQL("drop table "+TABLE_FIRST_NAME);
        db.execSQL("drop table "+TABLE_SECOND_NAME);
        onCreate(db);
    }
}
