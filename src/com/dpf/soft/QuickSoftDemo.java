package com.dpf.soft;

import java.util.Arrays;
import java.util.Random;

/**
 * @author dpf
 * @create 2020-09-25 4:42 下午
 * @email 446933040
 */
public class QuickSoftDemo {

    public static void main(String[] args) {
        int arr[] = new int[70000];

        Random random = new Random();
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = random.nextInt();
        }

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        int[] arr4 = Arrays.copyOf(arr, arr.length);
        int[] arr5 = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();
        quickSort1(arr2,0,arr2.length-1);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

         start = System.currentTimeMillis();
        quickSort2(arr3,0,arr3.length-1);
         end = System.currentTimeMillis();
        System.out.println(end-start);


        start = System.currentTimeMillis();
        quickSort3(arr4,0,arr4.length-1);
        end = System.currentTimeMillis();
        System.out.println(end-start);


        start = System.currentTimeMillis();
        quickSort4(arr5,0,arr5.length-1);
        end = System.currentTimeMillis();
        System.out.println(end-start);


    }






    /**
     * 双边循环 基准为中间数据
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort1(int[] arr, int left, int right) {

        // 递归退出条件
        if(left>right){
            return;
        }

        int temp = 0;
        int pivot = arr[(left + right) / 2]; //取中间的值为基准值

        int leftIndex = left;
        int rightIndex = right;
        while (leftIndex < rightIndex) {
            //左边找到大于基准值得值
            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            // 右边找到小于基准值的值
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            // leftIndex>=rightIndex 说明基准值左右两边值符合左边值都小于基准值，右边都大于
            if (leftIndex >= rightIndex) {
                break;
            }

            // 交换
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;

            //如果交换完成后，左下标或右下标正好在基准位置
            /*if (arr[leftIndex] == pivot) {
                rightIndex--;
            }
            if (arr[rightIndex] == pivot) {
                leftIndex++;
            }*/
        }

        //左右下标在同一位置，防止栈溢出
        if (leftIndex == rightIndex) {
            leftIndex++;
            rightIndex--;
        }

        //向左递归 如果已经有顺序，不再继续
        if (left < rightIndex) {
            quickSort1(arr, left, rightIndex);
        }
        //向右递归
        if (right > leftIndex) {
            quickSort1(arr, leftIndex, right);
        }
    }


    /**
     * 双边循环，基准为第一个数字
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort2(int[] arr,int startIndex,int endIndex) {
        //递归的退出条件：递归最后一层左边的元素小于或等于右边元素的位置
        if(startIndex >= endIndex) {
            return;
        }

        //取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        /*
         * 第一步：选定基准元素pivot，设置左指针left和右指针right
         * 第二步：第1次循环，从right指针开始，让指针指向的元素与基准元素作比较。
         * 第三步：如果大于或等于pivot则指针左移，否则停止移动转到left指针
         * 第四步：如果left指针指向的元素小于或等于pivot，则指针向右移动
         * 第五步：如果大于，则将前面右指针指向数据与左指针指向元素交换。继续执行1-5步
         * 第六步：直到left指针和right指针相遇
         */
        while(left != right) {
            while(left<right && arr[right] > pivot) {
                right--;
            }
            while(left<right && arr[left] <= pivot ) {
                left++;
            }
            if(left<right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        //pivot和指针重合点交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;

        int pivotIndex =  left;
        //根据基准元素，递归调用
        quickSort2(arr,startIndex,pivotIndex-1);
        quickSort2(arr,pivotIndex+1,endIndex);
    }

    /******************************************/

    /**
     * 单边循环
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort3(int[] arr, int left, int right) {

        if (left < right) {
            int pivot = left;
            int index = pivot + 1;
            int temp;
            for (int i = index; i <= right; i++) {
                if (arr[i] < arr[pivot]) {
                    temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                    index++;
                }
            }
            temp = arr[index - 1];
            arr[index - 1] = arr[pivot];
            arr[pivot] = temp;

            quickSort3(arr, left, index - 1);
            quickSort3(arr, index, right);
        }
    }


    /**
     * 单边循环，优化
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort4(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort4(arr, left, partitionIndex - 1);
            quickSort4(arr, partitionIndex + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;

    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
