package com.example.caoxinghua.myapplication.hotfix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

import ad.gomeplus.com.mylibrary.MainActivity;
import ad.gomeplus.com.mylibrary.PluginTest;

public class FixMainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_main);
        Button fixBt=(Button) findViewById(R.id.fixBt);
        final PluginTest test=new PluginTest();
        fixBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FixMainActivity.this,test.sayHello(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FixMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
