package com.example.caoxinghua.myapplication.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author caoxinghua on 2018/9/5
 * @email caoxinghua@gomeplus.com
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    public BaseViewHolder(View itemView) {
        super(itemView);
        views=new SparseArray<>();
    }
    private <T extends View> T getView(int id){
        View view=views.get(id);
        if(view==null){
            view=itemView.findViewById(id);
            views.append(id,view);
        }

        return (T) view;
    }
    public BaseViewHolder setText(int id,String s){
        TextView view= (TextView) getView(id);
        view.setText(s);
        return this;
    }
    public BaseViewHolder setImage(int id,int imgId){
        ImageView view= (ImageView) getView(id);
        view.setImageResource(imgId);
        return this;
    }
    public BaseViewHolder setOnClickListener(int id, View.OnClickListener listener){
        View view=getView(id);
        view.setOnClickListener(listener);
        return this;
    }
}
