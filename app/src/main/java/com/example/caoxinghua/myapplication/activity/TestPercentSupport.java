package com.example.caoxinghua.myapplication.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.example.caoxinghua.myapplication.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.Semaphore;

public class TestPercentSupport extends AppCompatActivity {
    private final MyHandler myHandler=new MyHandler(this);
    private HandlerThread handlerThread;
    private Handler handler;
    private Semaphore semaphore;
    private Handler mainHandler;
    private boolean isfresh=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_percent_support);
//        myHandler.postDelayed(runnable,60000);
        handlerThread=new HandlerThread("handlerThread");
        handlerThread.start();

        handler=new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Log.i("xxx","what:"+msg.what+"\n"+Thread.currentThread());

                if(isfresh){
                    checkUpdate();
                }
            }
        };
        mainHandler=new Handler();
        handler.sendEmptyMessage(0);
       /** semaphore=new Semaphore(1);
     new Thread(){
         @Override
         public void run() {
             try {
                 semaphore.acquire();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             Looper.prepare();
             handler=new Handler(){
                 @Override
                 public void handleMessage(Message msg) {
                     Log.i("xxx","what:"+msg.what);

                 }
             };
             semaphore.release();
             Looper.loop();
         }
     }.start();
         new Thread(){
             @Override
             public void run() {
                 try {
                     semaphore.acquire();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 handler.sendEmptyMessage(2);
             }
         }.start();*/
    }

    public void checkUpdate(){
        isfresh=false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("xxx","id:"+Thread.currentThread());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("xxx","vvvv:"+Thread.currentThread());
                Message message1=handler.obtainMessage(2,4,6);
                handler.sendMessage(message1);
            }
        });
    }

    private  static class  MyHandler extends Handler{
        private WeakReference<Activity> activityWeakReference;
        public MyHandler(Activity activity){
            activityWeakReference=new WeakReference<Activity>(activity);
        }
        public void handleMessage(Message msg){
            Activity activity=activityWeakReference.get();
            if(activity!=null){
                Log.i("xxx","handler");
            }
        }
    }
    private static Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.i("xxx","runnable");
        }
    };
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        myHandler.removeCallbacksAndMessages(null);
        if(handlerThread!=null){
            handlerThread.quit();
        }
    }
}
