package com.example.caoxinghua.myapplication.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Scroller;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

public class First_Activity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG=getClass().getSimpleName();
    private Button switch2Bt,switch3Bt;
    private TextView saveTv;
    private int num;
    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_first);
        initView();
        Log.i(TAG,"onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"onConfigurationChanged()");

    }

    @Override
    protected void onResume() {
        super.onResume();
//        saveTv.setText("num:"+num);
        Log.i(TAG,"onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("num",num);
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState()");


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        num=savedInstanceState.getInt("num");
        Log.i(TAG,"onRestoreInstanceState()");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG,"onNewIntent()");
    }

    private void initView(){
        switch2Bt= (Button) findViewById(R.id.switchTo2_bt);
        switch2Bt.setOnClickListener(this);
        saveTv= (TextView) findViewById(R.id.saveTv);
        switch3Bt= (Button) findViewById(R.id.switchTo3_bt);
        switch3Bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.switchTo2_bt:
                num++;
                saveTv.setText("num:"+num);
                intent.setClass(this,SecondActivity.class);
                startActivity(intent);
//                myView.invalidate();
                break;
            case R.id.switchTo3_bt:
//                intent.setClass(this,ThirdActivity.class);
                intent.setClass(this,TestPercentSupport.class);
                startActivity(intent);
                break;
        }
    }
}
