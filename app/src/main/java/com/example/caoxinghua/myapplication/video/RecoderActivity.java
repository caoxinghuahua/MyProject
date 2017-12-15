package com.example.caoxinghua.myapplication.video;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.IOException;

public class RecoderActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    private static final String TAG=RecoderActivity.class.getSimpleName();
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String mFileName = null;

    private static String mVideoName=null;
    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private PlayButton   mPlayButton = null;
    private MediaPlayer mPlayer = null;

    private RecordVideo mRecordVideo=null;

    private PlayVideo mPlayVideo=null;

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    private SurfaceView playSurfaceView=null;
    private SurfaceHolder playSurfaceHolder=null;

    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private MediaPlayer mediaPlayer;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
        mVideoName = getExternalCacheDir().getAbsolutePath();
        mVideoName += "/videotest.mp4";
        ActivityCompat.requestPermissions(this,permissions,REQUEST_RECORD_AUDIO_PERMISSION);
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        mRecordButton=new RecordButton(this);
        mPlayButton=new PlayButton(this);
        mRecordVideo=new RecordVideo(this);
        mPlayVideo=new PlayVideo(this);
        mSurfaceView=new SurfaceView(this);
        mSurfaceHolder=mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        playSurfaceView=new SurfaceView(this);
        playSurfaceHolder=playSurfaceView.getHolder();

        ll.addView(mRecordButton,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0));
        ll.addView(mPlayButton,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0));
        ll.addView(mRecordVideo,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0));
        ll.addView(mPlayVideo,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0));
        ll.addView(mSurfaceView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 700,0));
        ll.addView(playSurfaceView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 700,0));
        setContentView(ll);


    }
    private void onRecord(boolean recording){
        if(recording){
            record();
        }else {
            stopRecord();
        }
    }
    private void onPlay(boolean playing){
        if(playing){
            play();
        }else {
            stopPlay();
        }
    }
    private void play(){
        mPlayer=new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();

    }
    private void stopPlay(){
        mPlayer.stop();
        mPlayer.release();
        mPlayer=null;
    }
    private void record(){
        mRecorder=new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void stopRecord(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder=null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder=holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceHolder=holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceHolder=null;
        mMediaRecorder=null;
    }

    class RecordButton extends AppCompatButton {
        private boolean recording=true;
        public RecordButton(Context context){
            super(context);
            setText("start recording");
            setOnClickListener(clickListener);
        }
        OnClickListener clickListener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(recording);
                if(recording){
                    setText("stop recording");
                }else {
                    setText("start recording");
                }
                recording=!recording;
            }
        };
    };
    class PlayButton extends AppCompatButton{
        private boolean playing=true;
        public PlayButton(Context context){
            super(context);
            setText("start playing");
            setOnClickListener(clickListener);
        }
        OnClickListener clickListener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(playing);
                if(playing){
                    setText("stop playing");
                }else {
                    setText("start playing");
                }
                playing=!playing;
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mRecorder!=null){
            mRecorder.release();
            mRecorder=null;
        }
        if(mPlayer!=null){
            mPlayer.release();
            mPlayer=null;
        }

    }
    private void recordVideo(){
         mMediaRecorder = new MediaRecorder();
         mCamera=Camera.open();
         Camera.Parameters parameters=mCamera.getParameters();
         parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
         mCamera.setParameters(parameters);
         mCamera.setDisplayOrientation(90);//录制时显示角度
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
           e.printStackTrace();
       }
        mCamera.startPreview();
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
          mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);// 视频源
//          mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 音频源
          mMediaRecorder.setOrientationHint(90);// 输出旋转90度

          mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);// 视频输出格式
//          mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频格式
          mMediaRecorder.setVideoFrameRate(20);// 这个值>16
//          mMediaRecorder.setVideoEncodingBitRate(1 * 1024 * 512);// 设置帧频率，然后就清晰了
          mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);// 视频录制格式与手机有关，有些手机不支持某些格式
          mMediaRecorder.setVideoSize(176, 144);// 设置分辨率：
          mMediaRecorder.setMaxDuration(30 * 1000);
          mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
          mMediaRecorder.setOutputFile(mVideoName);
         try {
              mMediaRecorder.prepare();
              mMediaRecorder.start();
          } catch (IllegalStateException e) {
              e.printStackTrace();
          } catch (RuntimeException e) {
              e.printStackTrace();
          } catch (Exception e) {
              e.printStackTrace();
          }
   /**     mMediaRecorder = new MediaRecorder();// 创建mediarecorder对象
        // 设置录制视频源为Camera(相机)
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
        mMediaRecorder
                .setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setOrientationHint(90);
        // 设置录制的视频编码h263 h264
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        // 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
        mMediaRecorder.setVideoSize(176, 144);
        // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
        mMediaRecorder.setVideoFrameRate(20);
        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        // 设置视频文件输出的路径
        mMediaRecorder.setOutputFile(mVideoName);
        try {
            // 准备录制
            mMediaRecorder.prepare();
            // 开始录制
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
    private void stopRecordVideo(){
        if(mMediaRecorder!=null){
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder=null;
        }
        mCamera.setPreviewCallback(null);
       mCamera.stopPreview();
       mCamera.lock();
       mCamera.release();
        mCamera = null;
    }
    private void onRecordVideo(boolean recording){
        if(recording){
            recordVideo();
        }else {
            stopRecordVideo();
        }
    }
    class RecordVideo extends AppCompatButton{
        private boolean recording=true;
        public RecordVideo(Context context){
            super(context);
            setText("start record video");
            setOnClickListener(clickListener);
        }
        OnClickListener clickListener=new OnClickListener() {
            @Override
            public void onClick(View v) {
               onRecordVideo(recording);
                if(recording){
                    setText("stop record video");
                }else {
                    setText("start record video");
                }
                recording=!recording;
            }
        };
    }
    private  void onPlayVideo(boolean playing){
        if(playing){
            playVideo();
        }else {
            stopPlayVideo();
        }
    }
    private void playVideo(){
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mVideoName);

            mediaPlayer.setDisplay(playSurfaceHolder);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
    private void stopPlayVideo(){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
    }
    class PlayVideo extends AppCompatButton{
        private boolean playing=true;
        public PlayVideo(Context context){
            super(context);
            setText("start play video");
            setOnClickListener(clickListener);
        }
        OnClickListener clickListener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayVideo(playing);
                if(playing){
                    setText("stop play video");
                }else {
                    setText("start play video");
                }
                playing=!playing;
            }
        };

    }
}
