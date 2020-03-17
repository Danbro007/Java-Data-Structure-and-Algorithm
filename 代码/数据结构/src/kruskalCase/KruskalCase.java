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
    /**
     * 存放边的对象数组
     */
    private Edata[] edges;
    /**
     * 如果一个边的weight 为 INF，说明这个边的start和end不相通
     */
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
     * 通过ends数组查找到该顶点的终点，如果查找到该顶点的终点为 0 说明
     * 该点是刚加入，通过边的end当做该点的终点
     * 如果ends中有该点的终点，比如有A、B、C、D 四个点 他们的ends数组为{1,2,3,0}
     * A------>B-------->C------>D，我们要查找顶点A的终点，我们先找到A的终点B，然后再找B的终点C,最后找到C的终点D，既ends[3] = 0
     * A的终点为D
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
     * 1、先遍历edges数组，获取每个边的start和end的在顶点数组的下标
     * 2、通过start和end获取他们的终点
     * 3、如果两个顶点的终点不相等，说明没有回路。把边的end的终点设置为start的终点
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
