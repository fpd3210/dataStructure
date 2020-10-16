package com.dpf.linkedlist;



import java.util.Stack;

/**
 * @author dpf
 * @create 2019-10-25 21:45
 * @email 446933040@qq.com
 * 单链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        System.out.println("遍历链表~~");
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后~~");
        singleLinkedList.list();

        System.out.println("删除后~~~~");
        singleLinkedList.delete(3);
        singleLinkedList.list();

        System.out.println("链表反转后");
        singleLinkedList.reverse();
        singleLinkedList.list();

        System.out.println("链表长度："+singleLinkedList.getLength());


        HeroNode lastIndexNode = singleLinkedList.findLastIndexNode(2);
        System.out.println("链表倒数第2个节点信息为"+lastIndexNode);

        singleLinkedList.list();
        System.out.println();
        singleLinkedList.reversePrint();
    }
}

/**
 * 定义单链表
 *
 * 删除添加时辅助节点指向头节点遍历
 *
 */
class SingleLinkedList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    /**
     * 返回头节点
     * @return
     */
    public HeroNode getHead(){
        return head;
    }


    /**
     * 往节点尾部添加节点(不考虑顺序)
     * @param heroNode
     */
    public void add(HeroNode heroNode){
        //定义一个辅助节点，方便遍历链表(头节点不能动)
        HeroNode temp = head;
        while (true){
            //找到链表最后
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }

        //添加到链表尾部
        temp.next = heroNode;
    }
    /**
     * 按顺序添加节点（asc）
     */
    public void addByOrder(HeroNode heroNode){
        //定义一个辅助节点，方便遍历链表(头节点不能动)
        HeroNode temp = head;
        boolean falg = false; //标志添加的节点唯一标识是否已经存在
        //找到对应位置
        while (true){

            //找到链表最后
            if(temp.next == null){
                break;
            }

            if(temp.next.no>heroNode.no){  //找到对应位置
                break;
            }else if(temp.next.no == heroNode.no){
               falg = true;
               break;
            }
            temp = temp.next;
        }

        if (falg){  //不能添加，已经存在
            System.out.println("不能添加，当前信息已经存在");
        }else {
            //插入temp后面
            if(temp.next!=null){
                heroNode.next = temp.next;
            }
            temp.next = heroNode;
        }
    }

    /**
     * 更新链表信息
     * @param heroNode
     */
    public void update(HeroNode heroNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助节点，方便遍历链表(头节点不能动)
        HeroNode temp = head;
        boolean falg = false;  //标识需要更新的节点是否存在
        while (true){
            if (temp==null){
                //链表已经遍历完
                break;
            }
            if(temp.next.no == heroNode.no){
                falg = true;
                break;
            }
            temp = temp.next;
        }

        //更新节点
        if (falg){
            heroNode.next = temp.next.next;
            temp.next = heroNode;
        }else{
            System.out.println("需要更新的节点不存在");
        }
    }


    /**
     * 删除指定编号的节点
     * @param no
     */
    public void delete(int no){
        //定义一个辅助节点，方便遍历
        HeroNode temp = head;
        boolean falg = false; //标识需要删除的节点是否存在
        while (true){
            if(temp == null){
                break;
            }
            //找到对应节点
            if(temp.next.no == no){
                falg = true;
                break;
            }
            temp = temp.next;
        }

        //删除对应节点
        if (falg){
            temp.next = temp.next.next;
        }else{
            System.out.println("要删除的节点不存在");
        }

    }


    /**
     * 求链表长度
     * @return
     */
    public int getLength(){
        //定义一个辅助节点
        HeroNode temp = head.next;
        int length = 0;

        while (true){
            if(temp == null){
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }


    /**
     * 查找链表中的倒数第k个节点
     * @param k
     * @return
     */
    public HeroNode findLastIndexNode(int k){

        int length = getLength();

        if(k <= 0 || k>length){
            return null;
        }

        HeroNode cur = head.next;
        int index = 0;
        while (cur!=null){
            if(length-index == k){
                break;
            }
            index ++;
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 链表反转(前插）
     */
    public void reverse(){
        //如果当前链表为空，或者只有一个节点，无需反转
        if(head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助指针变量
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        while (cur !=null){
            next = cur.next;   //保存当前节点的下一个节点
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }

        head.next = reverseHead.next;

    }


    /**
     * 逆序输出
     */
    public void reversePrint(){
        if (head.next == null){
            return;
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        //定义一个辅助节点
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0){
            System.out.println(stack.pop());
        }


    }



    /**
     * 显示列表信息
     */
    public void list(){

        //定义一个辅助节点，方便遍历链表(头节点不能动)
        HeroNode temp = head.next;
        while (true){
            //找到链表最后
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
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
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
