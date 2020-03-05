package algorithm;

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

    /**
     * n个元素，一个个的遍历 n - 1次比较，最后一个不用比较因为他是最大的
     * 例如:{3, 1, 5, 6, 4, 8, 7, 0, 9, 2}
     * 第一次遍历：
     *          i = 0,min = 3 ,minIndex = 0 ------> 发现 0 是最小值，它的索引为 7
     *          此时的min = 0 minIndex = 7
     *          遍历结束，交换位置------>{0, 1, 5, 6, 4, 8, 7, 3, 9, 2}
     * 第二次遍历：
     *          i = 1，min = 1,minIndex = 1------>发现 1 是最小值，它的索引为 1
     *          此时的min = 1 minIndex = 1
     *          遍历结束，由于minIndex = i ，不用交换位置------>{0, 1, 5, 6, 4, 8, 7, 3, 9, 2}
     * 第三次遍历：
     *          i = 2, min = 2 ,minIndex = 5------> 发现 2 是最小值 ，它的索引为 9
     *          此时的min = 2 minIndex = 9
     *          遍历结束，交换位置------>{0, 1, 2, 6, 4, 8, 7, 3, 9, 5}
     *
     *  。。。。。。。。
     *
     * 第九次遍历结束：
     *          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
     */
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
            //如果最小值有变化则交换位置
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;

            }
        }
    }

}
