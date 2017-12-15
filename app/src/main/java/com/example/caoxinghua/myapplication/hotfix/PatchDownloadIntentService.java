package com.example.caoxinghua.myapplication.hotfix;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

import com.example.caoxinghua.myapplication.NewApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class PatchDownloadIntentService extends IntentService {
    private int fileLength,downloadLength;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public PatchDownloadIntentService(String name) {
        super(name);
    }
    public PatchDownloadIntentService(){
        super("ds");
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            String downloadUrl=intent.getStringExtra("url");
            if(downloadUrl!=null&&!downloadUrl.equals("")){
                downloadPatch(downloadUrl);
            }
        }
    }
    private void downloadPatch(String downloadUrl){
        File dir=new File(Environment.getExternalStorageDirectory()+"/shine/patch");
        if(!dir.exists()){
            dir.mkdirs();
        }
//        File patchFile = new File(dir, String.valueOf(System.currentTimeMillis()) + ".apatch");
//        downloadFile(downloadUrl, patchFile);
        File patchFile = new File(dir, "fix.apatch");

        if (patchFile.exists() && patchFile.length() > 0 && fileLength >= 0) {
            try {
                Log.i("xxx","x2"+NewApplication.getInstance()+"//"+NewApplication.getInstance().getPatchManager());
                NewApplication.getInstance().getPatchManager().addPatch(patchFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void downloadFile(String downloadUrl, File file){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStream ips = null;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setReadTimeout(10000);
            huc.setConnectTimeout(3000);
            fileLength = Integer.valueOf(huc.getHeaderField("Content-Length"));
            ips = huc.getInputStream();
            int hand = huc.getResponseCode();
            if (hand == 200) {
                byte[] buffer = new byte[8192];
                int len = 0;
                while ((len = ips.read(buffer)) != -1) {
                    if (fos != null) {
                        fos.write(buffer, 0, len);
                    }
                    downloadLength = downloadLength + len;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (ips != null) {
                    ips.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
