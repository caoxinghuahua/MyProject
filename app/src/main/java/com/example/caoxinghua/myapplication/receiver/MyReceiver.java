package com.example.caoxinghua.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null){
           String action=intent.getAction();
            Log.i("receiver","action:"+action+" context:"+context);
            if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                Bundle bundle=intent.getExtras();
                Object[] objects= (Object[]) bundle.get("pdus");
                for(Object object:objects){
                    SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) object);
                    String sendNo=smsMessage.getOriginatingAddress();
                    String body=smsMessage.getMessageBody();
                    Log.i("receiver","sendNo:"+sendNo+"body:"+body);
                }
                abortBroadcast();//拦截广播
            }
        }
    }
}
