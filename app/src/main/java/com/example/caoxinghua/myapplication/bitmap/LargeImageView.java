package com.example.caoxinghua.myapplication.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author caoxinghua on 2018/11/22
 * @email caoxinghua@gomeplus.com
 */
public class LargeImageView extends ImageView implements GestureDetector.OnGestureListener{
    private static final String TAG = "LargeImageView";

    private BitmapRegionDecoder mDecoder;
    /**
     * 绘制的区域
     */
    private volatile Rect mRect = new Rect();

    private int mScaledTouchSlop;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    /**
     * 图片的宽度和高度
     */
    private int mImageWidth, mImageHeight;
    private GestureDetector mGestureDetector;
    private BitmapFactory.Options options;

    public LargeImageView(Context context) {
       this(context,null);
    }

    public LargeImageView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LargeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mLastX= (int) e.getRawX();
        mLastY= (int) e.getRawY();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        int x= (int) e2.getRawX();
        int y= (int) e2.getRawY();
        move(x,y);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
       mLastX= (int) e.getRawX();
       mLastY= (int) e.getRawY();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int x = (int) e2.getRawX();
        int y = (int) e2.getRawY();
        move(x, y);
        return true;
    }
    private void init(Context context){
        options=new BitmapFactory.Options();
        options.inPreferredConfig= Bitmap.Config.RGB_565;
        mGestureDetector=new GestureDetector(this);
        mScaledTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
        InputStream inputStream=null;
        try {
            inputStream=context.getAssets().open("timg.jpg");
            mDecoder=BitmapRegionDecoder.newInstance(inputStream,false);

            BitmapFactory.Options tmpOption=new BitmapFactory.Options();
            tmpOption.inJustDecodeBounds=true;
            inputStream.reset();
            Bitmap bitmap=BitmapFactory.decodeStream(inputStream,null,options);

            mImageWidth=bitmap.getWidth();
            mImageHeight=bitmap.getHeight();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);//触摸事件交给gesture
    }
    private void move(int x,int y){
        boolean isInvalidate=false;
        int delteX=x-mLastX;
        int delteY=y-mLastY;
        if(mImageWidth>getWidth()){
            mRect.offset(-delteX,0);
            //检测右端
            if(mRect.right>mImageWidth){
               mRect.right=mImageWidth;
               mRect.left=mImageWidth-getWidth();
            }
            //检查左端
            if (mRect.left < 0) {
                mRect.left = 0;
                mRect.right = getWidth();
            }
            isInvalidate = true;
        }
        //如果图片高度大于屏幕高度
        if (mImageHeight > getHeight()) {
            mRect.offset(0, -delteY);

            //是否到达最底部
            if (mRect.bottom > mImageHeight) {
                mRect.bottom = mImageHeight;
                mRect.top = mImageHeight - getHeight();
            }

            if (mRect.top < 0) {
                mRect.top = 0;
                mRect.bottom = getHeight();
            }
            isInvalidate = true;

        }

        if (isInvalidate) {
            invalidate();
        }

        mLastX = x;
        mLastY = y;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getMeasuredWidth();
        int height=getMeasuredHeight();

        int imgW=mImageWidth;
        int imgH=mImageHeight;
        mRect.left=imgW/2-width/2;
        mRect.top=imgH/2-height/2;
        mRect.right=mRect.left+width;
        mRect.bottom=mRect.top+height;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap=mDecoder.decodeRegion(mRect,options);
        canvas.drawBitmap(bitmap,0,0,null);
    }
}
