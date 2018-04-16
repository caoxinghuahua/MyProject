package com.example.caoxinghua.myapplication.test_java;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueTest {

    //  private LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>();//链表阻塞队列
    private ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(15);
//    private SynchronousQueue<Integer> queue=new SynchronousQueue<Integer>();
    private int flag=0;
    private int size=10;
    class MyRunnable implements Runnable{

        @Override
        public void run() {
            int newFlag=flag++;
            System.out.println("启动线程"+newFlag);
            if(newFlag<2){
                for(int i=0;i<size;i++){
                    int p=new Random().nextInt(255);
                    System.out.println("生产商品"+p+"号");
                    try {
//                        linkedBlockingQueue.put(p);
                       queue.put(p);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   System.out.println("仓库中还有"+queue.size()+"个商品");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                for(int i=0;i<size/2;i++){
                    try {
                        int c=queue.take();
                        System.out.println(c+"号商品出库");

                        System.out.println("仓库中还有"+queue.size()+"个商品");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public static void main(String []args){
        BlockQueueTest blockQueueTest=new BlockQueueTest();
        MyRunnable runnable=blockQueueTest.new MyRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        List list=new ArrayList<Integer>();

    }
}
