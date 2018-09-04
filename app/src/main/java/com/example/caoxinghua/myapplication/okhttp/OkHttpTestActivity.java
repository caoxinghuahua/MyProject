package com.example.caoxinghua.myapplication.okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;

import com.bumptech.glide.Glide;
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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OkHttpTestActivity extends AppCompatActivity {
    private String url = "https://flight.gomeplus.com/flight?";
    private String imageUrl = "http://pic5.zhongsou.com/img?id=522f8c6e488097cef53!sy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_main);

        ImageView imageView=(ImageView) findViewById(R.id.iv);
//        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);

        String path = Environment.getDataDirectory().getAbsolutePath() + "\n" + Environment.getDownloadCacheDirectory().getAbsolutePath() + "\n"
                + Environment.getRootDirectory().getAbsolutePath() + "\n" + Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.i("xxx", "path:" + path);
        for(int i=0;i<1;i++){
            testOkhttp(""+(10014+i));
        }
    /**    new Thread() {
            public void run() {
               testOkhttp();
//                  Map<String,String>  map=new HashMap<String, String>();
//                  map.put("slotId","10014");
//                  map.put("requestType","2");
//                  getPostData(map);
            }
        }.start();*/

        Map<String, String> map = new HashMap<String, String>();
        map.put("slotId", "10014");
        map.put("requestType", "2");

        for (String key : map.keySet()) {
            System.out.println("key:" + key + "\n" + "value:" + map.get(key));
        }
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key:" + entry.getKey() + "\n" + "value:" + entry.getValue());
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + "\n" + "value:" + entry.getValue());
        }
        NetUtils.getPostData(url, map, new NetUtils.HttpCallBack<String>() {
        @Override public void success(String object) {
                      Log.i("xxx", "data:" + object);
        }

        @Override public void error(String error) {

        }
        });
    }

    private void testOkhttp(String slotId) {
        OkHttpClient httpClient = new OkHttpClient();
//        httpClient.setProxy(Proxy.NO_PROXY);
        //type=1 同步get 2异步get 3同步post 4异步post
        //同步时不能在主线程直接调用
        int type = 4;
        try {
            if (type == 1) {
                final Request request = new Request.Builder().url(url + "slotId=10016&requestType=2")
                        .build();
                Call call = httpClient.newCall(request);
                Response response = call.execute();
                if (response.isSuccessful()) {
                    Log.i("xxx", "get sync response:" + response.body().string());
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } else if (type == 2) {
                final Request request = new Request.Builder().url(url + "slotId=10016&requestType=2")
                        .build();
                Call call = httpClient.newCall(request);
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
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("slotId", "10016")
                        .add("requestType", "2")
                        .build();
                Request request = new Request.Builder().url(url)
                        .post(requestBody)
                        .build();
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    Log.i("xxx", "post sync response:" + response.body().string());
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } else if (type == 4) {
                //flag=1;键值对 flag=2;json格式
                int flag = 2;
                RequestBody requestBody = null;
                if (flag == 1) {
                    requestBody = new FormEncodingBuilder()
//                            .add("slotId", ""+slotId)
//                            .add("requestType", "2")
                            .add("deviceId","53d512452404d363")
                            .add("deviceIdType","ANDROID_ID")
                            .add("deviceType","Android")
                            .add("pushToken","Ahefb1rKwtuwAKnOETMZlTOklneUR4_syIlTFrSB28w8")
                            .build();
                } else if (flag == 2) {

                    JSONObject object = new JSONObject();
//                    object.put("slotId", ""+slotId);
//                    object.put("requestType", "2");
                    object.put("deviceId","53d512452404d363");
                    object.put("deviceIdType","ANDROID_ID");
                    object.put("deviceType","Android");
                    object.put("pushToken","Ahefb1rKwtuwAKnOETMZlTOklneUR4_syIlTFrSB28w8");
                    String content="https://m-discovery.gomeplus.com/product/40523.html?p=ZSldnq8ZTRxVUkeSrklWEsmWQAbdVUBYCqJ21ubdr6YwPAj6bb2Hxin0Tgt3vjOZTBt1T94rrFZ++/mTJSiMIb6OSWTqBg40i1fs0YC6kJgHBblOFmMVQh99N+O01bSU6agqX7BHej2mCXucJ/QWPtYXZCuwJQE6ieY7Pf4xKqT1GWbFxig4/UElwvBckKVm3mR+ZVF0IlGSKkF1J5xD1a+7dFIPrhC8I2vjgGx+8FQ+/ptech/dP95CbE7pHLB2&q=///gKIcUOJRt94EslsYATaFT0cXYs1+Er9tEkXIsNdW1xXEfkW26TeN3XOl4V+mwlXxCi5AxLo708npUF8bTTetDAeQd4n2W/Cc8uQLOMdNa3ITHbuYBj0SgjcFxsyeSv7JhVbitOGVPltVuCnZe3t43XVshEGDv8ZN3DF2XZRGfoCA994lOPRbyXgDHnFQHZdbegVJ2ihxQ02dYhBV5N6MrdswVAtQl0RvLgLbUxSynBINmzPefeeZxj2gujiZR2v+RBh5SY4qgHMfoUC1OiSbH4cAEIkOYhSQ+v5fE2OzSZmzyOGHGHGgyZMzZhOgjtBumsqyOZ193JtsflaKxZSeuCgzU/ScVl4DsgR8gUyEO60K9pwApMnYVKR/Oswq8AmowcBDRlTaIQW4Aye63akUCaBSIqT8IVZnkqwkIcz797AFG2ww+QO80PV+q64GG8GsulnPqkeyPL3aMth94Tzdo57gwAhhq7LFm0v9JPQzI6UHAhnCgnRhzJNoBo3A7QHYVNnAAS3/z4qCf4wnvzglIrhTodsYvrtjSPvEk+OQUZOXdxVjvwFOmrSGvlnt28qbMdXG3bBizcEh/4UgunQ==&s=Du+h74W/uT2KvmUncZ9oc7rerFpH90Efh2+Ug5cuqgN5+viVpkZya+2wc4fh2gp2GLCFFmgf7pFAQlA5Ino2BOzqRhEgCNtyhoWU5+5k+N3D1UjI5mh8IXh0Al6sWedsr+oSYsMnJw5V5s3MUOykxg==";
                    requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
                }

                final Request request = new Request.Builder().url("https://goawall.gome.com.cn/short/get")
                        .post(requestBody)
                        .build();
                final long start=System.currentTimeMillis();
                Log.i("xxx", "start:" + start);
                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.i("xxx", "error:" + (System.currentTimeMillis()-start));
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.i("xxx", "end:" + (System.currentTimeMillis()-start));
                       Log.i("xxx", "post async response:" + response.body().string());
                    }
                });
            }
        } catch (Exception e) {
           Log.i("xxx","error:"+e.toString());
        }
    }

    //定义activity的进入和退出动画
    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }


    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onStop() {
        super.onStop();


    }
}
