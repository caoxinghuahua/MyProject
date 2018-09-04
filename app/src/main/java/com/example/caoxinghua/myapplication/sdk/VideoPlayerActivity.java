package com.example.caoxinghua.myapplication.sdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.caoxinghua.myapplication.R;

import java.util.HashMap;
import java.util.Map;

import cn.com.gomeplus.player.listener.ExtPlayerListeners;
import cn.com.gomeplus.player.listener.PlayerListeners;
import cn.com.gomeplus.player.listener.PlayerListeners.OnCompletionListener;
import cn.com.gomeplus.player.presenter.PlayerPresenter;
import cn.com.gomeplus.player.widget.GomeplusPlayer;

public class VideoPlayerActivity extends AppCompatActivity implements ExtPlayerListeners.OnPlayerCompletionListener {
    private GomeplusPlayer gomeplusPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        gomeplusPlayer= (GomeplusPlayer) findViewById(R.id.player);
//        Map<String, Object> map = new HashMap<>();
//        map.put(GomeplusPlayer.ENVIRONMENT, 1);        //0:开发环境, 1:预生产环境, 2:生产环境
//        map.put(GomeplusPlayer.FULL_SCREEN, false);    //true:允许全屏, false:不允许全屏
//        map.put(GomeplusPlayer.AUTO_PLAY, false);         //true:自动播放, false:不自动播放
//        map.put(GomeplusPlayer.DANMU, false);                //true:开启弹幕功能, false:不开启弹幕功能
//        map.put(GomeplusPlayer.DANMUON, false);           //true:显示弹幕, false:隐藏弹幕
//
//        PlayerPresenter.getInstance().initPlayer(this, map);
        GomeplusPlayer.setPlayerEnvironment(1);
        gomeplusPlayer.play("4809",1);
        gomeplusPlayer.setCompletionListener(this);

    }


    @Override
    public void onPlayerCompletion(String s, int i) {
        Log.i("VideoPlayerActivity",s);
        Log.i("VideoPlayerActivity",s);
    }
}
