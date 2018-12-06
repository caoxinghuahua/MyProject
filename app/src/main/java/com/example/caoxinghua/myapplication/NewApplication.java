package com.example.caoxinghua.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.euler.andfix.patch.PatchManager;
import com.example.caoxinghua.myapplication.hotfix.PatchDownloadIntentService;



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

        //init router
        initRouter(this);
        initBugly();

    }
    public PatchManager getPatchManager(){
        if(patchManager!=null){
            return patchManager;
        }else {
            return null;
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void initRouter(Application application){
        if(BuildConfig.DEBUG){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);
    }
    private void initBugly(){
//        CrashReport.initCrashReport(getApplicationContext(), "ddac4b61c0", false);   //异常统计接入方式
    }
}
