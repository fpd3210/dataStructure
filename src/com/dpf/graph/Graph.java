package com.dpf.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author dpf
 * @create 2020-11-24 10:50 上午
 * @email 446933040
 */
public class Graph {
    public static void main(String[] args) {
        //测试一把图是否创建ok
        int n = 8;  //结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); //
		graph.insertEdge(1, 2, 1); //
		graph.insertEdge(1, 3, 1); //
		graph.insertEdge(1, 4, 1); //
        graph.showGraph();

        System.out.println();

        String Vertexs2[] = {"v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"};
        //创建图对象
        Graph graph2 = new Graph(n);
        //循环的添加顶点
        for(String vertex: Vertexs2) {
            graph2.insertVertex(vertex);
        }

        graph2.insertEdge(0, 1, 1);
        graph2.insertEdge(1, 3, 1);
        graph2.insertEdge(1, 4, 1);
        graph2.insertEdge(3, 7, 1);
        graph2.insertEdge(4, 7, 1);
        graph2.insertEdge(0, 2, 1);
        graph2.insertEdge(2, 5, 1);
        graph2.insertEdge(2, 6, 1);


        graph2.dfs();
        System.out.println();
        graph2.bfs();


    }


    // 顶点集合
    private ArrayList<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //表时边的数量
    private int numOfEdges;
    //记录是否被访问
    private boolean[] isVisited;

    /**
     * 构造器
     * @param n
     */
    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * 获取节点个数
     * @return
     */
    public int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 获取边的数量
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回节点下标对应的数据
     * @return
     */
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 返回对应权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * 插入节点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 插入边
     * @param v1
     * @param v2
     * @param weight 权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


    /**
     * 得到第一个邻接节点的下标
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     * @param v1 当前遍历节点序号
     * @param v2 v1节点已被访问过的节点序号
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2+1; j < vertexList.size(); j++) {
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历方法
     * @param isVisited
     * @param i
     */
    private void dfs(boolean [] isVisited,int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != - 1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            // 如果w节点已经被访问过,查找下一个邻接节点
            w = getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 广度优先遍历方法
     * @param isVisited
     * @param i
     */
    private void bfs(boolean [] isVisited,int i){
        // 表示队列的头节点对应下标
        int u;
        // 邻接节点w
        int w;
        // 队列，记录节点的访问顺序
        LinkedList<Object> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()){
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w !=-1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                // 以u为前驱节点，找到w后面的下一个邻接节点
                w = getNextNeighbor(u,w);
            }
        }
    }

    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
