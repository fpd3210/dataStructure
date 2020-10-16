package com.dpf.tree;



/**
 *  链式存储二叉树
 * @author dpf
 * @create 2020-10-13 10:03 下午
 * @email 446933040
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree(); //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树 root.setLeft(node2);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);

        binaryTree.setRoot(root);

        binaryTree.preTraverse();
        System.out.println();
        binaryTree.midTraverse();
        System.out.println();
        binaryTree.postTraverse();

        HeroNode heroNode = binaryTree.midTraverSearch(1);
        System.out.println(heroNode);
        System.out.println();

        binaryTree.levelTraverse();
    }
}


// 定义二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    // 层序遍历
    public void levelTraverse(){
        if(this.root!=null){
            this.root.levelTraverse(root);
        }
    }

    // 前序遍历查找
    public HeroNode preTraverSearch(int no){
        if(this.root!=null){
          return this.root.preTraverSearch(no);
        }else {
           return null;
        }
    }

    // 中序遍历
    public HeroNode midTraverSearch(int no){
        if(this.root!=null){
            return this.root.midTraverSearch(no);
        }else {
            return null;
        }
    }
    // 后序遍历
    public HeroNode postTraverSearch(int no){
        if(this.root!=null){
           return this.root.postTraverSearch(no);
        }else {
           return null;
        }
    }

    // 删除节点
    public void delNode(int no){
        if(root!=null){
            // 根节点就是要删除的节点
            if(root.getNo()==no){
                root = null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树，没有要删除的节点");
        }
    }

}


// 定义节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历   父节点- 左子树 右子树
    public void preTraverse(){
        System.out.println(this);
        if(this.left !=null){
            this.left.preTraverse();
        }
        if(this.right!=null){
            this.right.preTraverse();
        }
    }
    // 中序遍历  左子树  父节点  右子树
    public void midTraverse(){
        if(this.left!=null){
            this.left.midTraverse();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.midTraverse();
        }
    }

    // 后序遍历  左子树  右子树  根节点
    public void postTraverse(){
        if(this.left!=null){
            this.left.postTraverse();
        }
        if(this.right!=null){
            this.right.postTraverse();
        }
        System.out.println(this);
    }

    // 层序输出
    public void levelPrint(HeroNode node,int level){
        //空树或层级不合理
        if(node==null||level<1){
            return;
        }
        if(level==1){
            System.out.print(node+"\t");
        }
        levelPrint(node.left,level-1);
        levelPrint(node.right,level-1);
    }

    // 层序遍历
    public void levelTraverse(HeroNode node){
        if(node==null){
            return;
        }
        int depth = depth(node);
        for (int i = 1; i <= depth ; i++) {
            levelPrint(node,i);
            System.out.println();
        }
    }



    // 获取树的高度(即几层)
    public int depth(HeroNode node){
        int leftDepth=0;
        int rightDepth=0;
        if(node==null){
            return 0;
        }
        if(node.left!=null){
            leftDepth = depth(node.left);
        }
        if(node.right!=null){
            rightDepth = depth(node.right);
        }
        return (leftDepth>rightDepth?leftDepth:rightDepth)+1;
    }



    /*----------------------------------------------------------------*/

    // 先序遍历查找
    public HeroNode preTraverSearch(int no){

        if(this.no == no){
            return this;
        }

        // 用于存储查找结果
        HeroNode resNode = null;
        if(this.left!=null){
          resNode =  this.left.preTraverSearch(no);
        }

        if(resNode!=null){
            return resNode;
        }

        if(this.right!=null){
            resNode = this.right.preTraverSearch(no);
        }

        return resNode;
    }

    // 中序遍历查找
    public HeroNode midTraverSearch(int no){
        HeroNode resNode = null;

        if(this.left!=null){
            resNode = this.left.midTraverSearch(no);
        }

        if(resNode!=null){
            return resNode;
        }

        if(this.no == no){
            resNode = this;
        }
        if(resNode!=null){
            return resNode;
        }

        if(this.right!=null){
            resNode = this.right.midTraverSearch(no);
        }

        return resNode;
    }



    //后续遍历查找
    public HeroNode postTraverSearch(int no){

        HeroNode resNode = null;
        if(this.left!=null){
            resNode = this.left.postTraverSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }

        if(this.right!=null){
            resNode = this.right.postTraverSearch(no);
        }

        if(resNode!=null){
            return resNode;
        }
        if(this.no == no){
            resNode = this;
        }
        return resNode;
    }

    // 递归删除节点
    public void delNode(int no){
        // 左子节点不为空且左子节点对应数据就是要删数据
        if(this.left!=null && this.left.no == no){
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
