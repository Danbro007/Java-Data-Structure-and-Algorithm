package Algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Classname SelectSort
 * @Description TODO
 * @Date 2020/3/3 14:56
 * @Author Danrbo
 */
public class SelectSort implements Algorithm {


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
            int minIndex = i + 1;
            //遍历当前值之后的所有元素,获得最小值的索引
            for (int j = i + 1; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    minIndex = j + 1;
                }
            }
            //如果当前值小于array[minIndex]的值，则位置交换
            int temp;
            if (array[minIndex] < array[i]) {
                temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

}
