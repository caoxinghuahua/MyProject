package com.example.caoxinghua.myapplication.receiver;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;

import com.example.caoxinghua.myapplication.R;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestReceiverActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sendAllBt,sendSMSBt;
    private MyReceiver receiver;
    LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_receiver);
        initView();
//        registerReceiver();
    }
    private void initView(){
        sendAllBt= (Button) findViewById(R.id.sendAllBt);
        sendSMSBt= (Button) findViewById(R.id.sendSMSBt);
        sendAllBt.setOnClickListener(this);
        sendSMSBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendAllBt:
                Intent intent=new Intent();
                intent.setAction("test.receiver");
                sendOrderedBroadcast(intent,"permission.test");
                break;
            case R.id.sendSMSBt:
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendMsg();
                }else {
                    ActivityCompat.requestPermissions(TestReceiverActivity.this, new String[]{Manifest.permission.SEND_SMS},0);
                }
                break;
        }
    }
    private void registerReceiver(){
        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("test.receiver");

        localBroadcastManager=LocalBroadcastManager.getInstance(this);

        localBroadcastManager.registerReceiver(receiver,filter);
    }
    private void unRegisterReceiver(){
        localBroadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unRegisterReceiver();
    }
    private void sendMsg(){
        SmsManager smsManager=SmsManager.getDefault();
        List<String> dividMsg= null;
        try {
            dividMsg = smsManager.divideMessage(new String("调用系统发送 sms".toString().getBytes("iso-8859-1"),"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for(String  msg:dividMsg){
            smsManager.sendTextMessage("5554",null,msg,null,null);
        }
      /** Intent intent=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+5554));
       intent.putExtra("sms_body","调用系统发送 sms");
       startActivity(intent);*/
    }
}
