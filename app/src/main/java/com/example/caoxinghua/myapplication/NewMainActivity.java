package com.example.caoxinghua.myapplication;

import android.*;
import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Debug;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gomeplus.meixin.ad.manger.MXAdsInstance;
import com.gomeplus.meixin.ad.view.MXAdsBannerView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class NewMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<JumpBean> list=new ArrayList<JumpBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
//            Debug.startMethodTracing("/sdcard/mytrace");
//        }

        setContentView(R.layout.activity_new_main);
        initView();
        initData();
        init();
        SharedPreferences sharedPreferences=this.getSharedPreferences("test",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id","666");
        editor.commit();
        testDateFile();
    }
    private void initView(){

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,0,0,1);
            }
        });

    }
    private void init(){
        NewMainAdapter adapter=new NewMainAdapter(this,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent=new Intent();
                intent.setComponent(new ComponentName("com.example.caoxinghua.myapplication",list.get(position).getJumpStr()));
                startActivity(intent);
            }
        });
    }
    private void initData(){


        //test
        JumpBean bean=new JumpBean();
        bean.setName("全屏test fix");
        bean.setJumpStr("com.example.caoxinghua.myapplication.MainActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("MVVM 测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.MvvmTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("DrawLayout测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.DrawLayoutTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("RxJava测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.rxjava.TestRxJavaActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("OkHttp测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.okhttp.OkHttpTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("Video");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.TestVideo");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("SurfacePlayer");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.SurfacePlayer");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("Video");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.TestVideo");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("TestIjk");
        bean.setJumpStr("com.example.caoxinghua.myapplication.TestIjk");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("自定义keyboard");
        bean.setJumpStr("com.example.caoxinghua.myapplication.keyboard.KeydemoActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("HotFix热修复");
        bean.setJumpStr("com.example.caoxinghua.myapplication.hotfix.FixMainActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("可见曝光");
        bean.setJumpStr("com.example.caoxinghua.myapplication.okhttp.VisibleTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("Retrofit");
        bean.setJumpStr("com.example.caoxinghua.myapplication.retrofit.RetrofitActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("自定义瀑布流");
        bean.setJumpStr("com.example.caoxinghua.myapplication.defview.DefMainActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("GlideAndPicasso使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.glideAndPicasso.GlideAndPicassoActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("Constraint约束使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.okhttp.ConstraintActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("MediaRecoder使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.RecoderActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("录制video");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.TestRecordVideoActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("蓝牙使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.bluetooth.TestBlueToothActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("allowBackup测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.allowbackup.TestAllowBackupActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("NFC测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.nfc.TestNfcActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("Service测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.service.TestServiceActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("Activity测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.activity.First_Activity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("AIDL测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.aidl.TestAIDLActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("广播测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.receiver.TestReceiverActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("listview图片错位问题");
        bean.setJumpStr("com.example.caoxinghua.myapplication.listview.ListViewActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("WebP测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.webp.WebpActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("JNI测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.ndk.JniActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setName("AsyncTask测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.asynctask.AsyncTaskActivity");
        list.add(bean);
    }
    private void testDateFile(){
        File dir=this.getFilesDir();
        File file=new File(dir.getAbsolutePath()+"/test.txt");
        Log.i("xxx","path:"+file.getAbsolutePath()+"//"+this.getExternalCacheDir().getAbsolutePath());
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file);
            BufferedWriter bw=new BufferedWriter(fileWriter);
            bw.write("test shhhhhhh"+System.currentTimeMillis());
            bw.close();
            fileWriter.close();
        }catch (Exception e){

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            Debug.stopMethodTracing();
        }
    }
}
