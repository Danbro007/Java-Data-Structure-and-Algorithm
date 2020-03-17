package dijkstraAlgorithm;

import java.util.Arrays;

/**
 * @Classname DijkstraAlgorithm
 * @Description TODO Dijkstra算法
 * @Date 2020/3/16 13:45
 * @Author Danrbo
 */
public class DijkstraAlgorithm {
    private static final int N = 65535;


    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertexes.length][vertexes.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertexes, matrix);
        graph.show();
        graph.dsj(6);
        graph.visitedVertex.show();
    }


}


class Graph {
    private char[] vertexes;
    private int[][] matrix;
    public VisitedVertex visitedVertex;

    public Graph(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    /**
     * 打印图
     */
    public void show() {
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
     * Dijkstra算法
     *
     * @param index 出发顶点的下标
     */
    public void dsj(int index) {
        visitedVertex = new VisitedVertex(vertexes.length, index);
        //更新出发节点它周围的节点与出发节点的距离和它们的前驱节点
        update(index);
        //依次找出 出发点index 到 i 的最小距离的节点 j ，找到后并标记为访问过
        //既 alreadyVisited[i] = true
        //更新其他点到 j 距离和前驱节点
        for (int i = 1; i < vertexes.length; i++) {
            int j = visitedVertex.updateArr();
            update(j);
        }
    }

    /**
     * 更新指定点的距离和前驱节点
     * 如果遇到距离变短则更新及它的前驱节点
     *
     * @param index 要更新的点的下标
     */
    private void update(int index) {
        int len;
        //循环遍历当前节点的周围其他节点
        for (int i = 0; i < matrix[index].length; i++) {
            //len = 从出发顶点到index的距离 + index到i的距离
            len = this.visitedVertex.getDis(index) + matrix[index][i];
            //如果当前节点index的周围节点 i 还没被访问过并且出发节点到 i 的长度大于 len,
            // 既出发节点到 i 的距离 大于 出发节点到 index  + index 到 i 的和,
            // 说明出发节点到 index  + index 到 i 的和距离更短
            // 则更新 i 的前驱结点为index 和 i 到出发节点的距离为 len
            if (!this.visitedVertex.isVisited(i) && len < this.visitedVertex.getDis(i)) {
                this.visitedVertex.updatePre(i, index);
                this.visitedVertex.updateDis(i, len);
            }
        }
    }
}


class VisitedVertex {
    /**
     * 每个点是否被访问的数组
     */
    public boolean[] alreadyVisited;
    /**
     * 每个点的距离到出发顶点的距离
     * 比如：A-->C的距离为5，B—>C的距离为3 ----->dis = {5,3,0}
     */
    private int[] dis;
    /**
     * 存储每个点前驱节点的下标
     */
    private int[] pre_visited;

    /**
     * 创建访问节点的数组
     *
     * @param length 节点个数
     * @param index  出发节点的下标
     */
    public VisitedVertex(int length, int index) {
        alreadyVisited = new boolean[length];
        alreadyVisited[index] = true;
        dis = new int[length];
        pre_visited = new int[length];
        Arrays.fill(dis, 65535);
        dis[index] = 0;
    }

    /**
     * 查询节点是否被访问过
     *
     * @param index 要查询的节点下标
     * @return true:被访问过 false:没有被访问过
     */
    public boolean isVisited(int index) {
        return alreadyVisited[index];
    }

    /**
     * 更新节点的与出发节点的距离
     *
     * @param index    要更新的节点下标
     * @param distance 更新的距离
     */
    public void updateDis(int index, int distance) {
        dis[index] = distance;
    }

    /**
     * 更新节点的前驱结点
     *
     * @param index 要更新的节点
     * @param pre   前驱节点
     */
    public void updatePre(int index, int pre) {
        pre_visited[index] = pre;
    }

    /**
     * 获取节点到出发节点的距离
     *
     * @param index 节点
     * @return 节点到出发节点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }


    /**
     * 找到最短距离节点的下标
     *
     * @return 最短距离的下标
     */
    public int updateArr() {
        int min = 65535;
        int index = 0;
        //遍历alreadyVisited，如果节点没被访问过并且 出发节点到 i 节点的距离小于min
        //则说明 出发节点到 i 的距离最短 index = i
        for (int i = 0; i < alreadyVisited.length; i++) {
            if (!alreadyVisited[i] && min > getDis(i)) {
                min = getDis(i);
                index = i;
            }
        }
        //标记此最小节点被访问过
        alreadyVisited[index] = true;
        return index;
    }


    public void show() {
        System.out.print("alreadyVisited：");
        System.out.println(Arrays.toString(alreadyVisited));
        System.out.println();
        System.out.print("dis:");
        System.out.println(Arrays.toString(dis));
        System.out.println();
        System.out.print("pre_visited:");
        System.out.println(Arrays.toString(pre_visited));
    }
}




