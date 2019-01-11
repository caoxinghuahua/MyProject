package com.example.caoxinghua.myapplication.okhttp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;
import com.example.caoxinghua.myapplication.hotfix.FixMainActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VisibleTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible_test);

        final MyScrollView scrollView = (MyScrollView) findViewById(R.id.scrollView);
        final ImageView imageView = (ImageView) findViewById(R.id.iv);

        final ImageView imageView1 = (ImageView) findViewById(R.id.iv1);
        final ImageView imageView2 = (ImageView) findViewById(R.id.iv2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rect rect1 = new Rect();
                boolean ivIs = imageView1.getGlobalVisibleRect(rect1);
                Log.i("xxx", "w: " + rect1.width() + "h: " + rect1.height());
                Log.i("xxx", "w: " + imageView1.getMeasuredWidth() + "h: " + imageView1.getMeasuredHeight());

                if (isViewCovered(imageView1)) {
                    Log.i("xxx", "imageView1不可见");
                } else {
                    Log.i("xxx", "imageView1可见");
                }
                Point p = new Point();
                getWindowManager().getDefaultDisplay().getSize(p);
                int screenWidth = p.x;
                int screenHeight = p.y;
                Rect rect3 = new Rect(0, 0, screenWidth, screenHeight);
                Log.i("xxx", "w: " + screenWidth + "h: " + screenHeight);
                if (isViewCovered(imageView2)) {
                    Log.i("xxx", "imageView2不可见");
                } else {
                    Log.i("xxx", "imageView2可见");
                }
            }
        });
        MyImageView imageView3 = (MyImageView) findViewById(R.id.iv3);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisibleTestActivity.this, FixMainActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean isCover(View view) {

        if (view.getVisibility() != View.VISIBLE) {
            return true;
        }
        Rect rect = new Rect();
        boolean visbleRect = view.getGlobalVisibleRect(rect);
        if (!visbleRect) {
            return true;
        }
        View cuttentView = view;
        while (cuttentView.getParent() != null && cuttentView instanceof ViewGroup) {
            if (((ViewGroup) cuttentView.getParent()).getVisibility() != View.VISIBLE) {
                return true;
            }
            cuttentView = (View) cuttentView.getParent();
        }
        return false;
    }
    public boolean isViewCovered(final View view) {
        View currentView = view;

        Rect currentViewRect = new Rect();
        boolean partVisible = currentView.getGlobalVisibleRect(currentViewRect);//当前view是否在屏幕可视区域（w,h）>)
//        boolean totalHeightVisible = (currentViewRect.bottom - currentViewRect.top) >= view.getMeasuredHeight();
//        boolean totalWidthVisible = (currentViewRect.right - currentViewRect.left) >= view.getMeasuredWidth();
//        boolean totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible;
        // if any part of the view is clipped by any of its parents,return true
        if (!partVisible) return true;

        while (currentView.getParent() instanceof ViewGroup) {
            ViewGroup currentParent = (ViewGroup) currentView.getParent();
            // if the parent of view is not visible,return true
            if (currentParent.getVisibility() != View.VISIBLE) return true;//判断父view的可见性

            int start = indexOfViewInParent(currentView, currentParent);
            for (int i = start + 1; i < currentParent.getChildCount(); i++) {
                Rect viewRect = new Rect();
                view.getGlobalVisibleRect(viewRect);
                View otherView = currentParent.getChildAt(i);
                Rect otherViewRect = new Rect();
                otherView.getGlobalVisibleRect(otherViewRect);
                // if view intersects its older brother(covered),return true
                if (otherViewRect.contains(viewRect)&&otherView.getVisibility()==View.VISIBLE) return true;//判断当前view是否被包含及兄弟view的可见性
            }
            currentView = currentParent;
        }
        return false;
    }
    //找出当前view在父view中的index
    private int indexOfViewInParent(View view, ViewGroup parent) {
        int index;
        for (index = 0; index < parent.getChildCount(); index++) {
            if (parent.getChildAt(index) == view)
                break;
        }
        return index;
    }
}
