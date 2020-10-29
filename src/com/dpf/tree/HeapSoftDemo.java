package com.dpf.tree;

import java.util.Arrays;

/**
 * 堆排序
 * @author dpf
 * @create 2020-10-20 8:03 下午
 * @email 446933040
 */
public class HeapSoftDemo {
    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9};
        heapSoft(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 堆排序
     * @param arr
     */
    public static void heapSoft(int arr[]){
        int temp = 0;

        // 将无需列表构建成一个堆，根据升序降序选择大顶堆还是小顶堆
        for (int i = arr.length/2 -1; i >=0; i--) {
            adjustHead(arr,i,arr.length);
        }

        // 将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        // 重新调整结构使其满足堆定义，然后继续交换堆顶元素与当前末尾元素
        // 反复执行调整+交换 步骤，直到整个序列有序。
        for (int j=arr.length-1;j>0;j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHead(arr,0,j);
        }
    }


    /**
     * 将一个数组(二叉树)，调整为一个大顶堆
     * @param arr
     * @param i 表示非叶子节点在数组中的索引
     * @param length    表示多少个元素需要调整，length逐渐减少
     */
    public static void adjustHead(int arr[],int i,int length){

        int temp = arr[i];

        for (int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length&&arr[k]<arr[k+1]){ // 左子节点的值小于右子节点的值
                k++;  // k指向右子节点
            }
            if(arr[k]>temp){  //如果子节点大于父节点
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        // 当for循环结束后，我们得到讲义i节点为父节点树的最大值
        arr[i] = temp;
    }

}
