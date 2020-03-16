package kruskalCase;

import java.util.Arrays;

/**
 * @Classname KruskalCase
 * @Description TODO 克鲁斯科尔算法
 * @Date 2020/3/16 9:51
 * @Author Danrbo
 */
public class KruskalCase {
    /**
     * 边的个数
     */
    private int edgeNum;
    /**
     * 顶点数组
     */
    private char[] vertexes;

    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    private Edata[] edges;

    private static final int INF = 1000;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {{0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};


        KruskalCase kruskalCase = new KruskalCase(vertexes, matrix);
        kruskalCase.print();
        kruskalCase.edgesSort();
        kruskalCase.kruskal();
    }


    public KruskalCase(char[] vertexes, int[][] matrix) {
        /**
         * 初始化顶点和边的个数
         */
        int len = vertexes.length;
        this.vertexes = new char[len];
        this.matrix = new int[len][len];
        /**
         * 复制和拷贝边和顶点数组
         */
        for (int i = 0; i < vertexes.length; i++) {
            this.vertexes[i] = vertexes[i];
            for (int j = 0; j < vertexes.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        /**
         * 统计边的个数
         */
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] < INF) {
                    edgeNum++;
                }
            }
        }
        this.edges = getEdges();
    }

    public void edgesSort() {
        Arrays.sort(this.edges);
    }

    /**
     * 打印邻接矩阵
     */
    public void print() {
        for (int i = 0; i < vertexes.length; i++) {
            System.out.printf("%10s", vertexes[i]);
        }
        System.out.println();
        for (int i = 0; i < vertexes.length; i++) {
            System.out.printf("%s", vertexes[i]);
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%10d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 输入顶点返回该顶点的下标，如：输入 'A' 返回 0
     *
     * @param ch 字符
     * @return 字符的下标
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 把邻接矩阵里的数据实例化成Edata对象
     *
     * @return Edata对象数组
     */
    private Edata[] getEdges() {
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edata(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取顶点的终点，用于后面判断两个顶点的终点是否相同
     *
     * @param ends ends，存储了各个顶点对应的终点，动态生成。
     * @param i    顶点的下标
     * @return 顶点的终点
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 克鲁斯卡尔算法
     * 第一次：
     *          ends = {0,0,0,0,0,0,0,0,0,0,0,0,0}
     *          startPos = 4 ----> 4 的终点为 4，既它自身
     *          endPos = 5 -----> 5 的终点为 5，既它自身
     *          startPos的终点设置为 endPos的终点
     *          ends[4] = 5
     *          ends = {0,0,0,0,5,0,0,0,0,0,0,0,0}
     *第二次:
     *          ends = {0,0,0,0,5,0,0,0,0,0,0,0,0}
     *          startPos = 2 ----> 2 的终点为 2，既它自身
     *          endPos = 3 -----> 3 的终点为 3，既它自身
     *          startPos的终点设置为 endPos的终点
     *          ends[2] = 3
     *          ends = {0,0,3,0,5,0,0,0,0,0,0,0,0}
     *第三次:
     *          ends = {0,0,3,0,5,0,0,0,0,0,0,0,0}
     *          startPos = 3 ----> 3 的终点为 3，既它自身
     *          endPos = 4 -----> 4 的终点为 5  ！= 0----->i = 5 ---->ends[5] = 0 终点为 5
     *          startPos的终点设置为 endPos 的终点
     *          ends[3] = 5
     *          ends = {0,0,3,5,5,0,0,0,0,0,0,0,0}
     *第四次:
     *          ends = {0,0,3,5,5,0,0,0,0,0,0,0,0}
     *          startPos = 2 ----> 2 的终点为  ends[2] = 3 != 0----->  i = 3,ends[3] = 5 != 0 ---->i = 5,ends[5] = 0---->终点为 5
     *          endPos = 4 -----> 4 的终点为 5
     *          由于 2 和 4 的终点相同 ----->形成回路
     *          ends = {0,0,3,5,5,0,0,0,0,0,0,0,0}
     *第五次:
     *          ends = {0,0,3,5,5,0,0,0,0,0,0,0,0}
     *          startPos = 2 ----> 2 的终点为  ends[2] = 3 != 0----->  i = 3,ends[3] = 5 != 0 ---->i = 5,ends[5] = 0---->终点为 5
     *          endPos = 5 -----> 5 的终点为 5,既它自身
     *          由于 2 和 5 的终点相同 ----->形成回路
     *          ends = {0,0,3,5,5,0,0,0,0,0,0,0,0}
     *第六次:
     *          ends = {0,0,3,5,5,0,0,0,0,0,0,0,0}
     *          startPos = 1 ----> 1 的终点为 1 ,既它自身
     *          endPos = 5 -----> 5 的终点为 5,既它自身
     *          startPos的终点设置为 endPos 的终点
     *          ends[1] = 5
     *          ends = {0,5,3,5,5,0,0,0,0,0,0,0,0}
     *第七次:
     *          ends = {0,5,3,5,5,0,0,0,0,0,0,0,0}
     *          startPos = 4 ----> 5 的终点为  ends[4] = 5 != 0-----> i = 5,ends[5] = 0---->终点为 5
     *          endPos = 6 -----> 6 的终点为 6,既它自身
     *          startPos的终点设置为 endPos 的终点
     *          ends[5] = 6
     *          ends = {0,5,3,5,5,6,0,0,0,0,0,0,0}
     *第八次:
     *          ends = {0,5,3,5,5,6,0,0,0,0,0,0,0}
     *          startPos = 5 ----> 5 的终点为  ends[5] = 6 != 0-----> i = 6,ends[6] = 0---->终点为 6
     *          endPos = 6 -----> 6 的终点为 6,既它自身
     *          由于 5 和 6 的终点相同 ----->形成回路
     *          ends = {0,5,3,5,5,6,0,0,0,0,0,0,0}
     */
    public void kruskal() {
        int index = 0;
        Edata[] res = new Edata[this.edgeNum];
        int[] ends = new int[this.edgeNum];
        for (int i = 0; i < this.edges.length; i++) {
            int startPos = getPosition(edges[i].start);
            int endPos = getPosition(edges[i].end);
            int m = getEnd(ends, startPos);
            int n = getEnd(ends, endPos);
            /**
             * 如果两个顶点的终点不相等，说明没有回路。
             */
            if (m != n) {
                ends[m] = n;
                res[index++] = edges[i];
            }
        }
        System.out.println("生成最小生成树：");
        for (int i = 0; i < res.length; i++) {
            if (res[i] != null) {
                System.out.println(res[i]);
            }
        }
    }

}

/**
 * 它的对象是边
 */
class Edata implements Comparable {
    /**
     * 边的起点
     */
    char start;
    /**
     * 边的终点
     */
    char end;
    /**
     * 边的权值
     */
    int weight;

    public Edata(char start, char end, int wight) {
        this.start = start;
        this.end = end;
        this.weight = wight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "<" + start +
                ", " + end +
                ">, weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Edata o1 = (Edata) o;
        return this.weight - o1.weight;
    }
}
