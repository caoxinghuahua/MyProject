package com.example.caoxinghua.myapplication.okhttp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Created by caoxinghua on 2017/4/28.
 */



public class ExceptionDemo
{
    public static void main(String[]args) //throws Exception
    {
        Demo d = new Demo();

        try
        {
            int x = d.div(4,0);//程序运行截图中的三组示例 分别对应此处的三行代码
            //int x = d.div(5,0);
            //int x = d.div(4,1);
            System.out.println("x="+x);
        }
        catch (ArithmeticException e)
        {
            System.out.println(e.toString());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.toString());
        }
        catch (Exception e)//父类 写在此处是为了捕捉其他没预料到的异常 只能写在子类异常的代码后面
        //不过一般情况下是不写的
        {
            System.out.println(e.toString());
        }



        System.out.println("Over");

        Queue<String> queue=new LinkedList<String>();
        Vector<String> vector=new Vector<String>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");
        vector.add("E");

        System.out.println("旋转前: "+vector);
        Collections.swap(vector,0,4);
        System.out.println("旋转后: "+vector);
        InetAddress address = null;
        try {
            address = InetAddress.getByName("www.baidu.com");
        }
        catch (UnknownHostException e) {
            System.exit(2);
        }
        System.out.println(address.getHostName() + "=" + address.getHostAddress());
        System.exit(0);

    }

}
class Demo
{
    int div(int a,int b) throws ArithmeticException,ArrayIndexOutOfBoundsException//在功能上通过throws的关键字声明该功能可能出现问题
    {
        int []arr = new int [a];

        System.out.println(arr[4]);//制造的第一处异常

        return a/b;//制造的第二处异常
    }
}

