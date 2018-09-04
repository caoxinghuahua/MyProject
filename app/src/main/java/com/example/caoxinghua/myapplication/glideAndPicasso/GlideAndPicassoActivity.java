package com.example.caoxinghua.myapplication.glideAndPicasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.Target;
import com.example.caoxinghua.myapplication.R;
import com.example.caoxinghua.myapplication.video.VideoFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Picasso;

public class GlideAndPicassoActivity extends AppCompatActivity {
    private ImageView glideIv;
    private ImageView picassoIv;
    private SimpleDraweeView frescoView;
    private String imgUrlHttps="https://gfs9.gomein.net.cn/T1cdbvBvKv1RCvBVdK.jpg";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fresco
        Fresco.initialize(this);
        setContentView(R.layout.activity_glide_and_picasso);
        initView();
        initData();
    }
    private void initView(){
        glideIv=(ImageView) findViewById(R.id.glide_iv);
        picassoIv=(ImageView) findViewById(R.id.picasso_iv);
        frescoView= (SimpleDraweeView) findViewById(R.id.frescoView);
//        Fragment fragment=new Fragment();
//        FragmentManager manager=getSupportFragmentM anager();
//        FragmentTransaction transaction=manager.beginTransaction();
//        transaction.add(R.id.testView,fragment);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData(){
        testGlide();
//        testPicasso();
//        testFresco();
    }
    private void testGlide(){
        //加载https
        Glide.with(this).load(imgUrlHttps).placeholder(R.drawable.btn_keyboard_key).diskCacheStrategy(DiskCacheStrategy.NONE).override(300,600).into(glideIv);
    }
    private void testPicasso(){
        Picasso.with(this).load(imgUrlHttps).placeholder(R.drawable.btn_keyboard_key).resize(300,600).into(picassoIv);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void testFresco(){
        Uri uri=Uri.parse("http://img.my.csdn.net/uploads/201508/05/1438760726_5120.jpg");
        ControllerListener listener=new BaseControllerListener(){
            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                Log.i("xxx","onfinal");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.i("xxx","onfail");
            }
        };
        //设置渐进式加载
        ImageRequest request=ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(listener)
                .setOldController(frescoView.getController())//节省不不必要的内存分配
                .build();

        GenericDraweeHierarchy genericDraweeHierarchy=frescoView.getHierarchy();
        //修改占位图
        genericDraweeHierarchy.setPlaceholderImage(R.drawable.ad_default_image_banner);
        //修改圆角
        RoundingParams params=genericDraweeHierarchy.getRoundingParams();
        params.setCornersRadius(10);
        genericDraweeHierarchy.setRoundingParams(params);
        //设置加载进度条
        Drawable drawable=getDrawable(R.drawable.amap_car);
        genericDraweeHierarchy.setProgressBarImage(drawable);
        //设置按压图片
//        GenericDraweeHierarchyBuilder builder=new GenericDraweeHierarchyBuilder(getResources());
//        builder.setPressedStateOverlay(drawable);
        frescoView.setHierarchy(genericDraweeHierarchy);
        frescoView.setController(controller);


    }

}
