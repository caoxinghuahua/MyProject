package com.example.caoxinghua.myapplication.dispatch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.caoxinghua.myapplication.R;

/**
 * @author caoxinghua on 2018/11/27
 * @email caoxinghua@gomeplus.com
 */
public class MyFlowView extends ViewGroup {
    private int horizontalSpace;
    private int verticalSpace;
    public MyFlowView(Context context) {
        this(context,null);
    }

    public MyFlowView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyFlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        horizontalSpace=typedArray.getInt(R.styleable.FlowLayout_horizontial_spacing,0);
        verticalSpace=typedArray.getInt(R.styleable.FlowLayout_vertical_spacing,0);
        initView(context);

    }

    private void initView(Context context){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();
        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingBottom=getPaddingBottom();
        int paddingTop=getPaddingTop();
        int width=MeasureSpec.getSize(widthMeasureSpec);

        int h;
        int lineH=0;
        int childLeft=paddingLeft;
        int childTop=paddingTop;
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            int childWidth;
            int childHight;

            if(child.getVisibility()!=GONE){
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
            }else {
                continue;
            }
            childWidth=child.getMeasuredWidth();
            childHight =child.getMeasuredHeight();
            lineH=Math.max(lineH,childHight);
            if(childLeft+childWidth+horizontalSpace>width){
                childLeft=paddingLeft;
                childTop+=lineH+verticalSpace;
                lineH=childHight;
            }else {
                childLeft+=childWidth+horizontalSpace;
            }

        }
        h=lineH+childTop+paddingBottom;
        setMeasuredDimension(width,resolveSize(h,heightMeasureSpec));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mesW=getMeasuredWidth();
        int mesH=getMeasuredHeight();
        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingBottom=getPaddingBottom();
        int paddingTop=getPaddingTop();
        int childCount=getChildCount();
        int childLeft=paddingLeft;
        int childTop=paddingTop;
        int lineH = 0;
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            int childW;
            int childH;
            if(child.getVisibility()!=GONE){
                childW=child.getMeasuredWidth();
                childH=child.getMeasuredHeight();
            }else {
                continue;
            }
            lineH=Math.max(lineH,childH);
            if(childLeft+childW+horizontalSpace>mesW){
                childLeft=paddingLeft;
                childTop+=childH+verticalSpace;
                lineH=childH;
            }

            child.layout(childLeft,childTop,childLeft+childW,childTop+lineH);
            childLeft=childW+horizontalSpace;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mesW=getMeasuredWidth();
        int mesH=getMeasuredHeight();
        int w=getWidth();
        int h=getHeight();
    }
}

