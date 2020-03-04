package Algorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname ShellSort
 * @Description TODO 希尔排序
 * @Date 2020/3/4 10:40
 * @Author Danrbo
 */
public class ShellSort implements Algorithm {

    private String name = "希尔排序-交换方法";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sort(int[] array) {
        int temp;
        //确定步长，比如一个数组{8, 9, 1, 7, 2, 3, 5, 4, 6, 0}有十个元素，
        //第一次步长为10 / 2 = 5，既有5组 每组有2个元素{8,3},{9,5}{1,4} {7,6} {2,0}
        //然后在各自组里交换排序，{3,8}，{5,9} {1,4} {6,7} {0,2}---->{3,8,5,9,1,4,6,7,0,2}
        //第二次的步长为 5 / 2 = 2，既有2组 每组有5个元素{3,8,5,9,1} {4,6,7,0,2}
        //排序交换{1,3,5,8,9} {0,2,4,6,7}---->{1,3,5,8,9,0,2,4,6,7}
        //第三次的步长为 2 / 2 = 1，既有1组 每组有10个元素 {1,3,5,8,9,0,2,4,6,7}
        //排序交换得到{0,1,2,3,4,5,6,7,8,9}
        //此时的步长为 1 / 2 = 0 for循环结束
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //遍历步长以后的元素
            //比如{8, 9, 1, 7, 2, 3, 5, 4, 6, 0}的第一次步长为5
            //则遍历{3, 5, 4, 6, 0}
            for (int i = gap; i < array.length; i++) {
                //在各自组内元素循环交换排序
                //比如第一次步长为 5
                // j = 5 - 5 = 0 让array[0] = 8 和 array[3] = 9 进行冒泡排序
                // j = 6 - 5 = 1 让array[1] = 9 和 array[6] = 5 进行冒泡排序
                // j = 7 - 5 = 2 让array[2] = 1 和 array[7] = 4 进行冒泡排序
                // j = 8 - 5 = 3 让array[3] = 7 和 array[8] = 6 进行冒泡排序
                // j = 9 - 5 = 4 让array[4] = 2 和 array[9] = 0 进行冒泡排序
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

}


class ShellSort2 implements Algorithm {
    private String name = "希尔排序-移位方法";

    @Override
    public String getName() {
        return name;
    }

    /**
     * 使用移位法
     *
     * @param array 要排序的数组
     */
    @Override
    public void sort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                //当前要比较的元素索引
                int index = i;
                //当前要比较的值
                int temp = array[index];
                //在各自组里展开插入排序
                //index - gap >= 0 是判断分组里当前元素的下一个元素索引是否大于等于0
                // 没有就跳出while循环
                while (index - gap >= 0 && temp < array[index - gap]) {
                    array[index] = array[index - gap];
                    index -= gap;
                }
                //找到这个元素的位置了，插入元素
                array[index] = temp;
            }

        }
    }
}
