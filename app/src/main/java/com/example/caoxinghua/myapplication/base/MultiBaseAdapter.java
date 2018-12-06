package com.example.caoxinghua.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author caoxinghua on 2018/9/5
 * @email caoxinghua@gomeplus.com
 */
public abstract class MultiBaseAdapter<T> extends BaseRecyclerAdapter<T> {
    private Context context;
    private List<T> data;
    private CommonItemType<T> commonItemType;
    public MultiBaseAdapter(Context context, List<T> data,CommonItemType<T> commonItemType) {
        super(context, -1, data);
        this.context=context;
        this.data=data;
        this.commonItemType=commonItemType;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id=commonItemType.getLayoutId(viewType);
        View view= LayoutInflater.from(context).inflate(id,parent,false);
        BaseViewHolder baseViewHolder=new BaseViewHolder(view);
        return baseViewHolder;
    }

//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
////        covert(holder,data.get(position));
//    }

    @Override
    public int getItemViewType(int position) {
        return commonItemType.getItemViewType(position,data.get(position));
    }
}
