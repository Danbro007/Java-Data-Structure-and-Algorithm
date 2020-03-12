package divideAndConquer;

/**
 * @Classname HanoiTower
 * @Description TODO 解决汉诺塔问题
 * @Date 2020/3/12 14:03
 * @Author Danrbo
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');

    }

    /**
     * 1、当只要一个盘子时，则从A--->C
     * 2、当盘子的总数大于等于 2 个时，这里假设是 n 个
     * 2.1、 我们可以把这 n 个盘子分成两个部分，第1个到第n-1个为一个部分我们这里称呼为 i，第 n 个盘既最底部的盘这一部分我们称呼为 j。
     * 2.2、我们先把 i 这部分从A-->B（这个过程是个递归过程，回到步骤 2），然后再把最后一个既 j 部分的第 n 个盘从A-->C
     * 2.2、此时 B 的元素是 i 的元素既第 1 个到第 n-1 个，然后我们把在 B 的 i 部分的元素都从B--->C(这个过程是个递归过程)
     * @param num 盘的个数
     * @param a A柱
     * @param b B柱
     * @param c C柱
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.printf("第1个盘，从%s---->%s\n", a, c);
        } else {
            hanoiTower(num - 1, a, c, b);
            System.out.printf("第%d个盘，从%s---->%s\n", num, a, b);
            hanoiTower(num - 1, b, a,c);
        }

    }
}



