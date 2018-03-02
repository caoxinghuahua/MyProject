package com.example.caoxinghua.myapplication.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.caoxinghua.myapplication.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends BaseAdapter {
    private List<String> list;
    private LayoutInflater inflater;
    private Context context;
    public MyAdapter(Context context,List<String> list){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.list=list;
    }
     @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_listview_item,null);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        }else {
            Log.i("xxx","xxx:"+convertView.getTag());
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final String url=list.get(position);
//        viewHolder.img.setImageResource(R.drawable.ad_default_image_banner);
//        viewHolder.img.setTag(url);
//        Picasso.with(context).load(url).into(viewHolder.img);
     /**   DisplayImageOptions options=new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(context);
        final ViewHolder finalViewHolder = viewHolder;
        ImageLoader.getInstance().init(configuration);
        ImageLoader.getInstance().displayImage(url, finalViewHolder.img, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(url.equals(finalViewHolder.img.getTag())){
                    finalViewHolder.img.setImageBitmap(loadedImage);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });*/
        viewHolder.img.setTag(R.id.image_id,url);
        viewHolder.img.setImageResource(R.drawable.ad_default_image_banner);
        final ViewHolder finalViewHolder = viewHolder;

        Glide.with(context).load(url).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    String tag= (String) finalViewHolder.img.getTag(R.id.image_id);
                    if(tag!=null&&tag.equals(url)){
                        finalViewHolder.img.setImageDrawable(resource);
                    }
                }
            });

        Glide.with(context).load(url).placeholder(R.drawable.ad_default_image_banner).into(viewHolder.img);

        return convertView;
    }
    class  ViewHolder{
        ImageView img;
    }
}
