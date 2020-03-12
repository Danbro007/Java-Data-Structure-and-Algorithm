package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname GraphDemo
 * @Description TODO
 * @Date 2020/3/11 22:10
 * @Author Danrbo
 */
public class GraphDemo {
    public static void main(String[] args) {
        String[] vertexList = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(vertexList.length);
        for (int i = 0; i < vertexList.length; i++) {
            graph.insertVertex(vertexList[i]);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        graph.isVisitedReSet();
        System.out.println("\n广度优先");
        graph.bfs();
    }
}


class Graph {
    /**
     * 存储边的个数
     */
    private int numOfEdge;
    /**
     * 存储顶点集合
     */
    private List<String> vertexList;
    /**
     * 存储图对应的矩阵
     */
    private int[][] edges;
    /**
     * 每个顶点是否被访问过
     */
    private boolean[] isVisited;


    public Graph(int n) {
        this.edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdge = 0;
        isVisited = new boolean[n];
    }

    /**
     * 添加边
     *
     * @param i      顶点
     * @param j      顶点
     * @param weight 权重值
     */
    public void insertEdge(int i, int j, int weight) {
        edges[i][j] = weight;
        edges[j][i] = weight;
        numOfEdge++;
    }

    /**
     * 返回顶点的数量
     *
     * @return 顶点的数量
     */
    public int getNumOfVertex() {
        return this.getVertexList().size();
    }

    public String getVertexByIndex(int index) {
        return this.getVertexList().get(index);
    }

    public int getWeight(int i, int j) {
        return this.edges[i][j];
    }

    public void showGraph() {
        for (int i = 0; i < edges.length; i++) {
            System.out.printf("\t%s", vertexList.get(i));
        }
        System.out.println();
        for (int i = 0; i < edges.length; i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < edges.length; j++) {
                System.out.printf("\t%d", edges[i][j]);
            }
            System.out.println();
        }
    }

    public void insertVertex(String node) {
        vertexList.add(node);
    }

    /**
     * 获取通过顶点的下标获取这个顶点有效的邻节点下标
     *
     * @param index 顶点的下标
     * @return 邻节点下标
     */
    public int getNeighbour(int index) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 通过当前节点获取他邻接点的下标
     *
     * @param i 顶点下标
     * @param j 当前节点的下标
     * @return 当前节点邻接点
     */
    public int getNextNeighbour(int i, int j) {
        for (int k = j + 1; k < edges.length; k++) {
            if (edges[i][k] > 0) {
                return k;
            }
        }
        return -1;
    }

    /**
     * 1、先标记当前节点被访问过
     * 2、获取当前节点的邻接点 m
     * 3、判断 m 是否存在 -1：不存在 其他：m的索引
     * 4、如果邻接点 m 已经被访问过则获取m的邻接点，接着再进入 步骤 3
     * 5、如果邻接点 m 没有被访问过则进入步骤 1
     *
     * @param isVisited 顶点是否被访问
     * @param index     顶点的索引
     */
    private void dfs(boolean[] isVisited, int index) {
        System.out.print(this.getVertexList().get(index) + "---->");
        isVisited[index] = true;
        //获得邻接点
        int m = getNeighbour(index);
        //说明邻接点存在
        while (m != -1) {
            if (isVisited[m]) {
                m = getNextNeighbour(index, m);
            } else {
                dfs(isVisited, m);
            }
        }
    }

    /**
     * 深度优先遍历
     * 遍历vertexList里的每个元素，如果元素没有被访问则进行遍历
     */
    public void dfs() {

        for (int i = 0; i < getVertexList().size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历
     * 1、由于能进入方法的都是没有被访问的，所有先打印当前节点
     * 2、打印完成后把此节点设置被已被访问
     * 3、把此节点加入队列
     * 4、不断在队列里取出值，如果队列为空则退出循环
     * 4.1、先从队列取出一个元素 m ，并通过这个元素 m 获取到它的邻节点 n
     * 4.2、如果n存在则再判断 n 是否被访问过
     * 4.3、如果被访问过则寻找 n 的邻接点 进入 4.1
     * 4.4、如果没被访问过则打印该节点，设置该节点被访问过，并加入队列
     *
     * @param isVisited 顶点是否被访问 true:被访问过 false:没有被访问过
     * @param index     当前顶点的索引
     */
    private void bfs(boolean[] isVisited, int index) {
        int n;
        int m;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getVertexList().get(index) + "---->");
        isVisited[index] = true;
        queue.add(index);
        while (!queue.isEmpty()) {
            m = queue.removeFirst();
            n = getNeighbour(m);
            while (n!=-1){
                if (isVisited[n]){
                    n = getNextNeighbour(index,n);
                }else {
                    System.out.print(getVertexList().get(n) + "---->");
                    isVisited[n] = true;
                    queue.add(n);
                }
            }
        }
    }




    public void isVisitedReSet(){
        isVisited = new boolean[getNumOfVertex()];
    }


    public void bfs() {
        for (int i = 0; i < getVertexList().size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }

        }
    }


    public int getNumOfEdge() {
        return numOfEdge;
    }

    public void setNumOfEdge(int numOfEdge) {
        this.numOfEdge = numOfEdge;
    }

    public List<String> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<String> vertexList) {
        this.vertexList = vertexList;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }
}
