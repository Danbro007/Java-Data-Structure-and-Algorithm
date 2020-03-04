package Algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Classname InsertSort
 * @Description TODO 插入排序
 * @Date 2020/3/3 20:00
 * @Author Danrbo
 */
public class InsertSort implements Algorithm{
    public static void main(String[] args) {
        int[] array = {3, 9, -10, 10, 20};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     *
     * @param array 需要排序的数组
     */
    public static void insertSort(int[] array) {
        //从数组第二个元素开始遍历
        for (int i = 1; i < array.length; i++) {
            //要插入的元素
            int cur = array[i];
            //要插入元素的前一个元素的索引
            int index = i - 1;
            //要插入元素的往有序队列遍历比较
            // 遍历条件是有序队列没遍历完
            // 并且要插入的元素比有序队列里比较的元素小
            while (index >= 0 && cur < array[index]) {
                array[index + 1] = array[index];
                //index -1 比较元素的光标往前移一位
                index--;
            }
            //遍历完毕 ，插入元素
            //这里的index+1可以这样理解
            //比如：[3,11,5]，
            // 11 开始插入 此时 3 的index = 0
            // 3 比 11 大，11插入到index+1的位置上
            array[index + 1] = cur;
        }
    }

    @Override
    public void getEfficiency() {
        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = (int) Math.random() * 80000;
        }
        Date start1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("插入排序开始:" + format.format(start1));
        insertSort(array);
        Date end = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        System.out.println("插入排序结束:" + format2.format(end));
    }
}
