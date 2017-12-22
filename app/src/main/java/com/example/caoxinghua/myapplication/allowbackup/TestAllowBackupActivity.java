package com.example.caoxinghua.myapplication.allowbackup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;


public class TestAllowBackupActivity extends AppCompatActivity {
    private AppCompatButton loginBt;
    private AppCompatEditText nameEt;
    private AppCompatEditText passwordEt;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String shareName="allowBackup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_allowbackup);
        initView();
    }
    private void initView(){
        sharedPreferences=getSharedPreferences(shareName, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        loginBt= (AppCompatButton) findViewById(R.id.loginBt);
        nameEt=(AppCompatEditText) findViewById(R.id.nameEt);
        passwordEt=(AppCompatEditText) findViewById(R.id.passwordEt);
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEt.getText().toString();
                String password=passwordEt.getText().toString();
                String shareName=sharedPreferences.getString("name","hua");
                String sharePwd=sharedPreferences.getString("password","111111");
                if(name.equals(shareName)&&password.equals(sharePwd)){
                    Toast.makeText(TestAllowBackupActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString("name",name);
                    editor.putString("password",password);
                    editor.commit();
                }
            }
        });
    }
}
