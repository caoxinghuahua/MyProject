package com.example.caoxinghua.myapplication.glideAndPicasso;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;

public class AppComActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final LayoutInflater inflater=getLayoutInflater();
        LayoutInflaterCompat.setFactory(inflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate=getDelegate();
                View view=delegate.createView(parent, name, context, attrs);
                int count=attrs.getAttributeCount();
                for(int i=0;i<count;i++){
                    Log.i("xxx",attrs.getAttributeName(i)+":"+attrs.getAttributeValue(i));
                }

                if(view!=null&&view instanceof TextView){
                   ((TextView) view).setTextSize(25);
                }
                return view;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_com);
    }
}
