package com.example.caoxinghua.myapplication.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 *
 * 建立二叉查找树
 */
public class BinarySortTree {
    Node root;
    public BinarySortTree(Node node){
        root=node;
    }
    //递归实现
    public void buildRoot(int key){
        if(root.key>key){
            if(root.left==null){
                 root.left=new Node(key);
            }else {
                 buildTree(root.left,key);
            }
        }else if(root.key<key){
            if(root.right==null){
                root.right=new Node(key);
            }else {
                buildTree(root.right,key);
            }
        }
    }
    public void buildTree(Node node,int key){
        if(node.key>key){
            if(node.left==null){
                node.left=new Node(key);
            }else {
                buildTree(node.left,key);
            }
        }else if(node.key<key){
            if(node.right==null){
                node.right=new Node(key);
            }else {
                buildTree(node.right,key);
            }
        }
    }
    //中序遍历
    public void inOrder(Node node){
        if(node.left!=null) inOrder(node.left);
        System.out.print(node.key+"    ");
        if(node.right!=null) inOrder(node.right);
    }
    //前序遍历
    public void preOrder(Node node){
         System.out.print(node.key+"    ");
        if(node.left!=null) preOrder(node.left);

        if(node.right!=null) preOrder(node.right);
    }
    //后序遍历
    public void postOrder(Node node){

        if(node.left!=null) postOrder(node.left);

        if(node.right!=null) postOrder(node.right);
        System.out.print(node.key+"    ");
    }
//    //查找key
    public void search(Node node,int key){
        if(key<node.key){
           if(node.left!=null) {
               search(node.left,key);
            }else {
                System.out.println("不存在"+key);
            }
        }else if(key>node.key){
            if (node.right!=null) {
                search(node.right,key);
            }else {
                System.out.println("不存在"+key);
            }
        }else if(key==node.key){
            System.out.println("找到"+key);
        }
    }

//    //非递归中序遍历
//    public void inOrderNot(){
//        BinarySortTree sortTree=this;
//        System.out.println("非递归中序遍历");
//        while (sortTree!=null){
//
//            if(sortTree.left!=null){
//                sortTree=sortTree.left;
//            }
//            System.out.println(key+"   ");
//            if(sortTree.right!=null){
//                sortTree=sortTree.right;
//            }
//        }
//    }
    //非递归实现建立二叉查找树
    public void unRecursionBuild(int key){
        Node current=root;
        Node parentNode=root;
        boolean isLeftChild=true;
        while(current!=null){
             parentNode=current;
             if(current.key>key){
                     current=current.left;
                     isLeftChild=true;
             }else {
                     current=current.right;
                     isLeftChild=false;
             }
        }
        Node node=new Node(key);
        if(isLeftChild){
            parentNode.left=node;
        }else {
            parentNode.right=node;
        }
    }
    //非递归中序遍历
    public void unReInOrder(Node node){
        Stack<Node> stack = new Stack<Node>();
        Node current =node;
        while (current != null || stack.size() > 0) {
            while (current != null) {
                stack.push(current);
                current =current.left;
            }
            if (stack.size() > 0) {
                current = stack.pop();
                System.out.print(current.key+"  ");
                current =current.right;
            }
        }
    }

    //非递归前序遍历
    public void unRePreOrder(Node node){
        Stack<Node> stack = new Stack<Node>();
        Node current =node;
        while (current != null || stack.size() > 0) {

            while (current!= null) {
                System.out.print(current.key+"  ");
                stack.push(current);
                current =current.left;
            }
            if (stack.size() > 0) {
                current = stack.pop();
                current =current.right;
            }
        }
    }
    //非递归后序遍历
    public void unRePostOrder(Node node){
        Stack<Node> stack = new Stack<Node>();

        Node current =node;
        Node tmp;
        System.out.println();
        System.out.println("非递归后续遍历");
        while (current != null || stack.size() > 0) {

            while (current!= null)
            {
                current.isFirst=false;
                stack.push(current);

                current =current.left;
            }
            while (stack.size()>0&&stack.peek().isFirst==true){
                System.out.print(stack.pop().key+"  ");
            }
            if (stack.size() > 0) {
                   tmp=stack.pop();
                   tmp.isFirst=true;
                   stack.push(tmp);
                   current=tmp.right;
            }
        }
    }
    // 后序遍历非递归
    public  void PostOrder2(Node t) {
        Stack<Node> s = new Stack<Node>();
        Stack<Integer> s2 = new Stack<Integer>();
        Integer i = new Integer(1);
        System.out.println();
        System.out.println("非递归后续遍历");
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                s2.push(new Integer(0));
                t = t.left;
            }
            while (!s.empty() && s2.peek().equals(i)) {
                s2.pop();
                System.out.print(s.pop().key+"  ");
            }

            if (!s.empty()) {
                s2.pop();
                s2.push(new Integer(1));
                t = s.peek();
                t = t.right;
            }
        }
    }


    //非递归查找某个节点
    public void find(int key){
        Node current=root;
        while(current!=null&&current.key!=key){
            if(current.key<key){
                current=current.right;
            }else {
                current=current.left;
            }
        }
        System.out.println("find 结果："+(current!=null?current.key:"不存在"+key));
    }
}
