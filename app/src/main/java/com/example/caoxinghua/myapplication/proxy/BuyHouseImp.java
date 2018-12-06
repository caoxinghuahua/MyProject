package com.example.caoxinghua.myapplication.proxy;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 * 委托类
 */
public class BuyHouseImp implements BuyHouse {
    @Override
    public void buyHouse() {
        System.out.println("我想买房，没有钱");
    }
}
