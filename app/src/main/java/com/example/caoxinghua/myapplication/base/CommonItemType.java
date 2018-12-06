package com.example.caoxinghua.myapplication.base;

/**
 * @author caoxinghua on 2018/9/6
 * @email caoxinghua@gomeplus.com
 */
public interface CommonItemType<T> {
    int getItemViewType(int pos,T t);//获取类型
    int getLayoutId(int type);//获取id
}
