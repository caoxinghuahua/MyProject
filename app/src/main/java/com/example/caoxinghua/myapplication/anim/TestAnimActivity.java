package com.example.caoxinghua.myapplication.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.caoxinghua.myapplication.R;

public class TestAnimActivity extends AppCompatActivity {
    private ImageView animIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_anim);
        initView();
        testAnimator();//test anim
    }
    private void initView(){
        animIv= (ImageView) findViewById(R.id.animIv);
        // 帧动画
//        animIv.setImageResource(R.drawable.test);

//        AnimationDrawable animationDrawable= (AnimationDrawable) animIv.getDrawable();
//        animationDrawable.start();
       //      补间动画

//        animIv.setImageResource(R.drawable.ad_default_image_banner);
//        Animation animation= AnimationUtils.loadAnimation(this,R.anim.scale);
//
//        animIv.startAnimation(animation);

    }
    private void testAnimator(){
        //属性动画实现旋转效果
       ObjectAnimator animator=ObjectAnimator.ofFloat(animIv,"rotation",0,360);
       animator.setDuration(1000);
       animator.start();
    }
}
