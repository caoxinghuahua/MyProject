package com.example.caoxinghua.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MySencondReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null){
            String action=intent.getAction();
            Log.i("receiverS","action:"+action+" context:"+context);
        }
    }
}
