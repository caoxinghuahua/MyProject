package com.example.caoxinghua.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gomeplus.meixin.ad.manger.MXAdsInstance;
import com.gomeplus.meixin.ad.view.MXAdsBannerView;

import java.util.ArrayList;
import java.util.List;

public class NewMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<JumpBean> list=new ArrayList<JumpBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        initView();

        initData();
        init();
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
    }
}
