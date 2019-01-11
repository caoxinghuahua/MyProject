package com.example.caoxinghua.myapplication.sort;

/**
 * @author caoxinghua on 2018/10/15
 * @email caoxinghua@gomeplus.com
 */
public class SortTest {
    public static void main(String []args){
        int array[]={1,6,-5,8,39,45,23};

        BinarySortTree binarySortTree=new BinarySortTree(new Node(array[0]));

        /**for(int i=1;i<array.length;i++){
            binarySortTree.buildRoot(array[i]);
        }
        binarySortTree.inOrder(binarySortTree.root);//中序遍历
        System.out.println();
        binarySortTree.preOrder(binarySortTree.root);//前序遍历
        System.out.println();
        binarySortTree.postOrder(binarySortTree.root);//后续遍历
        System.out.println();
        binarySortTree.search(binarySortTree.root,23);
        binarySortTree.search(binarySortTree.root,55);*/
        //非递归
        for(int i=1;i<array.length;i++){
            binarySortTree.unRecursionBuild(array[i]);
        }
        binarySortTree.unReInOrder(binarySortTree.root);
        binarySortTree.find(33);
        binarySortTree.find(99);
        binarySortTree.unRePreOrder(binarySortTree.root);
        binarySortTree.unRePostOrder(binarySortTree.root);
        binarySortTree.PostOrder2(binarySortTree.root);
        int a[]={2,3,4,2,4,7,3,3,7,6};
        System.out.println(Most(a));
    }
    public static  int Most(int a[]){
        int len=a.length;
        int index=0;
        int count=1;
        int tmp=1;
        for(int i=0;i<len;i++){
            index=i;
            count=1;
            for(int j=i+1;j<len;j++){
                if(a[i]==a[j]){
                    count++;
                    index=j;
                }
            }
            if(tmp<count){
                tmp=count;
                index=i;
            }

        }
        return a[index];
    }
}
