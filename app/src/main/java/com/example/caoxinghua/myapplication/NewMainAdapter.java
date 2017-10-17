package com.example.caoxinghua.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class NewMainAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    private List<JumpBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    public NewMainAdapter(Context context,List<JumpBean> list){
        this.list=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view=inflater.inflate(R.layout.layout_newmain_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JumpBean bean=list.get(position);
        if(holder instanceof MyViewHolder){
            ((MyViewHolder) holder).textView.setText(bean.getName());
            holder.itemView.setOnClickListener(this);
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.content);
        }
    }
}

