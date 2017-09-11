package com.example.caoxinghua.myapplication.glideAndPicasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.caoxinghua.myapplication.R;
import com.squareup.picasso.Picasso;

public class GlideAndPicassoActivity extends AppCompatActivity {
    private ImageView glideIv;
    private ImageView picassoIv;
    private String imgUrlHttps="https://gfs9.gomein.net.cn/T1cdbvBvKv1RCvBVdK.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_and_picasso);
        initView();
        initData();
    }
    private void initView(){
        glideIv=(ImageView) findViewById(R.id.glide_iv);
        picassoIv=(ImageView) findViewById(R.id.picasso_iv);
    }
    private void initData(){
        //加载https
        Glide.with(this).load(imgUrlHttps).placeholder(R.drawable.btn_keyboard_key).diskCacheStrategy(DiskCacheStrategy.NONE).override(300,600).into(glideIv);
        Picasso.with(this).load(imgUrlHttps).placeholder(R.drawable.btn_keyboard_key).resize(300,600).into(picassoIv);

    }

}
