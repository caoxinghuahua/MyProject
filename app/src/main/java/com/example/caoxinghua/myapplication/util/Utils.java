package com.example.caoxinghua.myapplication.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by caoxinghua on 2017/2/22.
 */

public class Utils {
    public static String formatTime(long time){
        time/=1000;
        int h= (int) (time/3600);
        int m=(int)time%3600/60;
        int s=(int)time%60;
        StringBuffer buffer=new StringBuffer();
        if(h<10){
            buffer.append("0"+h+":");
        }else {
            buffer.append(h+":");
        }
        if(m<10){
            buffer.append("0"+m+":");
        }else {
            buffer.append(m+":");
        }
        if(s<10){
            buffer.append("0"+s);
        }else {
            buffer.append(s);
        }
        return buffer.toString();
    }
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}
