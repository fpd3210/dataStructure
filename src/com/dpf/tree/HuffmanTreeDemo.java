package com.dpf.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树（最优二叉树）
 * @author dpf
 * @create 2020-10-20 9:55 下午
 * @email 446933040
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preTraverse();
    }


    /**
     * 构建赫夫曼树
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int arr[]){

        List<Node> nodes = new ArrayList<Node>();
        for (int value:arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size()>1){
            // 排序从小到大
            Collections.sort(nodes);

            // 取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        return nodes.get(0);
    }

}


/**
 * 节点类，实现Comparable接口让Node对象持续排序
 */
class Node implements Comparable<Node>{
    // 节点权值
    int value;
    // 指向左子节点
    Node left;
    // 指向右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preTraverse(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preTraverse();
        }
        if(this.right!=null){
            this.right.preTraverse();
        }
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value-o.value;
    }
}

