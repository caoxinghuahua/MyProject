package com.example.caoxinghua.myapplication.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;


public class MyView extends AppCompatTextView {
    private final String TAG=getClass().getSimpleName();
    private float width_percent;
    private float height_percent;
    private int width;
    private int height;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.MyView);
        width_percent=array.getFloat(R.styleable.MyView_layout_height_percent,0);
        height_percent=array.getFloat(R.styleable.MyView_layout_height_percent,0);
        array.recycle();
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int)(width*width_percent),(int)(height*height_percent));
        Log.i(TAG,"onMeasure()");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG,"onLayout()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG,"onDraw()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
