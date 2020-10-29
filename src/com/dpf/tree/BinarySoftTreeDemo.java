package com.dpf.tree;

/**
 * 二叉排序树
 * @author dpf
 * @create 2020-10-24 10:05 上午
 * @email 446933040
 */
public class BinarySoftTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySoftTree binarySortTree = new BinarySoftTree(); //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new Node2(arr[i]));
        }

        System.out.println("中序遍历二叉排序树~");
        binarySortTree.midTraverse(); // 1, 3, 5, 7, 9, 10, 12

        binarySortTree.delNode(1);


        int arr2 [] = {1,2,3,4,5,6};
        BinarySoftTree tree = new BinarySoftTree();
        for (int i = 0; i < arr2.length ; i++) {
            tree.add(new Node2(arr2[i]));
        }
        tree.levelTraverse();


    }
}

/**
 * 二叉排序树
 */
class BinarySoftTree{
    private Node2 root;

    public Node2 getRoot(){
        return root;
    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public Node2 search(int value){
        if(root==null){
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node2 searchParent(int value){
        if(root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     * 添加节点
     * @param node
     */
    public void add(Node2 node){
        if(root==null){
            root=node;
        }else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void midTraverse(){
        if(root!=null){
            root.midTraverse();
        }else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    /**
     * 删除节点
     * @param value
     */
    public void delNode(int value){
        if(root==null){
            return;
        }else {
            // 查找需要删除的节点targetNode
            Node2 targetNode = search(value);
            if(targetNode==null){
                // 没有要删除的节点
                return;
            }

            // 如果这颗二叉树只有一个节点
            if(root.left==null && root.right==null){
                root=null;
                return;
            }

            // 查找targetNode的父节点
            Node2 parent = searchParent(value);
            if(targetNode.left==null&&targetNode.right==null){
                // 1.如果要删除的节点是叶子节点

                if(parent.left!=null&&parent.left.value==value){
                    // targetNode是父节点的左子节点
                    parent.left=null;
                }else if(parent.right!=null&&parent.right.value==value){
                    // targetNode节点是父节点的右子节点
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                // 2.删除有两颗子树的节点

                // 找到当前右子树最小节点对应值最小的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else{
                // 3.删除只有一颗子树的节点

                if(targetNode.left!=null){
                    // 如果要删除的节点有左子节点
                    if(parent!=null){
                        if(parent.left.value==value){
                            parent.left = targetNode.left;
                        }else {
                            // targetNode 是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else {
                        // 要删除的节点是根节点
                        root = targetNode.left;
                    }
                }else {
                    // 如果要删除的节点有右子节点
                    if(parent!=null){
                        if(parent.left.value==value){
                            // 如果targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        }else{
                            // 如果targetNode 是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else {
                        // 需要删除的节点有根节点
                        root = targetNode.right;
                    }
                }                           
            }
        }
    }

    /**
     * 找到当前子树最小的节点，将它删除并返回
     * @param node
     * @return
     */
    private int delRightTreeMin(Node2 node) {
        Node2 target = node;

        // 循环查找左子节点，找到最小值
        while (target.left!=null){
            target = target.left;
        }
        // 删除最小节点
        delNode(target.value);
        return target.value;
    }


    public void levelTraverse(){
        if(this.root!=null){
            this.root.levelTraverse(root);
        }
    }





}

/**
 * 节点
 */
class Node2{
    int value;
    Node2 left;
    Node2 right;

    public Node2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找需要删除的节点
     * @param value
     * @return
     */
    public Node2 search(int value){
        if(value == this.value){
            // 找到的节点就是该节点
            return this;
        }else if(value<this.value){
            // 如果左子节点为空
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else {
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value
     * @return
     */
    public Node2 searchParent(int value){
        if((this.left!=null&&this.left.value==value)||
                (this.right!=null&&this.right.value==value)){
            // 当前节点就是要删除节点的父节点
            return this;
        }else {
            if(value<this.value&&this.left!=null){
                // 如果当前值小于当前节点的值，并且当前节点的左子节点不为空
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);
            }else {
                // 没有找到父节点
                return null;
            }
        }
    }

    /**
     * 递归添加节点
     * @param node
     */
    public void add(Node2 node){
        if(node==null){
            return;
        }

        if(node.value<this.value){
            // 当前节点的左子节点为空
            if(this.left==null){
                this.left=node;
            }else {
                // 递归向左子树添加
                this.left.add(node);
            }
        }else {
            // 添加的节点的值大于等于当前节点的值
            if(this.right==null){
                this.right=node;
            }else {
                // 递归右子树添加
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void midTraverse(){
        if(this.left!=null){
            this.left.midTraverse();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.midTraverse();
        }
    }

    /**
     * 获取树的高度
     * @return
     */
    public int depth(Node2 node){


        int leftHeight = 0;
        int rightHeight = 0;

        if(node.left!=null){
            leftHeight = node.left.depth(node.left);
        }
        if(node.right!=null){
            rightHeight = node.right.depth(node.right);
        }

        return (leftHeight>rightHeight?leftHeight:rightHeight)+1;
    }

    /**
     * 层序输出
     * @param node2
     * @param level
     */
    public void levelPrint(Node2 node2,int level){

        if(node2==null||level<1){
            return;
        }
        if(level==1){
            System.out.print(node2+"\t");
        }
        levelPrint(node2.left,level-1);
        levelPrint(node2.right,level-1);
    }


    /**
     * 层序遍历
     * @param node2
     */
    public void levelTraverse(Node2 node2){

        if(node2==null){
            return;
        }

        int height = depth(node2);
        System.out.println();
        for (int i = 1; i < height; i++) {
            levelPrint(node2,i);
            System.out.println();
        }
    }


}
