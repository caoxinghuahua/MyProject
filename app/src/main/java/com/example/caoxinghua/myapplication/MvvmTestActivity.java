package com.example.caoxinghua.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.databinding.MainMvvmBinding;

public class MvvmTestActivity extends AppCompatActivity {
    String iconUrl="http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainMvvmBinding binding= DataBindingUtil.setContentView(this, R.layout.main_mvvm);
        User user=new User("Wade","Li",iconUrl);
        binding.setUser(user);
        binding.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MvvmTestActivity.this,"asasa",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
