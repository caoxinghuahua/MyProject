package com.example.caoxinghua.myapplication.webp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.caoxinghua.myapplication.R;
import com.rincliu.library.common.persistence.image.webp.WebPFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.everything.webp.WebPDecoder;

public class WebpActivity extends AppCompatActivity {
    private ImageView  imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webp);
        imageView= (ImageView) findViewById(R.id.imageView);
        InputStream inputStream=null;
        try {
            inputStream=getAssets().open("image.webp");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Bitmap bitmap= WebPDecoder.getInstance().decodeWebP(steamToBytes(inputStream));
        Bitmap bitmap= WebPFactory.nativeDecodeByteArray(steamToBytes(inputStream),null);
        imageView.setImageBitmap(bitmap);

    }
    private byte[] steamToBytes(InputStream is){
        ByteArrayOutputStream baos=new ByteArrayOutputStream(1024);
        byte[] buffer=new byte[1024];
        int len=0;
        try {
            while((len=is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
