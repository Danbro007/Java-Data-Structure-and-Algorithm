package floydAlgorithm;

import java.util.Arrays;

/**
 * @Classname FloydAlgorithm
 * @Description TODO 弗洛伊德算法
 * @Date 2020/3/17 10:42
 * @Author Danrbo
 */
public class FloydAlgorithm {
    private static int N = 65535;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertexes.length][vertexes.length];
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        Graph graph = new Graph(vertexes, matrix);
        graph.show();
        graph.floyd();
        System.out.println("弗洛伊德算法开始");
        graph.show();
    }


}


class Graph {
    private char[] vertexes;
    private int[][] dis;
    private char[][] pre;

    public Graph(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.dis = matrix;
        this.pre = new char[vertexes.length][vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            Arrays.fill(pre[i], vertexes[i]);
        }
    }

    public void show() {
        for (int i = 0; i < vertexes.length; i++) {
            System.out.printf("%10s", vertexes[i]);
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < vertexes.length; i++) {
            System.out.printf("%s", vertexes[i]);
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%10d", dis[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < vertexes.length; i++) {
            System.out.printf("%11s", vertexes[i]);
        }
        System.out.println();
        for (int i = 0; i < vertexes.length; i++) {
            System.out.printf("%s", vertexes[i]);
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%11s", pre[i][j]);
            }
            System.out.println();
        }
    }


    public void floyd() {
        int len;
        //从中点开始遍历
        for (int k = 0; k < dis.length; k++) {
            //遍历起点
            for (int i = 0; i < dis.length; i++) {
                //遍历终点
                for (int j = 0; j < dis.length; j++) {
                    //len = 起点到中点 + 中点到终点
                    len = dis[i][k] + dis[k][j];
                    //如果len 小于 起点到终点的长度 则更新路径
                    if (len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }

                }
            }

        }
    }
}
