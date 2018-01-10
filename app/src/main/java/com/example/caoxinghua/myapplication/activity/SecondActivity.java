package com.example.caoxinghua.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.caoxinghua.myapplication.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG=getClass().getSimpleName();
    private Button switch1Bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_second);
        Log.i(TAG,"onCreate()");
        initView();
    }
    private void initView(){
        switch1Bt= (Button) findViewById(R.id.swithTo1Bt);
        switch1Bt.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.swithTo1Bt:
                startActivity(new Intent(this,First_Activity.class));
                break;
        }
    }
}
