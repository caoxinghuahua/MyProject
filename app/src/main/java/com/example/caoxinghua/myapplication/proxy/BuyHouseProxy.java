package com.example.caoxinghua.myapplication.proxy;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 * 代理类
 */
public class BuyHouseProxy implements BuyHouse {
    BuyHouse buyHouse;
    public BuyHouseProxy(BuyHouse buyHouse){
        this.buyHouse=buyHouse;
    }
    @Override
    public void buyHouse() {
        System.out.println("买房前准备");
        buyHouse.buyHouse();
        System.out.println("买完房装修");
    }
}
