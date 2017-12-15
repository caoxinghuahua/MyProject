package com.example.caoxinghua.myapplication;

import android.app.Application;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.example.caoxinghua.myapplication.hotfix.PatchDownloadIntentService;

import java.io.File;


public class NewApplication extends Application {
    private static PatchManager patchManager;
    private static  NewApplication instance=null;
    public static NewApplication getInstance(){
        if(instance==null){
            instance =new NewApplication();
        }
        Log.i("xxx","in:"+instance);
        return instance;
    }
    public NewApplication(){
        Log.i("xxx","cc1:"+NewApplication.this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("xxx","cc2:"+NewApplication.this);

        Log.i("xxx","x1");
        patchManager = new PatchManager(this);
        Log.i("xxx","x11"+patchManager);
        patchManager.init(AppUtils.getVersionName(this));
        patchManager.loadPatch();
        Intent patchDownloadIntent = new Intent(this, PatchDownloadIntentService.class);
        patchDownloadIntent.putExtra("url", "test");
        startService(patchDownloadIntent);
//        File dir=new File(Environment.getExternalStorageDirectory()+"/shine/patch");
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
////        File patchFile = new File(dir, String.valueOf(System.currentTimeMillis()) + ".apatch");
////        downloadFile(downloadUrl, patchFile);
//        File patchFile = new File(dir, "fix.apatch");
//        try {
//            patchManager.addPatch(patchFile.getAbsolutePath());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
    public PatchManager getPatchManager(){
        Log.i("xxx","x33");
        if(patchManager!=null){
            Log.i("xxx","x3");
            return patchManager;
        }else {
            Log.i("xxx","x4");
            return null;
        }
    }

}
