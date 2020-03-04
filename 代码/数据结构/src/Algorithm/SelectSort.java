package Algorithm;

import java.util.Arrays;

/**
 * @Classname SelectSort
 * @Description TODO
 * @Date 2020/3/3 14:56
 * @Author Danrbo
 */
public class SelectSort implements Algorithm {
    public static void main(String[] args) {
        int[] array = {3, 1, 5, 6, 4, 8, 7, 0, 9, 2};
        new SelectSort().sort(array);
        System.out.println(Arrays.toString(array));
    }

    private String name = "选择排序";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sort(int[] array) {
        //例如 一共有5个数据 只要确定4个数据的排序 最后一个不用再次排序
        for (int i = 0; i < array.length - 1; i++) {
            //暂定当前值的索引是最小值的索引
            int minIndex = i;
            //暂定最小值
            int min = array[i];
            //遍历当前值之后的所有元素,获得最小值和其索引
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            //位置交换
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;

            }
        }
    }

}
