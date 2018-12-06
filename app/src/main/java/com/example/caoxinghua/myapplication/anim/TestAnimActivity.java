package com.example.caoxinghua.myapplication.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.caoxinghua.myapplication.R;
import com.nineoldandroids.animation.PropertyValuesHolder;

public class TestAnimActivity extends AppCompatActivity {
    private ImageView animIv;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_anim);
        initView();
        testAnimator();//test anim
    }
    private void initView(){
        animIv= (ImageView) findViewById(R.id.animIv);
        lottieAnimationView= (LottieAnimationView) findViewById(R.id.lottieView);
        // 帧动画
//        animIv.setImageResource(R.drawable.test);

//        AnimationDrawable animationDrawable= (AnimationDrawable) animIv.getDrawable();
//        animationDrawable.start();
       //      补间动画

//        animIv.setImageResource(R.drawable.ad_default_image_banner);
//        Animation animation= AnimationUtils.loadAnimation(this,R.anim.scale);
//
//        animIv.startAnimation(animation);
        testValueAnimator();

    }
    private void testAnimator(){
        //属性动画实现旋转效果
       ObjectAnimator animator=ObjectAnimator.ofFloat(animIv,"rotation",0,360);
       animator.setDuration(1000);
       animator.start();
//        lottieAnimationView.setAnimation("Logo/LogoSmall.json");
        lottieAnimationView.loop(true);
        //监听动画状态
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("xxx","anim start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("xxx","anim end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("xxx","anim cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("xxx","anim repeat");
            }
        });
        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                Log.i("xxx","anim update");
            }
        });
        LottieComposition.Factory.fromAssetFileName(this, "AndroidWave.json", new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                lottieAnimationView.setComposition(composition);
                lottieAnimationView.playAnimation();
            }
        });


//        lottieAnimationView.playAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(lottieAnimationView!=null){
            lottieAnimationView.cancelAnimation();
        }
    }
    private void testValueAnimator(){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0f,1f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("update", ((Float) animation.getAnimatedValue()).toString());
            }
        });
        valueAnimator.setInterpolator(new CycleInterpolator(3));
        valueAnimator.start();

        ObjectAnimator animatorX=ObjectAnimator.ofFloat(animIv,"x",200);
        animatorX.setDuration(1000);
        ObjectAnimator animatorY=ObjectAnimator.ofFloat(animIv,"y",100);
        animatorY.setDuration(1000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(animatorX,animatorY);
        animatorSet.start();

//        PropertyValuesHolder pvX=PropertyValuesHolder.ofFloat("x",300);
//        PropertyValuesHolder pvY=PropertyValuesHolder.ofFloat("y",300);
//        PropertyValuesHolder[] propertyValuesHolder=new PropertyValuesHolder[]{pvX,pvY};
//        ObjectAnimator.ofPropertyValuesHolder(animIv,propertyValuesHolder);

        //ViewPropertyAnimator
//        animIv.animate().x(400).y(500).start();
    }
}
