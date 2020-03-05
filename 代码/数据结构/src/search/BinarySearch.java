package search;

/**
 * @Classname BinarySearch
 * @Description TODO 二分查找 找到元素就返回它的索引，没找大则返回-1
 * @Date 2020/3/5 19:57
 * @Author Danrbo
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        int res = binarySearch(array, 0, array.length, 13);
        System.out.println(res);
    }

    public static int binarySearch(int[] array, int left, int right, int findValue) {
        if (left >= right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (findValue < midValue) {
            return binarySearch(array, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            return binarySearch(array, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }
}
