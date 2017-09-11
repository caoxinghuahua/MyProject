package com.example.caoxinghua.myapplication.okhttp;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by caoxinghua on 2017/4/26.
 */

public class Main {
    enum Car{
        lamborghini,tata,audi,fiat,honda
    }

    public static void main(String[] args) {

        String strSearch = "This is the string in which you have to search for a substring.";
        String substring = "substring";
        boolean found = false;
        int max = strSearch.length() - substring.length();
        testlbl:
        for (int i = 0; i <= max; i++) {
            int length = substring.length();
            int j = i;
            int k = 0;
            while (length-- != 0) {
                if(strSearch.charAt(j++) != substring.charAt(k++)){
                    continue testlbl;
                }
            }
            found = true;
            break testlbl;
        }
        if (found) {
            System.out.println("发现");
        }
        else {
            System.out.println("没有发现");
        }


        Scanner scanner=new Scanner(System.in);
        scanner.hasNext();
        boolean flag=true;
        while(scanner.hasNext()&&flag) {
            String s = scanner.next();
            flag = Pattern.matches(".*end.*", s);
            String c = null;
            if (flag) {
                c = s;
                flag=!flag;
            }


            switch (c) {
                case "lamborghini":
                    System.out.println("你选择了 lamborghini!");
                    break;
                case "tata":
                    System.out.println("你选择了 tata!");
                    break;
                case "audi":
                    System.out.println("你选择了 audi!");
                    break;
                case "fiat":
                    System.out.println("你选择了 fiat!");
                    break;
                case "honda":
                    System.out.println("你选择了 honda!");
                    break;
                default:
                    System.out.println("我不知道你的车型。");
                    break;

            }

        }
        System.exit(0);


    }

}