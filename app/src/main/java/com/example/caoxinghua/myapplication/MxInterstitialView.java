package com.example.caoxinghua.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.bumptech.glide.Glide;

/**
 * Created by caoxinghua on 2016/11/9.
 */

public class MxInterstitialView extends Dialog {
    private Context mContext;
    private DialogListener mListener;

    public MxInterstitialView(Context context,DialogListener listener) {
        super(context);
        mContext=context;
        mListener=listener;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setOwnerActivity((Activity) mContext);
        setCanceledOnTouchOutside(true);
        init();

    }
    private void init(){
        RelativeLayout relativeLayout=new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        ImageView imageView=new ImageView(mContext);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy").into(imageView);
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        AQuery aQuery = new AQuery(mContext);
//        aQuery.id(imageView).image("http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy", true, true);
        relativeLayout.addView(imageView,params);
        ImageButton imageButton=new ImageButton(mContext);
        RelativeLayout.LayoutParams paramsBt=new RelativeLayout.LayoutParams(200,100);

        paramsBt.rightMargin=50;
        paramsBt.topMargin=50;
        imageButton.setImageResource(R.mipmap.ic_launcher);
        paramsBt.addRule(RelativeLayout.ALIGN_RIGHT|RelativeLayout.ALIGN_RIGHT,-1);
        relativeLayout.addView(imageButton,paramsBt);
        this.setContentView(relativeLayout);
    }

    public interface DialogListener {
        void dialogViewDidClose();

        void dialogViewDidShowPage(int var1);
    }

}
