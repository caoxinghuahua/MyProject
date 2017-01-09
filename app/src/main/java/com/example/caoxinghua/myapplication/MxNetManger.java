package com.example.caoxinghua.myapplication;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by caoxinghua on 2016/11/3.
 */

public class MxNetManger {
    private int count;
    private Handler mHandler = new Handler();
    Thread  myThread;
    private  boolean isFault;
    private boolean isRun=true;
    public void sendRequest() {
        myThread=new Thread(){
            public void run(){
             send();
            }
        };
         myThread.start();
    }


    private void send(){
        HttpsURLConnection connection;
        while(isRun){
            try {
                URL url = new URL("https://adflight-pre.gomeplus.com/flight?slotId=10016&&requestType=2");

                connection = (HttpsURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                //设置客户端与服务连接类型
                connection.addRequestProperty("Connection", "Keep-Alive");
                // Workaround for the M release HttpURLConnection not observing the
                // HttpURLConnection.setFollowRedirects() property.
                // https://code.google.com/p/android/issues/detail?id=194495
                connection.setInstanceFollowRedirects(HttpsURLConnection.getFollowRedirects());
                connection.setSSLSocketFactory(getSSLSocketFactory());
                connection.connect();
                int status = connection.getResponseCode();
                if (status == 200) {
                    String result = streamToString(connection.getInputStream());
                    Log.i("xxx", "result:" + result);
                    isRun=false;
                }else{
                    count++;
                    isFault=true;
                }
                connection.disconnect();
            } catch (Exception exception) {
                count++;
                isFault=true;
                Log.i("xxx", "exc:" + exception.getMessage());
            }
            if(count<3&&isFault){
                send();
            }else{
                isRun=false;
                isFault=false;
                count=0;
            }
        }

    }
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("xxx", e.toString());
            return null;
        }
    }

    public static javax.net.ssl.SSLSocketFactory getSSLSocketFactory() {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certificates, String s) throws CertificateException {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());

        } catch (Exception e) {

        }
        return sc.getSocketFactory();
    }
}
