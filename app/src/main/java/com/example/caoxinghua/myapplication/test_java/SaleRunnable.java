package com.example.caoxinghua.myapplication.test_java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class SaleRunnable implements Runnable {
    private volatile int ticket=20;
    private ReentrantLock lock=new ReentrantLock();//重入锁
    @Override
    public void run() {
        while (true) {
               //同步代码块
//             synchronized (this) {
//               lock.lock();
               if(ticket<=0) break;

                System.out.println(Thread.currentThread().getName() + "卖出第" + (20 - ticket + 1) + "张票");
                ticket--;
//                lock.unlock();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static  void main(String[]args){
        SaleRunnable r=new SaleRunnable();
        Thread t1=new Thread(r,"t1");
        Thread t2=new Thread(r,"t2");
        Thread t3=new Thread(r,"t3");
        t1.start();
        t2.start();
        t3.start();
        testVolatile();
    }
    public static void testVolatile(){
        final VolatileTest test=new VolatileTest();
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    test.add();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();

                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    test.addB();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();

                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    test.add();
                    try {
                        sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();

                }
            }
        }.start();
    }
}
