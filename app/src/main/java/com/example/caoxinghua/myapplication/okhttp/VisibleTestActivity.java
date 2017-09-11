package com.example.caoxinghua.myapplication.okhttp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.caoxinghua.myapplication.R;
import com.example.caoxinghua.myapplication.hotfix.FixMainActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class VisibleTestActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible_test);

        final MyScrollView scrollView = (MyScrollView) findViewById(R.id.scrollView);
        final ImageView imageView = (ImageView) findViewById(R.id.iv);

        final ImageView imageView1 = (ImageView) findViewById(R.id.iv1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Point p = new Point();
                getWindowManager().getDefaultDisplay().getSize(p);
                int screenWidth = p.x;
                int screenHeight = p.y;
                Rect rect3 = new Rect(0, 0, screenWidth, screenHeight);
                Log.i("xxx", "w: " + screenWidth + "h: " + screenHeight);
                if (imageView1.getLocalVisibleRect(rect3)) {
                    Log.i("xxx", "imageView1可见");
                } else {
                    Log.i("xxx", "imageView1不可见");
                }
            }
        });
        final MyImageView imageView2 = (MyImageView) findViewById(R.id.iv2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rect = new Rect();
                imageView2.getGlobalVisibleRect(rect);
                Rect rect1 = new Rect();
                boolean ivIs = imageView.getGlobalVisibleRect(rect1);
                if (ivIs) {
                    Log.i("xxx", "vvvvimageView可见");
                } else {
                    Log.i("xxx", "vvvvimageView不可见");
                }
                Point p = new Point();
                getWindowManager().getDefaultDisplay().getSize(p);
                int screenWidth = p.x;
                int screenHeight = p.y;
                Rect rect3 = new Rect(0, 0, screenWidth, screenHeight);
                Log.i("xxx", "w: " + screenWidth + "h: " + screenHeight);
                if (imageView1.getLocalVisibleRect(rect3)) {
                    Log.i("xxx", "imageView1可见");
                } else {
                    Log.i("xxx", "imageView1不可见");
                }
                if (imageView2.getLocalVisibleRect(rect3)) {
                    Log.i("xxx", "imageView2可见");
                } else {
                    Log.i("xxx", "imageView2不可见");
                }
            }
        });
        MyImageView imageView3 = (MyImageView) findViewById(R.id.iv3);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisibleTestActivity.this, FixMainActivity.class);
                startActivity(intent);
            }
        });
        scrollView.addLisSet(imageView2.getListener());
        scrollView.addLisSet(imageView3.getListener());
        scrollView.setScrollChangeLitener(new MyScrollView.ScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                for (Object object : scrollView.getSet()) {
                    ((ImageListener) object).callBack();
                }
            }
        });

        final MyListView listView = (MyListView) findViewById(R.id.lv);
        List<String> list=new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add("第 "+(i+1)+"条记录");
        }
        MyAdapter adapter=new MyAdapter(this,list);
        listView.setAdapter(adapter);
        listView.addLisSet(imageView2.getListener());
        listView.addLisSet(imageView3.getListener());
        listView.setScrollChangeLitener(new MyListView.ScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
//                for (Object object : listView.getSet()) {
//                    ((ImageListener) object).callBack();
//                }
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (Object object : listView.getSet()) {
                    ((ImageListener) object).callBack();
                }
            }
        });

    }

    class MyAdapter extends BaseAdapter{
        private Context context;
        private List<String> list;
        public MyAdapter(Context context, List<String> list){
            this.context=context;
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                LinearLayout linearLayout=new LinearLayout(context);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.bottomMargin=10;
                params.topMargin=10;
                TextView textView=new TextView(context);
                linearLayout.addView(textView,params);
                textView.setTextColor(Color.BLACK);
                convertView=linearLayout;
                holder=new ViewHolder();
                holder.tv=textView;
                convertView.setTag(holder);
            }else {

                holder=(ViewHolder) convertView.getTag();
            }

            holder.tv.setText("content:     "+list.get(position));
            return convertView;
        }
        class ViewHolder{
            public TextView tv;
        }
    }
}
