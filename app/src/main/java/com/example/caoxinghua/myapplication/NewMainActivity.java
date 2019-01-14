package com.example.caoxinghua.myapplication;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.caoxinghua.myapplication.base.BaseViewHolder;
import com.example.caoxinghua.myapplication.base.CommonItemType;
import com.example.caoxinghua.myapplication.base.MultiBaseAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class NewMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<JumpBean> list=new ArrayList<JumpBean>();
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
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
        initLocation();
        startLocation();
        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String msg=appInfo.metaData.getString("UMENG_CHANNEL");
        Log.d("xxx", " msg == " + msg );
        //测试bugly接入是否成功
//        CrashReport.testJavaCrash();
    }
    private void initView(){

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,0,0,1);
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        
    }
    private void init(){
     /**  BaseRecyclerAdapter<JumpBean> adapter=new BaseRecyclerAdapter<JumpBean>(this,R.layout.layout_newmain_item,list) {
            @Override
            public void covert(BaseViewHolder holder, JumpBean bean) {
                holder.setText(R.id.content,bean.getName());
            }
        };*/
        MultiBaseAdapter<JumpBean> adapter=new MultiBaseAdapter<JumpBean>(this, list, new CommonItemType<JumpBean>() {
            @Override
            public int getItemViewType(int pos, JumpBean bean) {
                if(bean.getType()==0) {return 0;
                }else {
                    return 1;
                }
            }

            @Override
            public int getLayoutId(int type) {
                if(type==0) {
                    return R.layout.layout_newmain_item;
                }else {
                    return R.layout.layout_newmain_item2;
                }

            }
        }) {
            @Override
            public void covert(BaseViewHolder holder, JumpBean bean) {
                if(holder.getItemViewType()==0){
                    holder.setText(R.id.content,bean.getName());
                }else {
                    holder.setText(R.id.content2,bean.getName());
                }
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
              int type=list.get(position).getType();
                switch (type){
                    case 0:
                        String path=list.get(position).getJumpStr();
                        ARouter.getInstance().build(path).navigation(NewMainActivity.this);
                        break;
                    case 1:
                        Intent intent=new Intent();
                        intent.setComponent(new ComponentName("com.example.caoxinghua.myapplication",list.get(position).getJumpStr()));
                        startActivity(intent);
                        break;
                }
            }
        });
    }
    private void initData(){


        //test
        JumpBean bean=new JumpBean();
        bean.setType(1);
        bean.setName("全屏test fix");
        bean.setJumpStr("com.example.caoxinghua.myapplication.MainActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("MVVM 测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.MvvmTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("DrawLayout测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.DrawLayoutTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("RxJava测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.rxjava.TestRxJavaActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("OkHttp测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.okhttp.OkHttpTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("Video");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.TestVideo");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("SurfacePlayer");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.SurfacePlayer");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("Video");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.TestVideo");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("TestIjk");
        bean.setJumpStr("com.example.caoxinghua.myapplication.TestIjk");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("自定义keyboard");
        bean.setJumpStr("com.example.caoxinghua.myapplication.keyboard.KeydemoActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("HotFix热修复");
        bean.setJumpStr("com.example.caoxinghua.myapplication.hotfix.FixMainActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("可见曝光");
        bean.setJumpStr("com.example.caoxinghua.myapplication.okhttp.VisibleTestActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("Retrofit");
        bean.setJumpStr("com.example.caoxinghua.myapplication.retrofit.RetrofitActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("自定义瀑布流");
        bean.setJumpStr("com.example.caoxinghua.myapplication.defview.DefMainActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("GlideAndPicasso使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.glideAndPicasso.GlideAndPicassoActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("Constraint约束使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.okhttp.ConstraintActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("MediaRecoder使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.RecoderActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("录制video");
        bean.setJumpStr("com.example.caoxinghua.myapplication.video.TestRecordVideoActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("蓝牙使用");
        bean.setJumpStr("com.example.caoxinghua.myapplication.bluetooth.TestBlueToothActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("allowBackup测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.allowbackup.TestAllowBackupActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("NFC测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.nfc.TestNfcActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("Service测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.service.TestServiceActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("Activity测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.activity.First_Activity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("AIDL测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.aidl.TestAIDLActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("广播测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.receiver.TestReceiverActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("listview图片错位问题");
        bean.setJumpStr("com.example.caoxinghua.myapplication.listview.ListViewActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(0);
        bean.setName("WebP测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.webp.WebpActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("JNI测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.ndk.JniActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("AsyncTask测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.asynctask.AsyncTaskActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("anim测试");
        bean.setJumpStr("com.example.caoxinghua.myapplication.anim.TestAnimActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("视频sdk");
        bean.setJumpStr("com.example.caoxinghua.myapplication.sdk.VideoPlayerActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("listview嵌套scrollview点击事件处理");
        bean.setJumpStr("com.example.caoxinghua.myapplication.listview.ListViewActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("图片选择和上传");
        bean.setJumpStr("com.example.caoxinghua.myapplication.pick.PickActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("唤起第三方地图导航");
        bean.setJumpStr("com.example.caoxinghua.myapplication.thirdnav.NavActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(0);
        bean.setName("butterknife and dragger");
        bean.setJumpStr("/test/main");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("加载超大图片时分段加载");
        bean.setJumpStr("com.example.caoxinghua.myapplication.bitmap.LargeImageActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("事件分发处理");
        bean.setJumpStr("com.example.caoxinghua.myapplication.dispatch.DispatchActivity");
        list.add(bean);

        bean=new JumpBean();
        bean.setType(1);
        bean.setName("手势拖动");
        bean.setJumpStr("com.example.caoxinghua.myapplication.gesture.GestureActivity");
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



    /**
     * 初始化定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    Log.i("xxx","lat:"+location.getLatitude()+"lng:"+location.getLongitude());
                } else {
                    //定位失败

                }

            } else {
            }
        }
    };
    /**
     * 开始定位
     *
     */
    private void startLocation(){
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            Log.i("xxx","key back");
        }
        return super.onKeyDown(keyCode, event);
    }
}
