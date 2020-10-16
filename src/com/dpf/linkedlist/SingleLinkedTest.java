package com.dpf.linkedlist;

/**
 * @author dpf
 * @create 2020-09-18 9:23 下午
 * @email 446933040
 */
public class SingleLinkedTest {
}

class SingleLinked2{
    private HeroNode4 head = new HeroNode4(0,"");


    public void add(HeroNode4 heroNode4){
        HeroNode4 temp = head;

        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode4;
    }

    /**
     *
     * @param heroNode4
     */
    public void addByOrder(HeroNode4 heroNode4){
        HeroNode4 temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no>heroNode4.no){
                break;
            }
            if(temp.next.no == heroNode4.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println("要添加的节点已经存在");
        }else {
            if(temp.next == null){
                temp.next = heroNode4;
            }else {
                heroNode4.next = temp.next;
                temp.next = heroNode4;
            }
        }

    }

    /**
     * 遍历
     */
    public void list(){
        HeroNode4 temp = head;

        if (temp == null){
            System.out.println("链表为空");
            return;
        }
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode4{
    Integer no;
    String name;
    HeroNode4 next;

    public HeroNode4(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode4{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
