package com.example.caoxinghua.myapplication.proxy;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 */
public class SingleInstance {

}
//懒汉
class InstanceTest{
    private static InstanceTest instance=null;
    public  static InstanceTest getInstance(){
        if(instance==null){
            instance=new InstanceTest();
        }
        return instance;
    }
}
//饿汉
class InstanceTest1{
    private static InstanceTest1 instance=new InstanceTest1();
    public  static InstanceTest1 getInstance(){
        return instance;
    }
}
//双重校验
class InstanceTest2{
    private static InstanceTest2 instance=null;
    public  static InstanceTest2 getInstance(){
        if(instance==null){
            synchronized (InstanceTest2.class){
                if(instance==null){
                    instance=new InstanceTest2();
                }
            }

        }
        return instance;
    }
}
