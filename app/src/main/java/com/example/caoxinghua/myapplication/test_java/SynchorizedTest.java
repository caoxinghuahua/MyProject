package com.example.caoxinghua.myapplication.test_java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchorizedTest {
    public static void main(String []args){
       SynchorizedTest synchorizedTest=new SynchorizedTest();
       synchorizedTest.test();
    }
    class Bank{
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(3);
        private int count=100;

        /**synchronized
         * */
        public  synchronized void save(){
            count+=100;
        }
        public int getCount(){
            return count;
        }
        //threadLocal的使用
        private ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 100;
            }

        };
        public void save2(){
            threadLocal.set(threadLocal.get()+count);
        }
        public int getCount2(){
            return threadLocal.get();
        }
        //原子操作
        private AtomicInteger atomicInteger=new AtomicInteger(100);
        public void save3(){
            atomicInteger.addAndGet(100);
        }
        private AtomicInteger getCount3(){
            return atomicInteger;
        }
    }
    class NewRunable implements Runnable{
        private Bank bank;
        public NewRunable(Bank bank){
            this.bank=bank;
        }

        @Override
        public void run() {
            for(int i=0;i<10;i++){
                bank.save3();
                System.out.println(Thread.currentThread().getName()+ "账户余额为：" + bank.getCount3());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void test(){
       Bank bank=new Bank();
       NewRunable runable=new NewRunable(bank);
       new Thread(runable,"user1").start();
       new Thread(runable,"user2").start();
    }
}
