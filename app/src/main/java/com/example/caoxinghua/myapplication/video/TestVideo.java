package com.example.caoxinghua.myapplication.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.caoxinghua.myapplication.R;

public class TestVideo extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button localPlayBt;
    private Button videoPlayBt;
    private Button surfacePlayBt;
    private DrawerLayout drawerLayout;
    private FrameLayout content;
    private FragmentManager fragmentManager;
    private VideoFragment videoFragment;
    private NavigationView navigationView;
    private final String TAG = "TestVideo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_test_video);
        initView();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.i(TAG, this.toString());
        if(fragmentManager.findFragmentByTag("video")!=null){
            videoFragment=(VideoFragment) fragmentManager.findFragmentByTag("video");
        }else {
            videoFragment =new VideoFragment();
        }

        videoFragment.setRetainInstance(true);
        transaction.add(R.id.frame, videoFragment,"video");
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.video_drawlayout);
        toolbar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        content = (FrameLayout) findViewById(R.id.frame);
        localPlayBt = (Button) findViewById(R.id.local_play);
        videoPlayBt = (Button) findViewById(R.id.video_play);
        surfacePlayBt = (Button) findViewById(R.id.surface_play);
        localPlayBt.setOnClickListener(this);
        videoPlayBt.setOnClickListener(this);
        surfacePlayBt.setOnClickListener(this);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_local:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/kuaishou/vod/1485313133979/chapter1.mp4");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri, "video/mp4");
                        startActivity(intent);
                        break;
                    case R.id.menu_video:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.frame, videoFragment);
                        transaction.commit();
                        break;
                    case R.id.menu_surface:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent1=new Intent(TestVideo.this,SurfacePlayer.class);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local_play:
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/kuaishou/vod/1485313133979/chapter1.mp4");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "video/mp4");
                startActivity(intent);
                break;
            case R.id.video_play:
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame, videoFragment);
                transaction.commit();
                break;
            case R.id.surface_play:
                Intent intent1=new Intent(TestVideo.this,SurfacePlayer.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


}