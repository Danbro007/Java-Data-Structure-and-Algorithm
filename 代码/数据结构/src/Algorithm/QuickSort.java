package Algorithm;

import java.util.Arrays;

/**
 * @Classname QuickSort
 * @Description TODO
 * @Date 2020/3/4 13:28
 * @Author Danrbo
 */
public class QuickSort implements Algorithm {

    private String name = "快速排序";



    public static void main(String[] args) {
        int[] array = {3, 1, 5, 6, 4, 8, 7, 0, 9, 2};
        new QuickSort().sort(array);
        System.out.println(Arrays.toString(array));
    }


    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * {3, 1, 5, 6, 4, 8, 7, 0, 9, 2}
     * midValue = 3 ，low = i = 0， high = j =10 - 1 = 9
     * 第一次遍历:
     *      先从右边开始向左遍历---->array[9] =  2 ，2与3比较小于3，跳出while循环 j = 9
     *                          目前 j = 9
     *      从左边开始向右边遍历---->array[0] = 3 ，3与3比较 符合小于等于 3,此时 i = i + 1 = 0 + 1 = 1，既 i 向右移动一位
     *                        ---->array[1] = 1 小于 3  i = i + 1 = 1+ 1 = 2 跳出循环
     *                          目前 i = 2
     *
     *      判断 i 与 j 的大小：2 < 9 ----->两者交换位置 {3, 1, 2, 6, 4, 8, 7, 0, 9, 5}
     * 第二次遍历：
     *      先从右边开始向左遍历---->array[9] =  5 ，2 与 3 比较大于3 ，j = j - 1 = 9 - 1 = 8,j向左移动一位
     *                        ---->array[8] = 9，9 与 3 比较 大于3 ，j = j -1 = 8 -1 = 7，j向左移动一位
     *                        ---->array[7] = 0，0 与 3 比较 小于3， 跳出循环
     *                          目前 j = 7
     *
     *      从左边开始向右边遍历---->array[2] = 2 ，2与3比较 小于3,此时 i = i+  1 = 2 + 1 = 3，既 i 向左移动一位
     *                       ---->array[3] = 6 ，6与3比较 大于3,跳出循环
     *                          目前 i = 3
     *      判断 i 与 j 的大小：3 < 7 两者交换位置 {3, 1, 2, 0, 4, 8, 7, 6, 9, 5}
     *
     * 第三次遍历：
     *     先从右边开始向左遍历---->array[7] =  6，6与 3 比较大于3 ，j = j - 1 = 7 - 1 = 6,j向左移动一位
     *                         ---->array[6] = 7，7 与 3 比较大于3 ，j = j -1 = 6 -1 = 5，j向左移动一位
     *                         ---->array[5] = 8，8 与 3 比较大于3 ，j = j -1 = 5 -1 = 4，j向左移动一位
     *                         ---->array[4] = 4，4 与 3 比较大于3 ，j = j -1 = 4 -1 = 3，j向左移动一位
     *                         ---->此时 i = 3 ,j = 3  不符合 i < j 跳出while循环
     *                         目前  j = 3
     *      从左边开始向右边遍历----> 此时 i = 3 ,j = 3  不符合 i < j 跳出while循环
     *                          目前 i = 3
     *      判断 i 与 j 的大小： i = 3, j = 3 不符合 i < j  不能交换
     *      while(i < j)跳出 此时的{3, 1, 2, 0, 4, 8, 7, 6, 9, 5}
     *      此时low = 0 , high = 9 ,i = 3 , j = 3
     *      找到了midValue所在的位置 3 , array[3]和midValue交换位置
     *      array[0] = array[3] = 0 {0, 1, 2, 0, 4, 8, 7, 6, 9, 5}
     *      array[3] = midValue = 3 {0, 1, 2, 3, 4, 8, 7, 6, 9, 5}
     *      此时midValue既 3 的左边是比 3 小 ，3 的右边是比 3 大的
     *      分成两组递归排序:
     *              left = array[0]-----array[2] = {0,1,2}
     *              right = array[4]----array[9] = {4,8,7,6,9,5}
     *
     *
     * @param arr 排序的数组
     * @param low 数组左端的索引值
     * @param high 数组右端的索引值
     */
    public void quickSort(int[] arr, int low, int high) {
        //数组里只有一个元素 结束递归
        if (low >= high) {
            return;
        }
        //数组低位的起始光标
        int i = low;
        //数组高位的起始光标
        int j = high;
        //temp就是基准位
        int midValue = arr[low];
        //临时变量
        int temp;

        while (i < j) {
            //先看右边，依次往左递减
            while (midValue <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (midValue >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = midValue;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }


    @Override
    public String getName() {
        return this.name;
    }
}
