package com.example.caoxinghua.myapplication.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.caoxinghua.myapplication.R;

import com.example.caoxinghua.myapplication.util.NetUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OkHttpTestActivity extends AppCompatActivity {
    private String url="http://flight-pre.gomeplus.com/flight?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_main);
        new Thread(){
            public void run(){
                testOkhttp();
//                  Map<String,String>  map=new HashMap<String, String>();
//                  map.put("slotId","10014");
//                  map.put("requestType","2");
//                  getPostData(map);
            }
        }.start();
        Map<String,String>  map=new HashMap<String, String>();
                 map.put("slotId","10014");
                 map.put("requestType","2");

        for(String key:map.keySet()){
            System.out.println("key:"+key+"\n"+"value:"+map.get(key));
        }

        Iterator<Map.Entry<String,String>> iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            System.out.println("key:"+entry.getKey()+"\n"+"value:"+entry.getValue());
        }

        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+"\n"+"value:"+entry.getValue());
        }
        NetUtils.getPostData(url, map, new NetUtils.HttpCallBack<String>() {
            @Override
            public void success(String object) {
                Log.i("xxx", "data:" + object);
            }

            @Override
            public void error(String error) {

            }
        });

    }
    private void testOkhttp(){
        OkHttpClient httpClient=new OkHttpClient();
        //type=1 同步get 2异步get 3同步post 4异步post
        //同步时不能在主线程直接调用
        int type=4;
        try {
            if (type == 1) {
                final Request request=new Request.Builder().url(url+"slotId=10016&requestType=2")
                        .build();
                Call call=httpClient.newCall(request);
                Response response=call.execute();
                if(response.isSuccessful()){
                    Log.i("xxx", "get sync response:" + response.body().string());
                }else {
                    throw new IOException("Unexpected code " + response);
                }
            } else if (type == 2) {
                final Request request=new Request.Builder().url(url+"slotId=10016&requestType=2")
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
                Request request=new Request.Builder().url(url)
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
                int flag=2;
                RequestBody requestBody=null;
                if(flag==1){
                    requestBody=new FormEncodingBuilder()
                            .add("slotId","10015")
                            .add("requestType","2")
                            .build();
                }else if(flag==2){

                    JSONObject object=new JSONObject();
                    object.put("slotId","10014");
                    object.put("requestType","2");
                    requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),object.toString());
                }

                final Request request=new Request.Builder().url(url)
                        .post(requestBody)
                        .build();
                Log.i("xxx","start:"+System.currentTimeMillis());
                Call call=httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.i("xxx","end:"+System.currentTimeMillis());
                        Log.i("xxx", "post async response:" + response.body().string()+"\n"+response.headers());
                    }
                });
            }
        }catch (Exception e){

        }
    }


}
