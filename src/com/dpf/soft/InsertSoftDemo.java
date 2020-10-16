package com.dpf.soft;

import java.util.Random;

/**
 * @author dpf
 * @create 2020-09-22 10:36 上午
 * @email 446933040
 */
public class InsertSoftDemo {

    public static void main(String[] args) {
        int arr[] = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
            System.out.print("\t"+arr[i]);
        }
        insertSoft(arr);

        System.out.println();
        for (int i : arr) {
            System.out.print("\t"+ i);
        }
    }

    public static void insertSoft(int arr[]){

        /**
         * 思路：
         *  将要排序的数组分成两部分，下标为0(默认已经排好序)跟下标大于0两部分
         *  从没有排序的第一个（insertValue）开始 跟前面已经排好序的部分一一比较如果大于insertValue往后移一个位置
         *  直到找到合适的位置，最后插入。
         */
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i;
            while (insertIndex>0 && insertValue < arr[insertIndex - 1]){
                arr[insertIndex] = arr[insertIndex - 1];
                insertIndex -- ;
            }
            if(insertIndex !=i){
                arr[insertIndex] = insertValue;
            }
        }
    }
}

