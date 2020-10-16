package com.dpf.linkedlist;

/**
 * @author dpf
 * @create 2019-10-26 18:26
 * @email 446933040@qq.com
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建要给链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //加入
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();

//        System.out.println("修改后~~~");
//        HeroNode2 hero5 = new HeroNode2(2, "小卢", "玉麒麟");
//        doubleLinkedList.update(hero5);
//        doubleLinkedList.list();
//
//        System.out.println("删除后·····");
//        doubleLinkedList.del(4);
//        doubleLinkedList.list();


    }
}

/**
 * 定义链表
 *
 * 添加时辅助节点指向头
 */
class DoubleLinkedList{

    //初始化头节点,不存放任何数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点
     * @param heroNode
     */
    public void add(HeroNode2 heroNode){
        //定义一个辅助节点
        HeroNode2 temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 按升序添加节点
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode){
        //定义一个辅助节点
        HeroNode2 temp = head;
        boolean falg = false; //标识添加节点唯一标识是否已经存在
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no>heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                falg = true;
                break;
            }
            temp = temp.next;
        }
        if(!falg){
            //不是添加到链表尾部
            if(temp.next!=null){
                temp.next.pre = heroNode;
                heroNode.next = temp.next;
            }
            temp.next = heroNode;
            heroNode.pre = temp;
        }else{
            System.out.println("不能添加，当前信息已经存在");
        }


    }


    /**
     * 修改链表
     * @param heroNode2
     */
    public void update(HeroNode2 heroNode2){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助节点
        HeroNode2 temp = head.next;
        boolean falg = false; //标志要修改的数据是否存在

        while (true){
            //遍历到链表尾部
            if (temp.next == null){
                return;
            }
            //找到要修改的数据
            if(temp.next.no == heroNode2.no){
                falg = true;
                break;
            }
            temp = temp.next;
        }
        if(falg){
            temp.next.name = heroNode2.name;
            temp.next.nickname = heroNode2.nickname;
        }else{
            System.out.println("没有找到要修改的数据~~~");
        }


    }



    /**
     * 删除节点
     * @param no
     */
    public void del(int no){
        if(head.next == null){
            System.out.println("链表为空~~~~");
            return;
        }
        HeroNode2 temp = head.next;
        boolean falg = false;
        while (true) {
            if(temp == null){
                break;
            }

            if(temp.no == no){
                falg = true;
                break;
            }

            temp = temp.next;
        }
        if(falg){
           temp.pre.next = temp.next;
           if(temp.next!=null){
               temp.next.pre = temp.pre;
           }
        }else{
            System.out.println("要删除的节点不存在");
        }
    }


    /**
     * 遍历链表
     */
    public void list(){
        //定义一个辅助节点
        HeroNode2 temp = head.next;
        if(temp == null){
            System.out.println("链表为空~~");
            return;
        }
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }



}


/**
 * 定义节点
 */
class HeroNode2{

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}
