package Algorithm;

import java.util.Arrays;

/**
 * @Classname SelectSort
 * @Description TODO
 * @Date 2020/3/3 14:56
 * @Author Danrbo
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {3, 9, -10, 10, 20};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            //暂定光标指向的索引是最小值的索引
            int minIndex = i + 1;
            //遍历光标后的所有元素,获得最小值的索引
            for (int j = i + 1; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    minIndex = j + 1;
                }
            }
            //交换光标指向的值和找到的最小值位置
            int temp;
            if (array[minIndex] < array[i]) {
                temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }


}
