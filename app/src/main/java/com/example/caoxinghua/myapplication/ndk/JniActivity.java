package com.example.caoxinghua.myapplication.ndk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.caoxinghua.myapplication.R;

public class JniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        JNIUtil jniUtil=new JNIUtil();
        Log.i("xxx","jniStr:"+jniUtil.getWorld());
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;


        Bitmap bitmap=BitmapFactory.decodeFile("/sdcard/test.jpg",options);
        if(bitmap==null){
            Log.i("xxx","bitmap is null");
        }
        int outWidth=options.outWidth;
        int outHeight=options.outHeight;

        Log.i("xxx","w:"+outWidth+"\nh:"+outHeight);

        options.inSampleSize=2;
        options.inJustDecodeBounds=false;
        Bitmap bitmap1=BitmapFactory.decodeFile("/sdcard/test.jpg",options);
        Log.i("xxx","new w:"+options.outWidth+"\nnew h:"+options.outHeight);
    }
}
