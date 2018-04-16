package com.example.caoxinghua.myapplication.defview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.caoxinghua.myapplication.R;


public class FlowLayout extends ViewGroup {
    private static  final int DEFAULT_HORIZONTIAL_SPACING=5;
    private static  final int DEFAULT_VERTICAL_SPACING=5;
    private int horizontial_spacing;
    private int vertical_spacing;
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.FlowLayout);
        horizontial_spacing=a.getDimensionPixelSize(R.styleable.FlowLayout_horizontial_spacing,DEFAULT_HORIZONTIAL_SPACING);
        vertical_spacing=a.getDimensionPixelSize(R.styleable.FlowLayout_vertical_spacing,vertical_spacing);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidth=resolveSize(0,widthMeasureSpec);
        int padleft=getPaddingLeft();
        int padTop=getPaddingTop();
        int padRight=getPaddingRight();
        int padBottomm=getPaddingBottom();

        int childLeft=padleft;
        int childTop=padTop;
        int lineH=0;
        for(int i=0;i<getChildCount();i++){
            View childView=getChildAt(i);
//            Log.i("xxx",i+"w:"+childView.getWidth()+"/"+childView.getMeasuredWidth()+"h:"+childView.getHeight()+"/"+childView.getMeasuredHeight());
            if(childView.getVisibility()!=GONE){
                measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            }else {
                continue;
            }
            int childW=childView.getMeasuredWidth();
            int childH=childView.getMeasuredHeight();
//            Log.i("xxx3",i+"w:"+childView.getWidth()+"/"+childW+"h:"+childView.getHeight()+"/"+childH);

            lineH=Math.max(lineH,childH);
            if(childLeft+childW+horizontial_spacing>myWidth){
                childLeft=padleft;
                childTop+=childH+vertical_spacing;
                lineH=childH;
            }else {
                childLeft+=childW+horizontial_spacing;

            }


        }
        int wanH=childTop+lineH+padBottomm;
        setMeasuredDimension(myWidth,resolveSize(wanH,heightMeasureSpec));


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int myWidth = r - l;

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);

            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                childTop += vertical_spacing + lineHeight;
                lineHeight = childHeight;
            }

            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + horizontial_spacing;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("xxx","ondraw");
    }
}
