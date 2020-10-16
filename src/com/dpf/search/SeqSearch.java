package com.dpf.search;

/**
 * 线性查找
 * @author dpf
 * @create 2020-10-07 4:13 下午
 * @email 446933040
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {1,11,12,22,17};
        int index = seqSearch(arr, -11);
        if(index == -1){
            System.out.println("没有找到");
        }else{
            System.out.println("找到了，下标为"+index);
        }

    }

    public static int seqSearch(int arr[],int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
