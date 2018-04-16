package com.example.caoxinghua.myapplication;

import android.app.Application;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.example.caoxinghua.myapplication.hotfix.PatchDownloadIntentService;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.File;


public class NewApplication extends Application {
    private static PatchManager patchManager;
    private static  NewApplication instance=null;
    public static NewApplication getInstance(){
        if(instance==null){
            instance =new NewApplication();
        }
        return instance;
    }
    public NewApplication(){
    }
    @Override
    public void onCreate() {
        super.onCreate();

        patchManager = new PatchManager(this);
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
        if(patchManager!=null){
            return patchManager;
        }else {
            return null;
        }
    }

}
