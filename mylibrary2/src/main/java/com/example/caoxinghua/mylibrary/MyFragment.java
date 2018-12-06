package com.example.caoxinghua.mylibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author caoxinghua on 2018/11/12
 * @email caoxinghua@gomeplus.com
 */
public class MyFragment extends Fragment {
    private Context context;
    private View rootView;
    public MyFragment(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=LayoutInflater.from(context).inflate(R.layout.layout_fragment,null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



}
