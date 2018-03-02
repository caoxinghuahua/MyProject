package com.example.caoxinghua.myapplication.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.example.caoxinghua.myapplication.R;

public class ConstraintActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private HandlerThread handlerThread;
    private Handler handler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        initView();
        test();
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

    }

    public void test() {
        handlerThread = new HandlerThread("test");
        handlerThread.start();
        handler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            }
        };
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                for (int i = 0; i < 30; i++) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler1.sendEmptyMessage(0);
                }
            }
        };
//       handler.sendEmptyMessage(0);

    }
}

