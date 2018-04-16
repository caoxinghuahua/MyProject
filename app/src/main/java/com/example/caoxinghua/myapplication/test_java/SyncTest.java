package com.example.caoxinghua.myapplication.test_java;


public class SyncTest implements Runnable{
    A a =new A();
    B b=new B();
    public void init(){
        System.out.print("init");
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入了主线程之后...");
    }
    public void run(){
        System.out.print("run");
        Thread.currentThread().setName("副线程");
        a.next();
        b.bar(a);
        System.out.println("进入了副线程之后...");
    }
    public static void main(String[] args){
        SyncTest d1=new SyncTest();
        //死锁
        new Thread(d1).start();
        d1.init();
    }

}
class A{
    public synchronized void foo(B b){
        System.out.println("当前线程名为："+Thread.currentThread().getName()+"进入了A实例的foo()方法");
        try{
            Thread.sleep(200);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("当前线程名为："+Thread.currentThread().getName()+"试图调用B实例的last()方法");
        b.last();
    }
    public synchronized void last(){
        System.out.println("进入了A类的last()方法内部"+System.currentTimeMillis());
    }
    public synchronized void next(){
        System.out.println("进入了A类的next()方法内部"+System.currentTimeMillis());
    }
}
class B{
    public synchronized void bar(A a){
        System.out.println("当前线程名为："+Thread.currentThread().getName()+"进入了B实例的bar()方法");
        try{
            Thread.sleep(200);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("当前线程名为："+Thread.currentThread().getName()+"试图调用A实例的last()方法");
        a.last();
    }
    public synchronized void last(){
        System.out.println("进入了B类的last()方法内部");
    }
}