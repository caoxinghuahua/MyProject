package com.example.caoxinghua.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.caoxinghua.myapplication.widget.media.AndroidMediaController;
import com.example.caoxinghua.myapplication.widget.media.IRenderView;
import com.example.caoxinghua.myapplication.widget.media.IjkVideoView;

public class TestIjk extends AppCompatActivity {
    private IjkVideoView videoView;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/kuaishou/vod/1485313133979/chapter1.mp4";
    private String url="http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private String url1="https://api-v-pre.gomeplus.com/video/fDFlWMbiRsEMjef2VWTVzBlNIZxncHZwKgMsjcE1MQ4.m3u8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ijk);
        videoView = (IjkVideoView) findViewById(R.id.video_view);
        videoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        videoView.setVideoURI(Uri.parse(url1));
        videoView.setMediaController(new AndroidMediaController(this));
        videoView.start();
    }

}
