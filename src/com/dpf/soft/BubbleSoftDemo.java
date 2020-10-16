package com.dpf.soft;

import java.util.Random;

/**
 * @author dpf
 * @create 2020-09-21 10:13 下午
 * @email 446933040
 */
public class BubbleSoftDemo {

    public static void main(String[] args) {
        int arr[] = new int[8000000];

        Random random = new Random();
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = random.nextInt(10000);
        }

        long startTime = System.currentTimeMillis();
        bubbleSoft(arr);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);
    }

    public static void bubbleSoft(int arr[]){
        int temp;  // 中间变量，用于交换数据
        boolean flag = true;  // 判断一轮从前往后比是否有交换过数据
        for (int i = 0; i < arr.length - 1 ; i++) {
            for (int j = 0; j < arr.length - i -1; j++) {
                if(arr[j] > arr[j+1]){
                   temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j] = temp;
                    flag = false;
                }
                if (!flag){ // 如果没有交换过数据，说明已经排好序
                    break;
                }else {
                    flag = true;
                }
            }
        }
    }

}


