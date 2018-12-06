package com.example.caoxinghua.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caoxinghua.myapplication.OnItemClickListener;

import java.util.List;

/**
 * @author caoxinghua on 2018/9/5
 * @email caoxinghua@gomeplus.com
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{
    private Context context;
    private List<T> data;
    private int layoutId;
    private OnItemClickListener listener;
    public BaseRecyclerAdapter(Context context,int layoutId, List<T> data){
        this.context=context;
        this.layoutId=layoutId;
        this.data=data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layoutId,parent,false);
        BaseViewHolder baseViewHolder=new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(v,position);
                }
            }
        });
        covert(holder,data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public abstract void covert(BaseViewHolder holder, T t);
}
