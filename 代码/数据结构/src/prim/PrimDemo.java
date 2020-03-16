package prim;

import java.util.Arrays;

/**
 * @Classname PrimDemo
 * @Description TODO 普利姆算法
 * @Date 2020/3/15 14:57
 * @Author Danrbo
 */
public class PrimDemo {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        MGraph mGraph = new MGraph(data.length);
        //顶点与顶点之间边的权值,权值为10000表示两点之间不相连
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, data.length, data, weight);
        minTree.show(mGraph);
        minTree.prim(mGraph, 0);
    }
}

/**
 * 创建最小生成树--->村庄的图
 */
class MinTree {
    public void createGraph(MGraph graph, int vertexes, char[] data, int[][] weight) {
        //遍历每个顶点，点与点之间边的权值输入到图里
        for (int i = 0; i < vertexes; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertexes; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 遍历图打印点与点之间的边的权值
     *
     * @param graph 要遍历的图
     */
    public void show(MGraph graph) {
        for (int i = 0; i < graph.vertexes; i++) {
            System.out.println(Arrays.toString(graph.weight[i]));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     *
     * @param graph 要遍历的图
     * @param v     起始点
     */
    public void prim(MGraph graph, int v) {

        //初始化一个节点是否访问过的布尔数组，这里的下标对应节点的下标
        //比如 isVisited[0] = true,说明节点data[0]既节点 'A' 被访问过
        boolean[] isVisited = new boolean[graph.vertexes];
        isVisited[v] = true;
        int minWeight = 10000;
        int h1 = -1;
        int h2 = -1;
        for (int k = 1; k < graph.vertexes; k++) {
            for (int i = 0; i < graph.vertexes; i++) {
                if (isVisited[i]) {
                    for (int j = 0; j < graph.vertexes; j++) {
                        if (!isVisited[j] && minWeight > graph.weight[i][j]) {
                            minWeight = graph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
            }
            System.out.printf("%s---->%s之间的距离为：%d\n", graph.data[h1], graph.data[h2], minWeight);
            isVisited[h2] = true;
            minWeight = 10000;
            h1 = -1;
            h2 = -1;
        }


    }
}


class MGraph {
    /**
     * 表示图的节点个数
     */
    int vertexes;
    /**
     * 存储节点的顺序
     */
    char[] data;
    /**
     * 边的权值
     */
    int[][] weight;

    /**
     * 初始化图
     *
     * @param vertexes 图里的节点个数
     */
    public MGraph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
    }
}
