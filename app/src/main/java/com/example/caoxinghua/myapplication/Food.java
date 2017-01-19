package com.example.caoxinghua.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by caoxinghua on 2017/1/9.
 */

public class Food extends BaseObservable{
    private String name;
    private String img;
    private String description;
    private float price;
    public Food(String name,String img,String description,float price){
        this.name=name;
        this.img=img;
        this.description=description;
        this.price=price;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.caoxinghua.myapplication.BR.name);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    @BindingAdapter("img")
    public static void getNetImg(ImageView imageView,String img){
        Glide.with(imageView.getContext()).load(img).into(imageView);
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void ItemClick(View view){
        setName("new food");

        Toast.makeText(view.getContext(),"aaaaa",Toast.LENGTH_SHORT).show();
    }

}
