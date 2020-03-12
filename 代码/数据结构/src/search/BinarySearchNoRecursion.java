package search;

/**
 * @Classname BinarySearchNoRecursion
 * @Description TODO 二分查找非递归版
 * @Date 2020/3/12 13:35
 * @Author Danrbo
 */
public class BinarySearchNoRecursion {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int res = binarySearch(array, 11);
        System.out.println("index= " + res);
    }


    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;

    }
}
