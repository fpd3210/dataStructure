package com.dpf.queue.java;


/**
 * @author dpf
 * @create 2020-09-12 11:54 上午
 * @email 446933040
 */
public class ArrayQueue {
    private int maxSize;  //数组的最大容量
    private int front;  // 队列头
    private int rear;   // 队列尾
    private int [] arr; // 用于存数据模拟队列

    /**
     * 初始化队列
     * @param maxSize
     */
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
    }


    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
       return this.front == this.rear;
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return this.rear == this.maxSize-1;
    }


    /**
     * 入队
     * @param value
     */
    public void addQueue(int value){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能添加数据");
            return;
        }
        this.arr[++rear] = value;
    }

    /**
     * 出队
     */
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据可获取");
        }
        return arr[++front];
    }

    /**
     * 显示队列所有元素
     */
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列中没有元素");
            return;
        }
        for (int i = front+1; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 显示队列头元素
     */
    public int showQueueHead(){
        //判断队列是否为空
        if(isEmpty()){
              throw new RuntimeException("队列为空，没有数据可获取");
        }
        return arr[front+1];
    }


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        queue.addQueue(1);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.addQueue(5);
        queue.addQueue(6);

        System.out.println("显示队列中所有元素：");
        queue.showQueue();

        System.out.println("队列头部元素为"+queue.showQueueHead());

        System.out.println();
        System.out.println("出队元素为"+queue.getQueue());
        System.out.println("出队元素为"+queue.getQueue());
        System.out.println("出队元素为"+queue.getQueue());
        System.out.println("出队元素为"+queue.getQueue());

    }
}
