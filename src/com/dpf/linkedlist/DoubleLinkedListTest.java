package com.dpf.linkedlist;

/**
 * @author dpf
 * @create 2020-09-18 4:49 下午
 * @email 446933040
 */
public class DoubleLinkedListTest {
    public static void main(String[] args) {
        DoubleLinkedList2 doubleLinkedList = new DoubleLinkedList2();

        HeroNode3 node1 = new HeroNode3(6, "zhangsan");
        HeroNode3 node3 = new HeroNode3(3, "wangwu");
        HeroNode3 node2 = new HeroNode3(8, "lisi");

        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node3);

        doubleLinkedList.list();

        HeroNode3 node4 = new HeroNode3(8, "lisi666");
        doubleLinkedList.update(node4);
        doubleLinkedList.list();
        System.out.println();
        doubleLinkedList.delete(3);
        doubleLinkedList.list();





    }
}


class DoubleLinkedList2{
    private HeroNode3 head = new HeroNode3(0,"");


    /**
     * 在链表尾部添加节点
     * @param heroNode3
     */
    public void add(HeroNode3 heroNode3){
        //辅助节点
        HeroNode3 temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode3;
        heroNode3.pre = temp;
    }

    /**
     * 按升序添加节点
     * @param heroNode3
     */
    public void addByOrder(HeroNode3 heroNode3){
        HeroNode3 temp = head;

        // 标识添加的节点是否已经存在
        boolean flag = false;

        while (true){
           if(temp.next == null){
               break;
           }
           if(temp.next.no>heroNode3.no){
                break;
           }
           if(temp.next.no == heroNode3.no){
               flag = true;
           }
           temp = temp.next;
        }

        if(!flag){
            //如果不是添加到尾部
            if(temp.next !=null){
                temp.next.pre = heroNode3;
                heroNode3.next = temp.next;
            }
            temp.next = heroNode3;
            heroNode3.pre = temp;
        }else {
            System.out.println("需要添加的节点已经存在");
        }
    }


    /**
     * 删除对应序号的链表
     * @param no
     */
    public void delete(int no){
        if(head.next==null){
            System.out.println("链表为空~~~");
        }
        HeroNode3 temp = head.next;
        boolean flag = false;  //判断要删除的节点是否存在
        while (true){
            if (temp == null){
                break;
            }

            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }
    }

    /**
     * 修改节点
     * @param heroNode3
     */
    public void update(HeroNode3 heroNode3){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode3 temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == heroNode3.no){
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if(flag){
            temp.next.name = heroNode3.name;
        }else {
            System.out.println("要修改的节点不存在");
        }
    }

    /**
     * 遍历节点
     */
    public void list(){
        HeroNode3 temp = head.next;

        if(temp.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

}


class HeroNode3{
     Integer no;
     String name;
     HeroNode3 pre;
     HeroNode3 next;

    public HeroNode3(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode3{" +
                "no=" + no +
                ", name='" + name +
                '}';
    }
}
