package com.example.caoxinghua.myapplication.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.appwidget.AppWidgetProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Scroller;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class First_Activity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG=getClass().getSimpleName();
    private Button switch2Bt,switch3Bt;
    private TextView saveTv;
    private int num;
    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_first);
        initView();
        Log.i(TAG,"onCreate()");
		//test
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
           new Thread(){
               @Override
               public void run() {
//                  readContact();
               }
           }.start();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},100);
        }
       final Handler handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("xxx"," main what："+msg.what);

            }
        };
         
        A a=new A();
        a.start();
        Handler handler1=new Handler(a.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("xxx","thread what："+msg.what);
                handler.sendEmptyMessage(1);
            }
        };
        handler1.sendEmptyMessage(2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"onConfigurationChanged()");

    }

    @Override
    protected void onResume() {
        super.onResume();
//        saveTv.setText("num:"+num);
        Log.i(TAG,"onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("num",num);
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState()");


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        num=savedInstanceState.getInt("num");
        Log.i(TAG,"onRestoreInstanceState()");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG,"onNewIntent()");
    }

    private void initView(){
        switch2Bt= (Button) findViewById(R.id.switchTo2_bt);
        switch2Bt.setOnClickListener(this);
        saveTv= (TextView) findViewById(R.id.saveTv);
        switch3Bt= (Button) findViewById(R.id.switchTo3_bt);
        switch3Bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.switchTo2_bt:
                num++;
                saveTv.setText("num:"+num);
                intent.setClass(this,SecondActivity.class);
                startActivity(intent);
//                myView.invalidate();
                break;
            case R.id.switchTo3_bt:
//                intent.setClass(this,ThirdActivity.class);
                intent.setClass(this,TestPercentSupport.class);
                startActivity(intent);
                break;
        }
    }
    public void readContact(){
        //获取联系人信息的Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //获取ContentResolver
        ContentResolver contentResolver =this.getContentResolver();
        //查询数据，返回Cursor
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        while(cursor.moveToNext())
        {
            Map<String,Object> map = new HashMap<String,Object>();
            StringBuilder sb = new StringBuilder();
            //获取联系人的ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获取联系人的姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            //构造联系人信息
            sb.append("contactId=").append(contactId).append(",Name=").append(name);
            map.put("name", name);
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));//联系人ID


            //查询电话类型的数据操作
            Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,
                    null, null);
            while(phones.moveToNext())
            {
                String phoneNumber = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                //添加Phone的信息
                sb.append(",Phone=").append(phoneNumber);
                map.put("mobile", phoneNumber);
            }
            phones.close();


            //查询Email类型的数据操作
            Cursor emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null, null);
            while (emails.moveToNext())
            {
                String emailAddress = emails.getString(emails.getColumnIndex(
                        ContactsContract.CommonDataKinds.Email.DATA));
                //添加Email的信息
                sb.append(",Email=").append(emailAddress);
                Log.e("emailAddress", emailAddress);
                map.put("email", emailAddress);


            }
            emails.close();
            //Log.i("=========ddddddddddd=====", sb.toString());

            //查询==地址==类型的数据操作.StructuredPostal.TYPE_WORK
            Cursor address = contentResolver.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = " + contactId,
                    null, null);
            while (address.moveToNext())
            {
                String workAddress = address.getString(address.getColumnIndex(
                        ContactsContract.CommonDataKinds.StructuredPostal.DATA));


                //添加Email的信息
                sb.append(",address").append(workAddress);
                map.put("address", workAddress);
            }
            address.close();
            //Log.i("=========ddddddddddd=====", sb.toString());

            //查询==公司名字==类型的数据操作.Organization.COMPANY  ContactsContract.Data.CONTENT_URI
            String orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
            String[] orgWhereParams = new String[]{id,
                    ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
            Cursor orgCur = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                    null, orgWhere, orgWhereParams, null);
            if (orgCur.moveToFirst()) {
                //组织名 (公司名字)
                String company = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
                //职位
                String title = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
                sb.append(",company").append(company);
                sb.append(",title").append(title);
                map.put("company", company);
                map.put("title", title);
            }
            orgCur.close();
            list.add(map);
            Log.i("=========orgName=====", sb.toString());//查看所有的数据
            Log.e("=========map=====", map.toString());//有很多数据的时候，只会添加一条  例如邮箱，
        }

        Log.i("=========list=====", list.toString());//
        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                new Thread(){
                    @Override
                    public void run() {
//                        readContact();
                    }
                }.start();
            }
        }
    }
}
