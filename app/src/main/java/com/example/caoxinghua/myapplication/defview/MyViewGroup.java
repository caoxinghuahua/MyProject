package com.example.caoxinghua.myapplication.defview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View view=getChildAt(i);
            Log.i("xxx0","w:"+view.getWidth()+"--"+view.getMeasuredWidth());
//            view.layout(0,0,200,200);
        }

    }

}
