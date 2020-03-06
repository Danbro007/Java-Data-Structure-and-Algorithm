package search;

import java.util.Arrays;

/**
 * @Classname FibonacciSearch
 * @Description TODO 斐波那契数列
 * @Date 2020/3/6 12:49
 * @Author Danrbo
 */
public class FibonacciSearch {

    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(array, 89);
        System.out.println(i);
    }

    /**
     * 用非递归方法生成一个大小为20的斐波那契数列
     *
     * @return 斐波那契数组
     */
    public static int[] fib() {
        int[] fibArray = new int[maxSize];
        fibArray[0] = 1;
        fibArray[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
        return fibArray;
    }

    /**
     * 斐波那契查找算法
     *
     * @param a   数组
     * @param key 要查找的元素
     * @return 返回要查找的元素下标，没有返回-1。
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;
        int mid = 0;//存放mid值
        //f = [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
        int[] f = fib();//获取斐波那契数列

        /**
         * 取斐波那契分割数值的下标
         * high = 5,k = 0 f[0] - 1 = 1 - 1 = 0
         * 第一次while循环：
         *                  由于 5 > 0----> k = 0 + 1 = 1
         * 第二次while循环：
         *                  f[k] - 1 = f[1] - 1 = 0----->5 > 0 -----> k = 1 + 1 = 2
         * 第三次while循环：
         *                  f[k] - 1 = f[2] - 1 = 1----->5 > 1 -----> k = 2 + 1 = 3
         * 第四次while循环：
         *                  f[k] - 1 = f[3] - 1 = 4----->5 > 2 -----> k = 3 + 1 = 4
         * 第五次while循环：
         *                  f[k] - 1 = f[4] - 1 = 4----->5 > 4 -----> k = 4+ 1 = 5
         * 第六次while循环：
         *                  f[k] - 1 = f[5] - 1 = 7----->5 < 7 -----> 跳出while循环
         *     k = 5
         *
         * 把要查询的数组放到一个长度为数组长度最接近的一个斐波那契数的数组里，这样可以把通过斐波那契数方便的把黄金分割点当做mid值
         * 比如:数组长度为 9 ，则新创建的数组长度为 13 ，如果数组长度为 20 则新创建的数组长度为 21
                */
        while (high > f[k] - 1) {
            k++;
        }
        /**
         *
         * 复制数组a到新的数组里 长度为f[k]=f[5] = 8
         * a的数组只有6个元素 {1, 8, 10, 89, 1000, 1234} ，多的两个由0填补
         */
        int[] temp = Arrays.copyOf(a, f[k]);
        /**
         * 由于查找的是有序数组 用0填补会造成数组不是有序的，所以用a数组里的最大值填补空缺的
         * {1, 8, 10, 89, 1000, 1234，1234,1234};
         */
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        /**
         * while循环
         * temp = {1, 8, 10, 89, 1000, 1234，1234,1234}
         * f = [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
         * 第一次：
         *         low = 0, high = 5 , k = 5 , key = 89
         *         由于low <= high --->mid = low + f[k - 1] - 1 = 0 + f[5 - 1] - 1 = 0 + 5 - 1 = 4
         *         temp[4] = 1000 由于key < temp[4]-----> high = 4 - 1 = 3, k = 5 - 1 = 4
         * 第二次：
         *         low = 0, high = 3 , k = 4 , key = 89
         *         由于low <= high --->mid = low + f[k - 1] - 1 = 0 + f[4 - 1] - 1 = 0 + 3 - 1 = 2
         *         temp[2] = 10 由于key > temp[2]-----> low = 2 + 1 = 3, k = 4 - 2 = 2
         * 第三次：
         *         low = 3, high = 3 , k = 2 , key = 89
         *         由于low <= high --->mid = low + f[k - 1] - 1 = 2+ f[2 - 1] - 1 = 2 + 1 - 1 = 2
         *         temp[2] = 10 由于key > temp[10]-----> low = 2 + 1 = 3, k = 3 - 2 = 1
         * 第四次：
         *         low = 3, high = 3 , k = 1 , key = 89
         *         由于low <= high --->mid = low + f[k - 1] - 1 = 3 + f[2 - 1] - 1 = 3 + 1 - 1 = 3
         *         temp[3] = 89 由于key == temp[89]-----> mid = 3 high = 3 ,mid == high----->返回找到的数字索引 mid
         */
        while (low <= high) {
            /**
             *   F(k) = F(k-1) + F(k-2)
             *   F(k)-1 = F(k-1) - 1 + (F(k-2) - 1) + 1
             *   F[k - 1] - 1 为mid的索引值 ，low为数组的起始索引
              */
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                /**
                 *  数组的high变成 mid - 1既 mid的上一个位置
                 */
                high = mid - 1;
                /**
                 * F(k) = F(k-1) + F(k-2)
                 *比如 {1,2,3,4,5,6,7,8}  查找的数字为 key = 3 , mid = 4 既要比较的值为5
                 * 3 在 5 的左边 则通过 F(k - 1) 既 8 - 3 = 5  得知 k - 1
                 * 这里为什么 k 要减 1 ?
                 * 因为我们的数字 key <  temp[mid]，说明在temp[mid]左边，左半边是 F(k-1)的区域
                 */
                k--;
            } else if (key > temp[mid]) {
                /**
                 *  数组的high变成 mid + 1既 mid的下一个位置
                 */
                low = mid + 1;
                /**
                 *比如 {1,2,3,4,5,6,7,8}  查找的数字为 key = 7 , mid = 4 既要比较的值为5
                 * 7 在 5 的右边 则通过 F(k - 2)  既 8 - 5 = 3 得知 k - 2
                 * 这里为什么 k 要减 2 ?
                 * 因为我们的数字 key > temp[mid]，说明在temp[mid]右边，右边半边是 F(k-2的区域
                 */
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
