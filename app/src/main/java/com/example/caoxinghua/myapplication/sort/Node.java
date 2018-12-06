package com.example.caoxinghua.myapplication.sort;

/**
 * @author caoxinghua on 2018/10/16
 * @email caoxinghua@gomeplus.com
 */
public class Node {
    public int key;
    public Node left;
    public Node right;
    public boolean isFirst=false;
    public Node(int key){
        this.key=key;
    }
}
