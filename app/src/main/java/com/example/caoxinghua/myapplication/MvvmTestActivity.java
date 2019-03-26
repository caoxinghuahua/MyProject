package com.example.caoxinghua.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.adapter.MyAdapter;
import com.example.caoxinghua.myapplication.databinding.MainMvvmBinding;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MvvmTestActivity extends AppCompatActivity {
    String iconUrl="http://pic5.zhongsou.com/img?id=522d689a39870aaf6f7!sy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        testListView();
    }
    private void test(){
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
    private void testListView(){
        setContentView(R.layout.main_listview);
        ListView listView=(ListView) findViewById(R.id.lv);
        List<Food> list=new ArrayList<Food>();
        for(int i=0;i<10;i++){
            Food food=new Food("food"+i,iconUrl,"so good...",i);
            list.add(food);
        }
        MyAdapter adapter=new MyAdapter(this,R.layout.listview_item,com.example.caoxinghua.myapplication.BR.food,list);
        listView.setAdapter(adapter);
        try {
            DexClassLoader.getSystemClassLoader().loadClass("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
