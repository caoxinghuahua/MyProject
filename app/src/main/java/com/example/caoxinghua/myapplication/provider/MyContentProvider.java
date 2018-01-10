package com.example.caoxinghua.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class MyContentProvider extends ContentProvider {
    public  static final String AUTHORITY="com.example.test.hua";
    private static  UriMatcher sUriMatcher=null;
    private static final int MATCH_FIRST=1;
    private static final int MATCH_SECOND=2;
    private static final Uri CONTENT_URI_FIRST=Uri.parse("content://"+AUTHORITY+"/first");
    private static final Uri CONTENT_URI_SECOND=Uri.parse("content://"+AUTHORITY+"/second");
    private DataBaseHelper dataBaseHelper;
    static {
        sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY,"first",MATCH_FIRST);
        sUriMatcher.addURI(AUTHORITY,"second",MATCH_SECOND);
    }
    @Override
    public boolean onCreate() {
        dataBaseHelper=new DataBaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//        String table = null;
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)){
            case MATCH_FIRST:
                builder.setTables(DataBaseHelper.TABLE_FIRST_NAME);
                break;
            case MATCH_SECOND:
                builder.setTables(DataBaseHelper.TABLE_SECOND_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI: " + uri);
        }
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();

        Cursor cursor=builder.query(db,projection,selection,selectionArgs,null,null,null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case MATCH_FIRST:
                long rowId=db.insert(DataBaseHelper.TABLE_FIRST_NAME,null,values);
                if(rowId>=0){
                    Uri rUri= ContentUris.withAppendedId(CONTENT_URI_FIRST,rowId);
                    return rUri;
                }
                break;
            case MATCH_SECOND:
                long rowId2=db.insert(DataBaseHelper.TABLE_SECOND_NAME,null,values);
                if(rowId2>=0){
                    Uri rUri= ContentUris.withAppendedId(CONTENT_URI_SECOND,rowId2);
                    return rUri;
                }
                break;
            default: throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count=0;
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case MATCH_FIRST:
                count=db.delete(DataBaseHelper.TABLE_FIRST_NAME,selection,selectionArgs);
                break;
            case MATCH_SECOND:
                count=db.delete(DataBaseHelper.TABLE_SECOND_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI :" + uri);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        int count=0;
        switch (sUriMatcher.match(uri)){
            case MATCH_FIRST:
                count=db.update(DataBaseHelper.TABLE_FIRST_NAME,values,selection,selectionArgs);
                break;
            case MATCH_SECOND:
                count=db.update(DataBaseHelper.TABLE_SECOND_NAME,values,selection,selectionArgs);
                break;
            default:throw new IllegalArgumentException("unknown Uri："+uri);
        }
        this.getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
