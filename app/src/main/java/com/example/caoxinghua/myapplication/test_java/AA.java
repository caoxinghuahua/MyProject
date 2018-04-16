package com.example.caoxinghua.myapplication.test_java;

import android.util.Log;

import static java.lang.Thread.sleep;

public class AA {

        int n;
        public void setM(int m){
            synchronized (this) {
                n = m;
                System.out.println("n:" + n);


                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                System.out.println("n:" + n);
            }
        }

}
