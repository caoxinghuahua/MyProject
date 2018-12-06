package com.example.caoxinghua.myapplication.thirdnav;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.caoxinghua.myapplication.R;

import java.util.List;

/**
 * @author caoxinghua on 2018/10/23
 * @email caoxinghua@gomeplus.com
 */
public class NavDialog extends Dialog {
    private ListView listView;
    private Context context;
    private List<String> list;
    private double slat=39.92848272;
    private double slon=116.39560823;
    private double dlat=39.98848272;
    private double dlng=116.47560823;
    public NavDialog(@NonNull Context context) {
        super(context);
        this.context=context;
        initView(context);
    }

    public NavDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
        initView(context);
    }
    private void initView(final Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View  root=inflater.inflate(R.layout.layout_dialog_listview,null);
        listView= (ListView) root.findViewById(R.id.lv);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=list.get(position);
                if(s.equals("com.autonavi.minimap")){
                    toGaodeNavi(context);
                }else if(s.equals("com.tencent.map")){
                    toTencent(context);
                }else if(s.equals("com.baidu.BaiduMap")){
                    toBaidu(context);
                }
            }
        });
        setContentView(root);

    }



    public NavDialog setList(List<String> list){
        this.list=list;
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        return this;
    }
    // 百度地图
    public void toBaidu(Context context){

        Intent intent= new Intent("android.intent.action.VIEW", android.net.Uri.parse("baidumap://map/direction?region=beijing&origin=name:对外经贸大学|latlng:39.98871,116.43234&destination=西直门&mode=driving&src=andr.baidu.openAPIdemo"));
        context.startActivity(intent);
    }
    // 高德地图
    public void toGaodeNavi(Context context){
        Intent intent= new Intent("android.intent.action.VIEW", android.net.Uri.parse("amapuri://route/plan/?sid=&slat=39.92848272&slon=116.39560823&sname=对外经贸大学&did=BGVIS2&dlat=39.98848272&dlon=116.47560823&dname=西直门&dev=0&t=0"));
        context.startActivity(intent);
    }
    // 腾讯地图
    public void toTencent(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=drive&from=对外经贸大学&fromcoord=39.92848272,116.39560823&to=西直门&tocoord=39.98848272,116.47560823&policy=0&referer=appName"));
        context.startActivity(intent);

    }
}
