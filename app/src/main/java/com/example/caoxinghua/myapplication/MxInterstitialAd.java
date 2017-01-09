package com.example.caoxinghua.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.gomeplus.meixin.ad.listener.AdDataListener;
import com.gomeplus.meixin.ad.manger.MXAdsController;

/**
 * Created by caoxinghua on 2016/11/3.
 */

public class MxInterstitialAd {
    private MxInterstitialListner mListner;
    private Context mContext;
    private String mSlotId;
    private int count;
    private boolean isSuccess=false;
    public void MxInterstitialListner(Context context,String slotId){
       mContext=context;
       mSlotId=slotId;
    }
    public void setListner(MxInterstitialListner listner){
        mListner=listner;
    }
    public void loadAd(){

    }

    public void showAd(){

    }
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            MXAdsController controller = new MXAdsController(mContext);
            controller.getAdData(mSlotId, new AdDataListener() {
                @Override
                public void success(String s) {

                }


                public void fail(String error) {
                    Log.i("xxx", "error:" + error);
                    mListner.onAdDataLoadFail();
                }
            });
        }
    };
}
