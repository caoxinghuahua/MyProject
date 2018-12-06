package com.example.caoxinghua.myapplication.dispatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

/**
 * @author caoxinghua on 2018/11/26
 * @email caoxinghua@gomeplus.com
 */
public class DispatchActivity extends Activity implements OnTouchListener,View.OnClickListener{
    private Button bt1,bt2;
    private MyFlowView myFlowView;
    private TextView textView;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        initView();
    }
    private void initView(){
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        myFlowView= (MyFlowView) findViewById(R.id.myFlowView);
        textView= (TextView) findViewById(R.id.sub_tv);
        Log.i("xxx","getMe:"+textView.getMeasuredWidth()+"w:"+textView.getWidth()+"/"+bt1.getMeasuredWidth());
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt1.setOnTouchListener(this);
        bt2.setOnTouchListener(this);

        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Handler handler=new Handler(Looper.getMainLooper()){
                    @Override
                    public void handleMessage(Message msg) {
//                        Toast.makeText(DispatchActivity.this,msg.what+"",Toast.LENGTH_SHORT).show();
                    }
                };
                handler.sendEmptyMessage(1);
                Log.i("xxx","address1:"+handler);
                Looper.loop();

            }
        }.start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.bt1:
                Log.i("xxx","bt1 ontouch"+event.getAction());
                break;
            case R.id.bt2:
                Log.i("xxx","bt2 ontouch");
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                Log.i("xxx","bt1 click");
                break;
            case R.id.bt2:
                Log.i("xxx","bt2 click");
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("xxx","ontouch event");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("xxx","dispatch"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
