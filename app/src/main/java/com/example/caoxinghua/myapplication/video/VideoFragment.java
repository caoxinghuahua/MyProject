package com.example.caoxinghua.myapplication.video;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.caoxinghua.myapplication.R;

/**
 * Created by caoxinghua on 2017/2/17.
 */

public class VideoFragment extends Fragment {
    private VideoView videoView;
    private String pathStr = "/kuaishou/vod/1485313133979/chapter1.mp4";

    public VideoFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_video, null);
        videoView = (VideoView) view.findViewById(R.id.videoView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        videoView.setMediaController(new MediaController(getActivity()));
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        System.out.print("path:\n" + path);
        Uri uri = Uri.parse(path + pathStr);
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                return;
            }

        }

        videoView.setVideoURI(uri);
        int current = 0;
        if (savedInstanceState != null) {
            current = savedInstanceState.getInt("current");
            videoView.seekTo(current);
        }

        videoView.start();
        videoView.requestFocus();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", videoView.getCurrentPosition());
    }
}
