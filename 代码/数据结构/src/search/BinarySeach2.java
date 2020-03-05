package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname BinarySeach2
 * @Description TODO 二分查找 找到指定元素在数组里的所有位置
 * @Date 2020/3/5 20:05
 * @Author Danrbo
 */
public class BinarySeach2 {

    public static void main(String[] args) {
        int[] array = {1, 1, 1, 3, 5, 6, 7, 8, 9};
        List<Integer> list = binarySearch(array, 0, array.length, 1);
        System.out.println(Arrays.toString(list.toArray()));
    }


    public static List<Integer> binarySearch(int[] array, int left, int right, int findValue) {
        List<Integer> list = new ArrayList<>();
        if (left >= right) {
            return list;
        }
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (findValue < midValue) {
            return binarySearch(array, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            return binarySearch(array, mid + 1, right, findValue);
        } else {
            list.add(mid);
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || array[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (true) {
                if (temp > array.length || array[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
