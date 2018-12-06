package com.example.caoxinghua.mylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.ButterKnife;

/**
 * @author caoxinghua on 2018/11/12
 * @email caoxinghua@gomeplus.com
 */
@Route(path="/test/main")
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(this);
    }
}
