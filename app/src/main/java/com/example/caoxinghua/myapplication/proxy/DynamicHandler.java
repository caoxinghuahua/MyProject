package com.example.caoxinghua.myapplication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 * 动态处理器
 */
public class DynamicHandler implements InvocationHandler {
    Object object;
    public DynamicHandler(Object object){
        this.object=object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic 买房前准备");
        Object result=method.invoke(object,args);
        System.out.println("dynamic 买房后装修");
        return result;
    }
}
