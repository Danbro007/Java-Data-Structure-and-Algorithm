package dynamicProgramming;


/**
 * 01背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        /**
         * 每个物品的价值
         */
        int[] val = {0, 3, 4, 5, 8, 10};
        /**
         * 每个物品的重量
         */
        int[] w = {0, 2, 3, 4, 5, 9};
        /**
         * 背包的容量
         */
        int m = 20;
        /**
         * 物品的个数
         */
        int n = w.length;
        int[][] v = new int[w.length][m+1];
        for (int i = 1; i < w.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                /**
                 * 如果要添加的物品重量大于背包的剩余容量则不添加
                 */
                if (w[i] > j){
                    v[i][j] = v[i-1][j];
                }else {
                    /**
                     * 如果容量足够则在取物品和不取物品里比较背包里最大价值
                     */
                    v[i][j] = Math.max(v[i-1][j],val[i] + v[i-1][j - w[i]]);
                }

            }

        }
        for (int i = 0; i <= m; i++) {
            System.out.printf("%d\t", i);
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 1; i < w.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.printf("%d\t", v[i][j]);
            }
            System.out.println();
        }

    }
}
