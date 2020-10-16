package com.dpf.linkedlist;

import java.util.Stack;

/**
 * @author dpf
 * @create 2019-10-25 21:45
 * @email 446933040@qq.com
 * 单向循环链表
 */
public class Josephu {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);



    }


}

/**
 * 定义单链表
 *
 * 删除添加时辅助节点指向头节点遍历
 *
 */
class CircleSingleLinkedList{

    //创建一个First节点，当前没有编号
    private Boy first = null;

    /**
     * 添加小孩节点
     * @param nums
     */
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("nums的值不正确");
        }
        Boy curBoy = null; //辅助指针，用来构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums ; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);

            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }

    }

    /**
     * 遍历当前链表
     */
    public void showBoy() {

        if(first == null){
            System.out.println("链表为空~~~");
            return;
        }

        Boy curBoy = first;
        while (true) {

            System.out.println("编号："+curBoy.getNo());
            if(curBoy.getNext()==first){
                break;
            }
            curBoy = curBoy.getNext();

        }
    }

    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if(first == null || startNo < 1 || startNo>nums){
            System.out.println("参数输入有误，请重新输入");
        }

        //定义一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        while (true){
            if(helper.getNext() == first){  //helper指向最后小孩节点
                break;
            }

            helper = helper.getNext();
        }


        //小孩报数前(first指向用户指定的报数编号的小孩)，
        for (int i = 0; i < startNo -1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数是，让first he helper指针同时的移动 m-1次，然后出圈
        //这里是一个循环动作，直到圈中只有一个节点
        while (true){
            if (helper == first){  //说明圈中只有一个节点
                break;
            }

            //让first 和 helper 指针同时移动 countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            //这时first指向的节点，就是要出圈的小孩节点
            System.out.println("小孩"+first.getNo()+"出圈");
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("最后留在圈中的小孩编号"+first.getNo());
    }
}


/**
 * 定义节点
 */
class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
