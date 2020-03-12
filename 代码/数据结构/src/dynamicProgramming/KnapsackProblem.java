package dynamicProgramming;


/**
 * 01背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        /**
         * 每个物品的价值
         */
        int[] val = {1500, 3000, 2000};
        /**
         * 每个物品的重量
         */
        int[] w = {1, 4, 3};
        /**
         * 背包的容量
         */
        int m = 4;
        /**
         * 物品的个数
         */
        int n = w.length;

        /**
         * 创建一个二维数组，这个数组的第一行和第一列都为0
         *
         *
         *  w =    {1,    4,    3}
         *  val = {1500, 3000, 2000}
         *
         *      0      1      2       3       4
         *      0      0      0       0       0
         *    1 0	1500	1500	1500	1500
         *    4 0	1500	1500	1500	3000
         *    3 0	1500	1500	2000	3500
         */
        int[][] v = new int[n + 1][m + 1];

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                }
            }
        }

        for (int i = 0; i < v[0].length; i++) {
            System.out.printf("%d      ",v[0][i]);

        }
        System.out.println();
        for (int i = 1; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.printf("%d\t",v[i][j]);
            }
            System.out.println();

        }

    }
}
