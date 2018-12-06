package com.example.caoxinghua.myapplication.listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.caoxinghua.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends Activity {
    private List<String> list=new ArrayList<String>();
    private SlideAdapter adapter;
    private SlideListView listView;
    private String []imgUrls={"http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760756_3304.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760755_6715.jpeg",
            "http://img.my.csdn.net/uploads/201508/05/1438760726_5120.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760726_8364.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760725_4031.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760724_9463.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760724_2371.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760707_4653.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760706_6864.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760706_9279.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760704_2341.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760704_5707.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760685_5091.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760685_4444.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760684_8827.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760683_3691.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760683_7315.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760663_7318.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760662_3454.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760662_5113.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760661_3305.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760661_7416.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760589_2946.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760589_1100.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760588_8297.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760587_2575.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760587_8906.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760550_2875.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760550_9517.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760549_7093.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760549_1352.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760548_2780.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760531_1776.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760531_1380.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760530_4944.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760530_5750.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760529_3289.jpg",
            "http://img.my.csdn.net/uploads/201508/05/1438760500_7871.jpg"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);
        initView();
    }
    private void initView(){
        listView= (SlideListView) findViewById(R.id.listView);
        for(int i=0;i<10;i++){
            list.add(imgUrls[i]);
        }
//        adapter=new MyAdapter(this,list);
        adapter=new SlideAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE://停止滑动
                        Glide.with(ListViewActivity.this).resumeRequests();
                        break;
                    case SCROLL_STATE_FLING://惯性滑动
                        Glide.with(ListViewActivity.this).pauseRequests();
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL://触摸滑动
                        Glide.with(ListViewActivity.this).pauseRequests();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

}
