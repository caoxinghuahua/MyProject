package com.example.caoxinghua.myapplication.service;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;

import java.io.Serializable;

public class TestServiceActivity extends AppCompatActivity implements View.OnClickListener{
    private Button startBt,stopBt,bindBt,unBindBt,accessBt;
    private TextView contentTv;
    private MyService myService;
    private boolean isBind=false;
    Messenger rMessenger;
    Messenger tMessenger;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_service);
        initView();
        handler=new Handler(){
          public void handleMessage(Message msg){
              switch (msg.what){
                  case 6:
                      Log.i("xxx","test6");
                      break;
              }
          }
        };
        tMessenger=new Messenger(handler);


    }
    private void initView(){
        startBt= (Button) findViewById(R.id.startBt);
        stopBt= (Button) findViewById(R.id.stopBt);
        bindBt= (Button) findViewById(R.id.bindBt);
        unBindBt= (Button) findViewById(R.id.unBindBt);
        accessBt= (Button) findViewById(R.id.accessBt);
        contentTv= (TextView) findViewById(R.id.contentTv);
        startBt.setOnClickListener(this);
        stopBt.setOnClickListener(this);
        bindBt.setOnClickListener(this);
        unBindBt.setOnClickListener(this);
        accessBt.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.startBt:
                intent=new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.stopBt:
                intent=new Intent(this,MyService.class);
                stopService(intent);
                break;
            case R.id.bindBt:
                doBindService();
                break;
            case R.id.unBindBt:
                doUnbindService();
                break;
            case R.id.accessBt:
                startActivity(new Intent(this,AccessibilityActivity.class));
                break;
        }

    }
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
          /*  myService=((MyService.MyBinder)service).getService();
            contentTv.setText("num:0");
            myService.addCallBack(new ServiceCallBack() {
                @Override
                public void showNum(final int num) {
                    final int s=num;
                    Log.i("xxx","num:"+s);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contentTv.setText("num:"+num);
                        }
                    });
                }
            });
            myService.test();*/
            rMessenger=new Messenger(service);
            Message message=Message.obtain(null,1,2,3);
            message.replyTo=tMessenger;
            try {
                rMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService.removeCallBack();
            myService=null;
        }
    };

    private void doBindService(){
        if (isBind)return;
        isBind=true;
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
//        startService(intent);


    }
    @Override
    protected void onStop() {
        super.onStop();
//        doUnbindService();
    }
    private void doUnbindService(){
        if(!isBind)return;
        unbindService(connection);
//        stopService(new Intent(this,MyService.class));
        isBind=false;
    }
}
