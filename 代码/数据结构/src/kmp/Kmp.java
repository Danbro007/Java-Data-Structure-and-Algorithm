package kmp;

import java.util.Arrays;

/**
 * @Classname Kmp
 * @Description TODO Kmp算法
 * @Date 2020/3/13 15:01
 * @Author Danrbo
 */
public class Kmp {


    public static void main(String[] args) {
        int[] next = kmpNext("ABCABD");
        System.out.println(Arrays.toString(next));
    }

    /**
     * dest = ""ABCABD"
     * next[0] = 0;
     * 第一次：
     *         i = 1, j = 0,不符合 j > 0 的条件
     *         dest.charAt(1) = 'B',dest.charAt(0) = 'A'
     *         不符合 dest.charAt(i) == dest.charAt(j)
     *         next[1] = 0
     *         next = {0,0,0,0,0,0}
     * 第二次：
     *         i = 2, j = 0,不符合 j > 0 的条件
     *         dest.charAt(2) = 'C',dest.charAt(0) = 'A'
     *         不符合 dest.charAt(i) == dest.charAt(j)
     *         next[2] = 0
     *         next = {0,0,0,0,0,0}
     * 第三次：
     *         i = 3, j = 0,不符合 j > 0 的条件
     *         dest.charAt(3) = 'A',dest.charAt(0) = 'A'
     *         符合 dest.charAt(i) == dest.charAt(j)
     *          j = 0 + 1
     *          next[3] = j = 1
     *          next = {0,0,0,1,0,0}
     * 第四次：
     *         i = 4, j = 1,
     *         dest.charAt(4) = 'B',dest.charAt(1) = 'B'
     *         符合 j > 0 但是不符合 dest.charAt(i) != dest.charAt(j)的条件
     *         此时的 i = 4, j = 1;
     *         dest.charAt(4) = 'B' ,dest.charAt(1) = 'B'，两者相等
     *         j = 1 + 1 = 2
     *         next[4] = 2
     *         next = {0,0,0,1,2,0}
     * 第五次：
     *         i = 4, j = 2,
     *         dest.charAt(4) = 'B',dest.charAt(2) = 'C'
     *         符合 j > 0 && dest.charAt(i) != dest.charAt(j)的条件,
     *         进入while循环:
     *              j = next[j-1] = next[2-1] = 0,跳出循环
     *         此时的 i = 4, j = 0;
     *         dest.charAt(4) = 'B' ,dest.charAt(0) = 'A'，两者不相等
     *         next[5] = 0
     *         next = {0,0,0,1,2,0}
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;

        }
        return next;
    }
}
