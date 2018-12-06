package com.example.caoxinghua.myapplication.okhttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MyImageView extends ImageView {
    private boolean isFirst=true;
    private ImageListener listener;
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListener();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }
    public void setListener(){
        if(listener==null){
            listener=new ImageListener() {
                @Override
                public void callBack() {
                    if (!getFlag()) return;
                    Rect rect = new Rect();
                    boolean ivIs = getGlobalVisibleRect(rect);
                    if (ivIs) {
                        Log.i("xxx", "scroll imageView可见");
                        setFlag(false);
                    } else {
                        Log.i("xxx", "scroll imageView不可见");
                    }
                }
            };
        }
    }
    public ImageListener getListener(){
        return listener;
    }
    public void setFlag(boolean flag){
        isFirst=flag;
    }
    public boolean getFlag(){
        return isFirst;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        Log.i("xxx", "inivald");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        super.invalidateDrawable(dr);
    }

    @Override
    public void getLocationOnScreen(int[] outLocation) {
        super.getLocationOnScreen(outLocation);
    }

    @Override
    public boolean getGlobalVisibleRect(Rect r, Point globalOffset) {
        return super.getGlobalVisibleRect(r, globalOffset);
    }

    @Override
    public void onWindowSystemUiVisibilityChanged(int visible) {
        super.onWindowSystemUiVisibilityChanged(visible);

    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.i("xxx","vvvvv:"+visibility);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("xxx","gundong");
    }
}
