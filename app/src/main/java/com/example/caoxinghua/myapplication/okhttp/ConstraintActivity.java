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
import com.google.gson.JsonObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


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
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody= new FormEncodingBuilder().add("key","value").build();
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put("key","value");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody requestBody1= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
//        RequestBody requestBody12=new MultipartBuilder().type(MultipartBuilder.FORM).addPart(Headers.of()).build();
        Request request=new Request.Builder().url("").post(requestBody).build();
        Call call=okHttpClient.newCall(request);
        try {
            Response response=call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        call.enqueue(new Callback() {
           @Override
           public void onFailure(Request request, IOException e) {

           }

           @Override
           public void onResponse(Response response) throws IOException {

           }
        });
        okhttp3.OkHttpClient.Builder builder=new okhttp3.OkHttpClient.Builder();
        okhttp3.OkHttpClient client=builder.build();


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

