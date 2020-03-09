package search;

/**
 * @Classname InsertValueSearch
 * @Description TODO 插值查找 动态的mid值
 * @Date 2020/3/5 21:03
 * @Author Danrbo
 */
public class InsertValueSearch {

    private static int COUNT = 0;
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1234};
        int i = insertValueSearch(array, 0, array.length - 1, 1);
        System.out.println(i);
    }

    public static int insertValueSearch(int[] array, int left, int right, int findValue) {
        //由于查找的是有序数组，如果查找的数字大于数组的最后一个或者小于数组的第一个
        // 则不需要往下查询，减少不必要的查询。
        if (left >= right || findValue < array[left] || findValue > array[right]) {
            return -1;
        }
        COUNT++;
        //这里的mid是可变的，比二分查找的次数更少
        //比如二分查找在{1, 2, 3, 4, 5, 6, 7, 8, 9}里找 1 的索引需要 4 次
        //在插值查找里 第一次查找 mid = 0 + (99-0) * 0/(99) = 0，直接 1 次就能定位到 1 的位置
        //大大提高了效率
        int mid = left + (right - left)*(findValue - array[left])/(array[right] - array[left]);
        int midValue = array[mid];
        if (findValue < midValue) {
            return insertValueSearch(array, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            return insertValueSearch(array, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }

}
