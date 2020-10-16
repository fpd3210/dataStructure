package com.dpf.soft;

import java.util.Random;

/**
 * @author dpf
 * @create 2020-09-21 10:48 下午
 * @email 446933040
 */
public class SelectSoftDemo {

    public static void main(String[] args) {

        int arr[] = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }

        selectSoft(arr);
        System.out.println();
        for (int i : arr) {
            System.out.println(i);
        }

    }

    public static void selectSoft(int arr[]){

        for (int i = 0; i < arr.length ; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length ; j++) {
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if(i != minIndex){
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
