package com.dpf.tree;

/**
 * 平衡二叉树(基于二叉排序树)
 * @author dpf
 * @create 2020-10-26 10:51 下午
 * @email 446933040
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//            int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        // 创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree(); //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node3(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.midTraverse();
        System.out.println("树的高度=" + avlTree.getRoot().height()); //
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
        System.out.println("当前根节点的左子节点="+avlTree.getRoot().left.value);
        System.out.println("当前根节点的右子节点="+avlTree.getRoot().right.value);

    }
}


class AVLTree{
    private Node3 root;

    public Node3 getRoot() {
        return root;
    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public Node3 search(int value){
        if(root==null){
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value
     * @return
     */
    public Node3 searchParent(int value){
        if(root==null){
            return null;
        }else {
            return root.searchParent(value);
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
            Node3 targetNode = search(value);
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
            Node3 parent = searchParent(value);
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
    private int delRightTreeMin(Node3 node) {
        Node3 target = node;

        // 循环查找左子节点，找到最小值
        while (target.left!=null){
            target = target.left;
        }
        // 删除最小节点
        delNode(target.value);
        return target.value;
    }


    /**
     * 添加节点
     * @param node
     */
    public void add(Node3 node){
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
            System.out.println("二叉排序树为空~~~不能遍历");
        }
    }
}

class Node3{
    int value;
    Node3 left;
    Node3 right;

    public Node3(int value) {
        this.value = value;
    }


    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }

    @Override
    public String toString() {
        return "Node3{" +
                "value=" + value +
                '}';
    }

    /**
     * 返回以当前节点为根节点的树的高度
     * @return
     */
    public int height() {

        int leftHeight = 0;
        int rightHeight = 0;
        if(left!=null){
            leftHeight = left.height();
        }
        if(right!=null){
            rightHeight = right.height();
        }
        return (leftHeight>rightHeight?leftHeight:rightHeight)+1;
    }

    /**
     * 左旋转方法
     */
    public void leftRotate(){
        // 以当前节点的值创建新的节点
        Node3 newNode = new Node3(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置为当前节点右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的左子树
        right = right.right;
        //把当前节点的左子树设置成新的节点
        left = newNode;
    }

    /**
     * 右旋转方法
     */
    public void rightRotate(){
        Node3 newNode = new Node3(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 查找要删除节点的值
     * @param value
     * @return 如果找到返回该节点，否则返回Null
     */
    public Node3 search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
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
     * 查找要删除节点的父节点（平衡二叉树是一颗二叉排序树）
     * @param value
     * @return 返回的是要删除节点的父节点，如果没有返回Null
     */
    public Node3 searchParent(int value){

        // 当前节点就是要删除节点的父节点
        if(this.left!=null&&this.left.value==value ||this.right!=null&&this.right.value==value){
            return this;
        }else{// 查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if(value<this.value&&this.left!=null){
                // 向左递归查找
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                // 向右递归查找
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }

    /**
     * 添加节点方法
     * @param node
     */
    public void add(Node3 node){
        if(node==null){
            return;
        }
        // 要插入的节点得值小于当前节点
        if(node.value<this.value){
            if(this.left==null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else{ // 要插入的节点的值大于等于当前节点的值
            if(this.right==null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        //当添加完一个节点后，如果(右子树高度-左子树高度)>1 左旋转
        if(rightHeight()-leftHeight()>1){
            // 如果它的右子树的左子树的高度大于它的右子树的高度
            if(right!=null&&right.leftHeight()>right.rightHeight()){
                // 先对右子树进行右旋转
                right.rightRotate();
                // 然后再对当前节点进行左旋转
                leftRotate();
            }else {
                // 直接进行左旋转
                leftRotate();
            }
        return;
        }

        // 当添加完一个节点后，如果(左子树高度-右子树高度)>1 右旋转
        if(leftHeight()-rightHeight()>1){
            // 如果它的左子树的右子树高度大于它的左子树的高度
            if(left!=null&&left.rightHeight()> left.leftHeight()){
                // 先对当前节点的左子树进行左旋转
                left.leftRotate();
                // 再对当前节点进行右旋转
                rightRotate();
            }else {
                // 直接进行右旋转
                rightRotate();
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
}