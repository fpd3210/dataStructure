package com.dpf.queue;

import java.util.Scanner;

/**
 * @author dpf
 * @create 2019-10-24 16:49
 * @email 446933040@qq.com
 *
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        CircleQueue  queue = new CircleQueue(4);
        boolean loop = true;
        char key = ' '; //接受用户输入
        Scanner sc = new Scanner(System.in);
        while(loop){
            System.out.println("s(show)显示队列");
            System.out.println("g(get)从队列取出数据");
            System.out.println("a(add)添加数据");
            System.out.println("h(Head)显示队列头数据");
            System.out.println("e(exit)");

            key = sc.next().charAt(0); //接收一个字符串
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'g':
                    try {
                        int value = queue.getQueue();
                        System.out.printf("取出的数据为%d\n",value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'a':
                    System.out.println("输入你要添加的数");
                    int val = sc.nextInt();
                    queue.addQueue(val);
                    break;
                case 'h':
                    try {
                        int value = queue.showQueueHead();
                        System.out.printf("队列头部的数据为%d\n",value);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;

                    default:
                        break;
            }
        }
    }
}






