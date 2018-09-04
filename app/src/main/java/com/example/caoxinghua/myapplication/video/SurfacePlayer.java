package com.example.caoxinghua.myapplication.video;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;
import com.example.caoxinghua.myapplication.util.Utils;

import java.io.IOException;

public class SurfacePlayer extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnSeekCompleteListener, View.OnClickListener{
    private SurfaceHolder holder;
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/kuaishou/vod/1485313133979/chapter1.mp4";
    private int vWidth, vHeight;
    private Display display;
    private SeekBar seekBar;
    private ImageButton startBt;
    private ImageButton fullScreenBt;
    private int currentPos;
    private MyThread myThread;
    private TextView overTimeTv;
    private TextView allTimeTv;
    private boolean fullScreen=false;
    private int screenW;
    private int screenH;
    private RelativeLayout video_parent_rl;
    private String netUrl="http://gvsout.pre.video.api/video/-7SK-QmF9TzsyYkp1PxsM-416zJ1ie0RR5ClwyrvEMk.m3u8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_surface_player);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        startBt = (ImageButton) findViewById(R.id.startBt);
        overTimeTv=(TextView) findViewById(R.id.over_time_tv);
        allTimeTv=(TextView) findViewById(R.id.all_time_tv);
        fullScreenBt=(ImageButton) findViewById(R.id.switch_screen_Bt);
        video_parent_rl=(RelativeLayout) findViewById(R.id.video_parent_rl);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {

                }
            });
            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                    return;
                }

            }
            mediaPlayer.setDataSource(netUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                overTimeTv.setText(Utils.formatTime(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(progress);
                }
            }
        });
        startBt.setOnClickListener(this);
        fullScreenBt.setOnClickListener(this);
        display = getWindowManager().getDefaultDisplay();
        screenW=display.getWidth();
        screenH=display.getHeight();
        myThread=new MyThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
        mediaPlayer.prepareAsync();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 当prepare完成后，该方法触发，在这里我们播放视频

        //首先取得video的宽和高
        vWidth = video_parent_rl.getWidth();
        vHeight =video_parent_rl.getHeight();

//        if (vWidth > screenW || vHeight > screenH) {
//            //如果video的宽或者高超出了当前屏幕的大小，则要进行缩放
//            float wRatio = (float) vWidth / (float) screenW;
//            float hRatio = (float) vHeight / (float) screenH;
//
//            //选择大的一个进行缩放
//            float ratio = Math.max(wRatio, hRatio);
//
//            vWidth = (int) Math.ceil((float) vWidth / ratio);
//            vHeight = (int) Math.ceil((float) vHeight / ratio);
//
//            //设置surfaceView的布局参数
////            surfaceView.setLayoutParams(new LinearLayoutCompat.LayoutParams(vWidth, vHeight));
//        }
        int duration=mediaPlayer.getDuration();
        seekBar.setMax(duration);
        allTimeTv.setText(Utils.formatTime(duration));
        currentPos = mp.getCurrentPosition();
        overTimeTv.setText(Utils.formatTime(currentPos));
        seekBar.setProgress(currentPos);
        //然后开始播放视频
        mp.start();
        myThread.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(this, "播放完毕", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }


    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBt:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    startBt.setImageResource(R.mipmap.pausing_small);
                }else{
                    mediaPlayer.start();
                    startBt.setImageResource(R.mipmap.playing_small);
                }
                break;
            case R.id.switch_screen_Bt:
                if(fullScreen){
                    fullScreen=false;
                    fullScreenBt.setImageResource(R.mipmap.fulling_screen_button);
                    setFullScreen(fullScreen);
                }else {
                    fullScreen=true;
                    fullScreenBt.setImageResource(R.mipmap.shrink);
                    setFullScreen(fullScreen);
                }

                break;
        }
    }
    class MyThread extends Thread{
            public void run() {
                while (true) {
                    Log.i("xxx","ccc");
                    if(!mediaPlayer.isPlaying()){
                        return;
                    }
                    final  int position = mediaPlayer.getCurrentPosition();
                    Log.i("xxx","ccc"+position);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(position);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

            video_parent_rl.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }else {

            video_parent_rl.setLayoutParams(new FrameLayout.LayoutParams(vWidth,vHeight));
        }
    }
    private void setFullScreen(boolean fullScreen){
      if(fullScreen){
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
          video_parent_rl.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      }else {
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          video_parent_rl.setLayoutParams(new FrameLayout.LayoutParams(vWidth,vHeight));
      }

    }
}
