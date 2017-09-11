package com.example.caoxinghua.myapplication.okhttp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import java.util.HashSet;
import java.util.Set;


public class MyScrollView extends ScrollView {
    private Set<ImageListener> set=new HashSet<ImageListener>();
    private MyScrollView.ScrollChangeListener listener;
    public MyScrollView(Context context) {
        super(context);

    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void addLisSet(ImageListener listener){
        set.add(listener);
    }
    public Set getSet(){
       return set;
    }
    public void setScrollChangeLitener(MyScrollView.ScrollChangeListener listener ){
        this.listener=listener;
    }
    interface ScrollChangeListener{
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null){
            listener.onScrollChanged(l,t,oldl,oldt);
        }
    }

}
