package com.example.caoxinghua.myapplication.okhttp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Set;


public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }
    private Set<ImageListener> set=new HashSet<ImageListener>();
    private ScrollChangeListener listener;

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void addLisSet(ImageListener listener){
        set.add(listener);
    }
    public Set getSet(){
        return set;
    }
    public void setScrollChangeLitener(ScrollChangeListener listener ){
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
