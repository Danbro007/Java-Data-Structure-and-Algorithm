package Algorithm;

import java.util.Arrays;

/**
 * @Classname BubbleSort
 * @Description TODO 冒泡排序
 * @Date 2020/3/3 13:57
 * @Author Danrbo
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 9, -10, 10, 20};

        System.out.println("排序前："+Arrays.toString(array));
        bubbleSort(array);
        System.out.println("排序后："+Arrays.toString(array));

    }

    public static void bubbleSort(int[] array) {
        //遍历的次数
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            int temp;
            //j为前面一个指针的位置 j为后一个指针的位置
            // 例如 要排序一个size为5的数组
            //开始第一次遍历 既 i = 0
            // 前一个指针从 0 开始，后一个指针从 1 开始,逐个遍历
            //直到 array.length - i - 1 既 5 -0 -1 = 4 既 j = 3 j+1 = 4
            //排序得到的最大数字放到array[4]
            //第二次遍历 i = 1
            // 5 -1 -1 = 3 既 j = 2 j+1 = 3
            //排序得到的第二大的数字放到array[3]
            //余此类推
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            //没有发生过交换则break
            if (flag) {
                break;
            }
        }
    }
}
