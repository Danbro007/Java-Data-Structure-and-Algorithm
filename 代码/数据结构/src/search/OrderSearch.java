package search;

/**
 * @Classname OrderSearch
 * @Description TODO 线性查找方法
 * @Date 2020/3/9 11:56
 * @Author Danrbo
 */
public class OrderSearch {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1234};
        int res = orderSearch(array, -1);
        System.out.println("结果为：" + res);
    }

    /**
     * 查找数组里有没有这个元素
     *
     * @param array 查找的数组
     * @param i     查找的元素
     * @return 找到德华返回该元素的索引，没有返回 -1
     */
    public static int orderSearch(int[] array, int i) {
        for (int j = 0; j < array.length; j++) {
            if (array[j] == i) {
                return j;
            }
        }
        return -1;
    }
}
