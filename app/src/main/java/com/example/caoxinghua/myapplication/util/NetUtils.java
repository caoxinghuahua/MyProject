package com.example.caoxinghua.myapplication.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caoxinghua on 2017/4/14.
 */

public class NetUtils {
    private static ExecutorService executorService;
    private final  static  int DEFAULT_POOL=4;
    static {
        executorService= Executors.newFixedThreadPool(DEFAULT_POOL);
    }
    public interface HttpCallBack<T> {
        public void success(T object);

        public void error(T error);
    }
    public static void getPostData(final String url, final Map<String,String> paramsMap, final HttpCallBack<String> callBack){
       executorService.submit(new Runnable() {
           @Override
           public void run() {
               Log.i("xxx","s1:"+System.currentTimeMillis());
               URL u;
               try {
                   StringBuilder tempParams = new StringBuilder();
                   int pos = 0;
                   for (String key : paramsMap.keySet()) {
                       if (pos > 0) {
                           tempParams.append("&");
                       }
                       tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key),"utf-8")));
                       pos++;
                   }
                   String params =tempParams.toString();
                   // 请求的参数转换为byte数组
                   byte[] postData = params.getBytes();
                   u=new URL(url);
                   HttpURLConnection urlConnection= (HttpURLConnection) u.openConnection();
                   urlConnection.setConnectTimeout(5000);
                   urlConnection.setRequestMethod("POST");
                   urlConnection.setDoOutput(true);
                   urlConnection.setRequestProperty("Content-Type", "application/json");
                   urlConnection.connect();
                   Log.i("xxx","s2:"+System.currentTimeMillis());
                   DataOutputStream dos= new DataOutputStream(urlConnection.getOutputStream());
                   dos.write(postData);
                   dos.flush();
                   dos.close();
                   int statusCode=urlConnection.getResponseCode();
                   if(statusCode==200){
                       ByteArrayOutputStream baos=new ByteArrayOutputStream() ;
                       InputStream is=urlConnection.getInputStream();
                       byte[] buffer=new byte[1024];
                       int len=0;
                       while((len=is.read(buffer))!=-1){
                           baos.write(buffer,0,len);
                       }
                       baos.close();
                       is.close();
                       byte [] byteArray=baos.toByteArray();
                       Log.i("xxx","s3:"+System.currentTimeMillis());
                       callBack.success(new String(byteArray));
                   }else{
                       callBack.error("error");
                   }
               }catch (Exception e){

               }
           }
       });
    }
}
