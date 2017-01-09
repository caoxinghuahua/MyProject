package com.example.caoxinghua.myapplication;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by caoxinghua on 2017/1/6.
 */

public class User {
    public  String firstName;
    public  String secondName;
    public  String iconUrl;
    public User(){

    }
    public User(String firstName,String secondName,String iconUrl){
        this.firstName=firstName;
        this.secondName=secondName;
        this.iconUrl=iconUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    @BindingAdapter("bind:iconUrl")
    public static void getNetImage(ImageView imageView,String iconUrl){
        Glide.with(imageView.getContext()).load(iconUrl).into(imageView);
    }
}
