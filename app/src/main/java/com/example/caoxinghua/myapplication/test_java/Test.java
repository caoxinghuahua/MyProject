package com.example.caoxinghua.myapplication.test_java;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        List list1=new ArrayList();
        List list2=new ArrayList(){};
        List list3=new ArrayList(){{}};
        List list4=new ArrayList(){{}{}};
        System.out.println(list1.getClass()==list2.getClass());
        System.out.println(list1.getClass()==list3.getClass());
        System.out.println(list1.getClass()==list4.getClass());
        System.out.println(list2.getClass()==list3.getClass());
        System.out.println(list2.getClass()==list4.getClass());
        System.out.println(list3.getClass()==list4.getClass());
        System.out.println(list1.getClass().getName());
        System.out.println(list2.getClass().getName());
        System.out.println(list3.getClass().getName());
        System.out.println(list4.getClass().getName());
        AA a=new AA();

        System.out.print("a:"+a.hashCode());

        System.out.print("a:"+a.hashCode());
       for(int i=0;i<10;i++){
//           a.setM(i);
       }
       int arr[]={3,18,13,45,39,70,98,5,100,1};
//       buddleSort(arr);
//       insertSort(arr);
//        sheelSort(arr);
        selectSort(arr);
        quick(arr);
    }
    //冒泡排序
    public static void buddleSort(int a[]){
        int len=a.length;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                }
            }
        }
        print(a);
    }
    //插入排序
    public static void insertSort(int a[]){
        int size=a.length;
        for(int i=1;i<size;i++){
            int x=a[i];
            int j=i;
            for(;a[j-1]>x&&j>0;j--){
                a[j]=a[j-1];
            }
            a[j]=x;
        }
        print(a);
    }
    public static void print(int a[]){
        int len=a.length;
        System.out.println("排序结果:");
        for(int i=0;i<len;i++){
            System.out.print("  "+a[i]);
        }
        System.out.println("");
    }

    public static void sheelSort(int [] a){
      int len=a.length;//单独把数组长度拿出来，提高效率
       while(len!=0){
                    len=len/2;
                for(int i=0;i<len;i++){//分组
                  for(int j=i+len;j<a.length;j+=len){//元素从第二个开始
                    int k=j-len;//k为有序序列最后一位的位数
                    int temp=a[j];//要插入的元素

                    while(k>=0&&temp<a[k]){//从后往前遍历
                        a[k+len]=a[k];
                        k-=len;//向后移动len位
                    }
                    a[k+len]=temp;
                }
            }
        }
        print(a);
    }
    public static void selectSort(int a[]){
        int len=a.length;
        for(int i=0;i<len;i++){
            int start=a[i];
            int num=i;
            for(int j=i;j<len;j++){
                if(a[j]<start){
                    num=j;
                    start=a[j];
                }
            }
            a[num]=a[i];
            a[i]=start;

        }
        print(a);
    }
    //快速排序
    public static void quick(int a[]){
        if(a.length>0){
            quickSort(a,0,a.length-1);
        }
        print(a);
    }

    public static void quickSort(int a[],int low,int high){
        if(low<high){
            int middle=getMiddle(a,low,high);
            quickSort(a,low,middle-1);
            quickSort(a,middle+1,high);
        }
    }

    public static int getMiddle(int a[],int low,int high){
        int key=a[low];
        while (low<high){
            while (low<high&&a[high]>key){
                high--;
            }
            a[low]=a[high];
            while (low<high&&a[low]<key){
                low++;
            }
            a[high]=a[low];
        }
        a[low]=key;
        return low;
    }
}
