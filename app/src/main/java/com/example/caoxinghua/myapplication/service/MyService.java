package com.example.caoxinghua.myapplication.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private IBinder binder=new MyBinder();
    private ServiceCallBack callBack;
    private Messenger messenger;
    private Handler mHandler;
    public MyService() {
    }
    class  MyBinder extends Binder {
           MyService getService(){
               return MyService.this;
           }
    }

    public void addCallBack(ServiceCallBack callBack){
        this.callBack=callBack;
    }
    public void removeCallBack(){
        callBack=null;
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("xxx", "onBind()");

//        return binder;
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("xxx", "onCreate()");
        mHandler=new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        Log.i("xxx", "service handler1");
                        try {
                            msg.replyTo.send(Message.obtain(null,6,7,8));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

        };
        messenger=new Messenger(mHandler);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("xxx","onStart()"+(intent==null));
        startForeground();

        return START_NOT_STICKY;

    }
    public void test(){
        new Thread(){
            public void run(){
                int num=0;
                for(int i=0;i<10; i++){

                    num=i;
                    Log.i("xxx","111");
                    if(callBack!=null){
                        Log.i("xxx","222");
                        callBack.showNum(num);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }.start();

    }
    private void startForeground() {
        Notification note = new Notification(0, "test service",
                System.currentTimeMillis());
        note.flags |= Notification.FLAG_NO_CLEAR;
//        if (android.os.Build.VERSION.SDK_INT < 18)
            startForeground(134139, note);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("xxx","onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("xxx","onRebind()");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("xxx","onDestroy()");
        stopForeground(true);
//        Intent intent=new Intent(this,MyService.class);
//        startService(intent);
    }
}
