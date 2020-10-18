package com.dpf;

/**
 * @author dpf
 * @create 2020-10-18 9:42 上午
 * @email 446933040
 */
public class TestD {
    public static void main(String[] args) {
        fun(3);
    }

    public static void fun(int n){
        if (n<1){
            return;
        }
        System.out.println("----start-----");
        System.out.println("aaaa"+n);
        fun(n-1);
        System.out.println("bbb"+n);
        System.out.println("----end-----");
    }


}
