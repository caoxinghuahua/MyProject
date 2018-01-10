package com.example.caoxinghua.myapplication.activity;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
    private final List<Conn> pool=new ArrayList<Conn>(3);
    Semaphore connSemaphore=new Semaphore(3);
    Semaphore semaphore=new Semaphore(1);
    public TestSemaphore(){
        pool.add(new Conn());
        pool.add(new Conn());
        pool.add(new Conn());
    }
    public  void print(String str){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" enter ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "正在打印 ..." + str);
        System.out.println(Thread.currentThread().getName()+" out ...");
        semaphore.release();
    }
    public Conn getConn(){
        try {
            connSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Conn conn=null;
        synchronized (pool){
            conn=pool.remove(0);
        }
        System.out.println(Thread.currentThread().getName()+" get a conn " + conn);
        return conn;
    }
    public void release(Conn c){
        synchronized (this){
            pool.add(c);
        }
        System.out.println(Thread.currentThread().getName()+" release a conn " + c);
        connSemaphore.release();
    }
    public static void main(String[] args){
        /**final TestSemaphore testSemaphore=new TestSemaphore();
        for (int i=0;i<2;i++){
           new Thread(){
               public void run() {
                   testSemaphore.print("hello world");
               }
           }.start();
        }*/
        final TestSemaphore testSemaphore1=new TestSemaphore();
        new Thread(){
            @Override
            public void run() {
                Conn conn=testSemaphore1.getConn();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testSemaphore1.release(conn);
            }
        }.start();
        for(int i=0;i<3;i++){
            new Thread(){
                @Override
                public void run() {
                    testSemaphore1.getConn();
                }
            }.start();
        }
    }
    class  Conn{

    }
}
