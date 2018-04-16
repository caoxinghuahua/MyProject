package com.example.caoxinghua.myapplication.test_java;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapTest {
    public static void main(String []args){
      MapTest mapTest=new MapTest();
//      mapTest.testMap();
        mapTest.testTreeMap();
    }

    public void testMap(){
        Map<String,String> map=new HashMap<>();
        map.put("1","2");
        map.put("3","3");
        map.put("4","5");
        map.put("2","4");
        for(Object set:map.keySet()){
            System.out.println("key:"+set.toString()+" value:"+map.get(set));
        }
        Iterator<String> iterator=map.keySet().iterator();
        while (iterator.hasNext()){
            String key=iterator.next();
            System.out.println("key:"+key+" value:"+map.get(key));
        }
        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());

        }
    }
    public void testTreeMap(){
        //自定义类做key时，若想自然顺序排列需要实现Comparable接口
        TreeMap<Student,String> treeMap=new TreeMap<>();
        Student s1=new Student("ac",20);
        Student s2=new Student("cc",18);
        Student s3=new Student("qw",10);
        Student s4=new Student("ad",18);
        Student s5=new Student("ba",50);
        treeMap.put(s1,"3");
        treeMap.put(s2,"4");
        treeMap.put(s3,"5");
        treeMap.put(s4,"6");
        treeMap.put(s5,"7");

        for(Map.Entry<Student,String> entry:treeMap.entrySet()){
            Student stu=entry.getKey();
            System.out.println("name:"+stu.getName()+" age:"+stu.getAge()+" value:"+entry.getValue());
        }
        LinkedList<Integer> l=new LinkedList<>();

    }
    class Student implements Comparable<Student>{
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(@NonNull Student o) {
            int num1=age-o.getAge();
            int num2=num1==0?name.compareTo(o.getName()):num1;
            return num2;
        }
    }
}
