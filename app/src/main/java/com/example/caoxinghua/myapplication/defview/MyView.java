package com.example.caoxinghua.myapplication.defview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class MyView extends View {
    private Paint paint;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        measure(300,200);
        int w=getWidth();
        int h=getHeight();

        Log.i("xxx1","w:"+w+"--"+getMeasuredWidth());
        int r=Math.min(w-getPaddingLeft()-getPaddingRight(),h-getPaddingTop()-getPaddingBottom())/2;
        canvas.drawCircle(w/2,h/2,r,paint);
        Log.i("xxx2","w:"+w+"--"+getMeasuredWidth());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("xxx1","view onTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("xxx1","view onTouchEvent");
        return super.onTouchEvent(event);
    }
}
