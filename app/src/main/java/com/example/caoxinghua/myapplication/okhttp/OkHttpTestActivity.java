package com.example.caoxinghua.myapplication.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.VideoView;

import com.example.caoxinghua.myapplication.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class OkHttpTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_main);
        new Thread(){
            public void run(){
                testOkhttp();
            }
        }.start();

    }
    private void testOkhttp(){

        OkHttpClient httpClient=new OkHttpClient();
        //type=1 同步get 2异步get 3同步post 4异步post
        //同步时不能在主线程直接调用
        int type=4;
        try {
            if (type == 1) {
                final Request request=new Request.Builder().url("https://adflight-pre.gomeplus.com/flight?slotId=10016&requestType=2")
                        .build();
                Call call=httpClient.newCall(request);
                Response response=call.execute();
                if(response.isSuccessful()){
                    Log.i("xxx", "get sync response:" + response.body().string());
                }else {
                    throw new IOException("Unexpected code " + response);
                }
            } else if (type == 2) {
                final Request request=new Request.Builder().url("https://adflight-pre.gomeplus.com/flight?slotId=10016&requestType=2")
                        .build();
                Call call=httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        final String json = response.body().string();
                        Log.i("xxx", "get async response:" + json);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTitle(json);
                            }
                        });
                    }
                });

            } else if (type == 3) {
                RequestBody requestBody=new FormEncodingBuilder()
                        .add("slotId","10016")
                        .add("requestType","2")
                        .build();
                Request request=new Request.Builder().url("https://adflight-pre.gomeplus.com/flight?")
                        .post(requestBody)
                        .build();
                Response response=httpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    Log.i("xxx", "post sync response:" + response.body().string());
                }else {
                    throw new IOException("Unexpected code " + response);
                }
            } else if (type == 4) {
                //flag=1;键值对 flag=2;json格式
                int flag=1;
                RequestBody requestBody=null;
                if(flag==1){
                    requestBody=new FormEncodingBuilder()
                            .add("slotId","10016")
                            .add("requestType","2")
                            .build();
                }else if(flag==2){

                    requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"{\"key\":\"test\"}");
                }

                Request request=new Request.Builder().url("https://adflight-pre.gomeplus.com/flight?")
                        .post(requestBody)
                        .build();
                Call call=httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.i("xxx", "post async response:" + response.body().string());
                    }
                });
            }
        }catch (Exception e){

        }
    }
}
