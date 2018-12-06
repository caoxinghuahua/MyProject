package com.example.caoxinghua.myapplication.thirdnav;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;
import com.example.caoxinghua.myapplication.widget.media.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NavActivity extends BaseActivity implements View.OnClickListener{
    private Button gotoMap_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        initView();
        initListener();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gotoMapBt:
                createDialog();
                Toast.makeText(this,"super click",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        gotoMap_bt= (Button) findViewById(R.id.gotoMapBt);
    }

    @Override
    protected void initListener() {
        super.initListener();
        gotoMap_bt.setOnClickListener(this);
    }
    private void createDialog(){
        List<String> list=hasMap(this);
        NavDialog dialog=new NavDialog(this).setList(list);
        dialog.show();
    }

   /** 高德地图包名：com.autonavi.minimap

    腾讯地图包名：com.tencent.map

    谷歌地图 com.google.android.apps.maps
    百度地图  com.baidu.BaiduMap

         */
    public List<String> mapsList (){
        List<String> maps = new ArrayList<>();
        maps.add("com.baidu.BaiduMap");
        maps.add("com.autonavi.minimap");
        maps.add("com.tencent.map");
        return maps;
    }

    // 检索筛选后返回
    public List<String> hasMap (Context context){
        List<String> mapsList = mapsList();
        List<String> backList = new ArrayList<>();
        for (int i = 0; i < mapsList.size(); i++) {
            boolean avilible = isAvilible(context, mapsList.get(i));
            if (avilible){
                backList.add(mapsList.get(i));
            }
        }
        return backList;


    }
    // 检索地图软件
    public static boolean isAvilible(Context context, String packageName){
//获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
//获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
//用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
//从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
//判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


}
