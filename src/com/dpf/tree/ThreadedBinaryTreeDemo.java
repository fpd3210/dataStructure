package com.dpf.tree;


/**
 * 线索二叉树
 * @author dpf
 * @create 2020-10-17 10:55 下午
 * @email 446933040
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode3 root = new HeroNode3(1,"tom");
        HeroNode3 node2 = new HeroNode3(3,"jack");
        HeroNode3 node3 = new HeroNode3(6,"smith");
        HeroNode3 node4 = new HeroNode3(8,"mary");
        HeroNode3 node5 = new HeroNode3(10,"jerry");
        HeroNode3 node6 = new HeroNode3(14,"sunny");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        // 线索化二叉树
        threadedBinaryTree.threadedNodes();

        System.out.println("10号节点的前驱节点："+node5.getLeft());
        System.out.println("10号节点的后驱节点："+node5.getRight());

        // 原来中序遍历
        threadedBinaryTree.midTraverse();
        System.out.println();
        // 线索化后遍历
        threadedBinaryTree.threadedList();

    }
}

// 定义实现线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode3 root;

    // 用于遍历（线索化）时保留当前节点的前一个节点
    private HeroNode3 pre;

    public void setRoot(HeroNode3 root){
        this.root = root;
    }

    // 重载threadedNodes方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    // 遍历线索化二叉树的方法
    public void threadedList(){
        // 用于存储当前遍历的节点
        HeroNode3 node = root;
        while (node!=null){
            // 循环找到leftType==1的几点
            // 后面随着遍历而变化,因为当 leftType==1 时，说明该结点是按照线索化处理后的有效结点
            while (node.getLeftType()==0){
                node = node.getLeft();
            }
            System.out.println(node);

            // 如果当前节点的右指针指向的是后继节点就一直输出
            while (node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    // 对当前二叉树进行中序线索化
    public void threadedNodes(HeroNode3 node){
        if(node==null){
            return;
        }

        // 1.线索化左子树
        threadedNodes(node.getLeft());

        // 2.线索化当前节点
        // 处理当前节点的前驱节点
        if(node.getLeft()==null){
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针类型
            node.setLeftType(1);
        }
        // 处理后继节点
        if(pre!=null&&pre.getRight()==null){
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        // 处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        // 3.线索化右子树
        threadedNodes(node.getRight());
    }

    // 前序遍历
    public void preTraverse(){
        if(this.root!=null){
            this.root.preTraverse();
        }else {
            System.out.println("二叉树为空");
        }
    }
    // 中序遍历
    public void midTraverse(){
        if(this.root!=null){
            this.root.midTraverse();
        }else {
            System.out.println("二叉树为空");
        }
    }
    // 后序遍历
    public void postTraverse(){
        if(this.root!=null){
            this.root.postTraverse();
        }else {
            System.out.println("二叉树为空");
        }
    }
}


// 定义节点
class HeroNode3{
    private int no;
    private String name;
    private HeroNode3 left;
    private HeroNode3 right;

    // 1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    // 2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode3(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode3 getLeft() {
        return left;
    }

    public void setLeft(HeroNode3 left) {
        this.left = left;
    }

    public HeroNode3 getRight() {
        return right;
    }

    public void setRight(HeroNode3 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode3{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历   父节点- 左子树 右子树
    public void preTraverse(){
        System.out.println(this);
        if(this.left !=null && this.getLeftType()==0){
            this.left.preTraverse();
        }
        if(this.right!=null && this.getRightType()==0){
            this.right.preTraverse();
        }
    }
    // 中序遍历  左子树  父节点  右子树
    public void midTraverse(){
        if(this.left!=null&&this.getLeftType()==0){
            this.left.midTraverse();
        }
        System.out.println(this);
        if(this.right!=null&&this.getRightType()==0){
            this.right.midTraverse();
        }
    }

    // 后序遍历  左子树  右子树  根节点
    public void postTraverse(){
        if(this.left!=null&&this.getLeftType()==0){
            this.left.postTraverse();
        }
        if(this.right!=null&&this.getRightType()==0){
            this.right.postTraverse();
        }
        System.out.println(this);
    }


    // 递归删除节点
    public void delNode(int no){
        // 左子节点不为空且左子节点对应数据就是要删数据
        if(this.left!=null && this.left.no == no ){
            this.left = null;
            return;
        }

        // 右子节点不为空且右子节点就是要删除的数据
        if(this.right!=null && this.right.no == no){
            this.right = null;
            return;
        }

        // 左子节点不为空，向左递归
        if(this.left!=null){
            this.left.delNode(no);
        }

        // 右子节点不为空，向右递归
        if(this.right !=null){
            this.right.delNode(no);
        }
    }
}
