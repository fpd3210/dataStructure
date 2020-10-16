package com.dpf.tree;



/**
 * 顺序存储二叉树
 * @author dpf
 * @create 2020-10-16 9:52 上午
 * @email 446933040
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preTraverse();
        System.out.println();
        arrayBinaryTree.midTraverse();
        System.out.println();
        arrayBinaryTree.postTraverse();

    }
}

class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preTraverse(){
        preTraverse(0);
    }

    public void midTraverse(){
        midTraverse(0);
    }

    public void postTraverse(){
        postTraverse(0);
    }

    public void preTraverse(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+"\t");
        // 向左递归遍历 index*2+1<arr.length表示，树的第index个节点的左节节的值在数组的长度范围之内
        if(index*2+1<arr.length){
            preTraverse(2*index+1);
        }
        // 向左递归遍历 index*2+2<arr.length表示，树的第index个节点的右节节的值在数组的长度范围之内
        if(index*2+2<arr.length){
            preTraverse(2*index+2);
        }
    }

    public void midTraverse(int index){
        if(arr==null ||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if(index*2+1<arr.length){
            midTraverse(2*index+1);
        }
        System.out.print(arr[index]+"\t");
        // 向左递归遍历
        if(index*2+2<arr.length){
            midTraverse(2*index+2);
        }
    }

    public void postTraverse(int index){
        if(arr==null ||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if(index*2+1<arr.length){
            postTraverse(2*index+1);
        }
        // 向左递归遍历
        if(index*2+2<arr.length){
            postTraverse(2*index+2);
        }

        System.out.print(arr[index]+"\t");
    }


}
