package com.example.caoxinghua.myapplication.proxy;

import java.lang.reflect.Proxy;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 */
public class ProxyTest {
    public static void main(String[] args){
        //静态代理
        BuyHouseImp buyHouseImp=new BuyHouseImp();
        BuyHouseProxy proxy=new BuyHouseProxy(buyHouseImp);
        proxy.buyHouse();

        BuyHouse buyHouse=new BuyHouseImp();
        BuyHouse proxyBuyHouse= (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(),new Class[]{BuyHouse.class},new DynamicHandler(buyHouse));
        proxyBuyHouse.buyHouse();

    }
}
