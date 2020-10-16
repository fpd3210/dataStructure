package com.dpf.hash;

import java.util.Scanner;

/**
 * @author dpf
 * @create 2020-10-13 8:14 下午
 * @email 446933040
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next(); //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}


// 创建HashTab管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    // 表示有多少条链表
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];

        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员
    public void add(Emp emp) {
        // 根据的到的id决定应添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);

        empLinkedListArray[empLinkedListNo].add(emp);
    }

    // 遍历所有链表(hashtab)
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d 条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    // 编写散列函数，使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }


}

class Emp {
    public int id;
    public String name;

    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}


class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        // 添加的是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }

        // 不是第一个雇员，使用辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    // 遍历链表信息的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }

        System.out.print("第" + (no + 1) + "链表的信息为");

        Emp curEmp = head;
        while (true) {
            System.out.println(curEmp);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    //根据id查找雇员
    public Emp findEmpById(int id) {

        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while (true) {

            if (curEmp.id == id) {
                break;
            }

            if (curEmp.next == null) {
                curEmp = null;
                break;
            }

            curEmp = curEmp.next;
        }
        return curEmp;
    }


}


