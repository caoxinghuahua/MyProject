package com.example.caoxinghua.myapplication.okhttp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;


import com.example.caoxinghua.myapplication.R;

public class ConstraintActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        initView();
    }
    private void initView(){
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);

    }
}

