package com.dpf.search;

/**
 * @author dpf
 * @create 2020-10-07 4:31 下午
 * @email 446933040
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,3,5,10,11,17,33};
        int i = binarySearch(arr, 0, arr.length-1, 19);
        System.out.println(i);
    }

    public static int binarySearch(int arr[],int left,int right,int value){
        //结束递归条件
        if(right>left){
            return -1;
        }
        int midIndex = (left+right)/2;
        if(arr[midIndex]>value){
            return binarySearch(arr, left, midIndex-1, value);
        }else if(arr[midIndex]<value){
            return binarySearch(arr,midIndex+1,right,value);
        }else {
            return midIndex;
        }
    }
}
