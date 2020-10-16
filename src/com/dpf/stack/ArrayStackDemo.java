package com.dpf.stack;

/**
 * @author dpf
 * @create 2020-09-19 11:01 下午
 * @email 446933040
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(6);
        stack.push(7);

        stack.list();
        System.out.println();
        System.out.println("出栈的数据为："+stack.pop());
        System.out.println();

        stack.list();
        System.out.println("-----------------------");

        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(4);
        linkedStack.push(8);

        linkedStack.list();

        System.out.println("出栈数据为"+linkedStack.pop());

        linkedStack.list();


    }
}


/**
 * 数组模拟栈
 */
class ArrayStack{
    private Integer maxSize;
    private Integer stack[];
    private Integer top = -1;

    public ArrayStack(Integer maxSize) {
        this.maxSize = maxSize;
        stack = new Integer[maxSize];
    }

    //栈满(
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(Integer data){
        //判断栈是否满
        if(!isFull()){
            top++;
            stack[top] = data;
        }
    }


    // 出栈
    public Integer pop(){
        //判断栈是否为空
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据可出栈");
        }
        int value = stack[top--];
        return value;
    }

    // 遍历栈
    public void list(){
        //判断栈是否为空
        if(isEmpty()){
            System.out.println("栈空，没有数据~~~~");
            return;
        }

        for (int i = top; i >-1 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }

    }

}

/**
 * 链表模拟栈
 */
class LinkedStack{

    LinkedNode head = new LinkedNode(-1);

    //判断栈是否为空
    public boolean isEmpty(){

        return head.next == null;
    }


    //入栈
    public void push(Integer data){

        LinkedNode node = new LinkedNode(data);

        LinkedNode temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }


    //出栈
    public Integer pop(){

        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据可出栈");
        }
        LinkedNode temp = head;
        int val = temp.next.no;
        temp.next = temp.next.next;
        return val;
    }

    //获取栈的长度
    public Integer length(){
        if(head.next == null){
            return 0;
        }

        LinkedNode temp = head.next;
        Integer length = 0;
        while (true){
            if(temp == null){
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据可遍历");
        }
        LinkedNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp.no);
            temp = temp.next;
        }
    }




}

class LinkedNode{
    Integer no;
    LinkedNode next;

    public LinkedNode(Integer no) {
        this.no = no;
    }
}