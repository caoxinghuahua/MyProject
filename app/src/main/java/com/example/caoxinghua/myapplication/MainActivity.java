package com.example.caoxinghua.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.gomeplus.meixin.ad.bean.AdBean;

import java.util.ArrayList;


public class MainActivity extends Activity {
    String json="";
    RelativeLayout mLayout;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        mLayout=(RelativeLayout) findViewById(R.id.layout);
        MxBannerAd mxBannerAd=new MxBannerAd(this,"10017",MxBannerAd.SIZE_FULL_FLEXIBLE);
        mLayout.addView(mxBannerAd);
        mxBannerAd.setListener(new AdBaseListener() {
            @Override
            public void onAdDataLoadSuccess() {
                Log.i("xxx","loadSuccess");
            }

            @Override
            public void onAdDataLoadFail() {
                Log.i("xxx","loadFail");
            }

            @Override
            public void onAdShow() {
                Log.i("xxx","show");
            }

            @Override
            public void onAdClick() {
                Log.i("xxx","click");
            }
        });
//       new MxNetManger().sendRequest();
//        new MxInterstitialView(this, new MxInterstitialView.DialogListener() {
//            @Override
//            public void dialogViewDidClose() {
//
//            }
//
//            @Override
//            public void dialogViewDidShowPage(int var1) {
//
//            }
//        }).show();
        RelativeLayout relativeLayout=new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        popupWindow=new PopupWindow(relativeLayout,1080,1920,true);
        ImageView imageView=new ImageView(this);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy").into(imageView);
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        AQuery aQuery = new AQuery(mContext);
//        aQuery.id(imageView).image("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy", true, true);
        params.addRule(RelativeLayout.CENTER_IN_PARENT,-1);
        relativeLayout.addView(imageView,params);
        ImageButton imageButton=new ImageButton(this);
        RelativeLayout.LayoutParams paramsBt=new RelativeLayout.LayoutParams(200,100);

        paramsBt.rightMargin=50;
        paramsBt.topMargin=50;
        imageButton.setImageResource(R.mipmap.ic_launcher);
        paramsBt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,-1);
        relativeLayout.addView(imageButton,paramsBt);

            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha =0.5f; //0.0-1.0
            getWindow().setAttributes(lp);

//        popupWindow.setContentView(relativeLayout);
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            popupWindow.showAtLocation(findViewById(R.id.layout2), Gravity.CENTER,10,10);
        }
    }
    private void testHandler(){
        Handler handler=new Handler();
        ListView listView=new ListView(MainActivity.this);

    }
}
