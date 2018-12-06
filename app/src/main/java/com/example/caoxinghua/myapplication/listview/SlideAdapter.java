package com.example.caoxinghua.myapplication.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.caoxinghua.myapplication.R;

import java.util.List;

public class SlideAdapter extends BaseAdapter implements View.OnClickListener{

    private List<String> dataList;
    private Context context;
    private LayoutInflater inflater;
    public SlideAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            View content=inflater.inflate(R.layout.layout_adapter_item_content,null);
            View menu=inflater.inflate(R.layout.layout_adapter_item_menu,null);
            holder=new ViewHolder(content,menu);
            SlideItemView slideItemView=new SlideItemView(context);
            slideItemView.setContentView(content,menu);
            convertView=slideItemView;
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(dataList.get(position)).into(holder.imageView);

        holder.itemTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context,"删除啦",Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView itemTvDelete;

        public ViewHolder(View center,View menu) {
            imageView= (ImageView) center.findViewById(R.id.pic_iv);
            this.itemTvDelete = (TextView) menu.findViewById(R.id.item_delete_bt);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.item_delete_bt:


                break;
        }
    }
}