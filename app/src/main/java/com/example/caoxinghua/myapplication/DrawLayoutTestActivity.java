package com.example.caoxinghua.myapplication;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by caoxinghua on 2017/1/18.
 */

public class DrawLayoutTestActivity extends AppCompatActivity{
    private ListView listView;
    private DrawerLayout drawerLayout;
    private String[] menus;
    private TextView contentTv;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.layout_drawlayout);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawLayout);
        contentTv=(TextView)findViewById(R.id.tv);
        listView=(ListView)findViewById(R.id.lv);
        menus=getResources().getStringArray(R.array.simple_array);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.drawlayout_list_item,R.id.item_tv,menus);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contentTv.setText(menus[position]);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
//       drawerLayout.openDrawer(Gravity.LEFT);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_compose:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.action_delete:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.action_settings:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
