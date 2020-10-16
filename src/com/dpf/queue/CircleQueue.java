package com.dpf.queue;

/**
 * @author dpf
 * @create 2019-10-25 10:16
 * @email 446933040@qq.com
 * 循环队列
 */
public class CircleQueue {
    private int maxSize;  //数组的最大容量
    private int rear;     //队列尾    rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    private int front;    //队列头
    private int[] arr;    //用于存放数据，模拟队列


    /**
     * 初始化队列
     * @param maxSize
     */
    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        System.out.println("rear="+rear+"\tfront="+front);
        return (rear+1)%maxSize==front;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 往队列添加数据
     * @param n
     */
    public void addQueue(int n){
        //判断是否满
        if (isFull()){
            System.out.println("队列满，不能添加数据");
            return;
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;

    }

    /**
     * 取出数据
     * @return
     */
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据可取");
        }

        int value = arr[front];
        front = (front+1)%maxSize;
        System.out.println("rear="+rear+"\tfront="+front);
        return value;
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue(){
        //判断是否为空
        if(isEmpty()){
            System.out.println("队列为空，没有数据可显示");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    /**
     * 显示队头数据
     * @return
     */
    public int showQueueHead(){
        //判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据可显示");
        }
        return arr[front];
    }


    /**
     *  求当前队列有效数个数
     */
    public int size(){

        return (rear-front+maxSize)%maxSize;
    }
}
