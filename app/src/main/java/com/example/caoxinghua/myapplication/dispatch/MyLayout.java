package com.example.caoxinghua.myapplication.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author caoxinghua on 2018/11/26
 * @email caoxinghua@gomeplus.com
 */
public class MyLayout extends LinearLayout {
    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("xxx"," ViewGroup ontouch event");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("xxx"," ViewGroup dispatchTouchEvent");

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("xxx"," ViewGroup onInterceptTouchEvent");
        //getParent().requestDisallowInterceptTouchEvent(true);//子view中调用该方法，则父view的onInterceptTouchEvent不会调用
        return super.onInterceptTouchEvent(ev);

    }
}
