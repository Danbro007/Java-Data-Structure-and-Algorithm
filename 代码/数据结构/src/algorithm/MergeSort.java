package algorithm;

import java.util.Arrays;

/**
 * @Classname MergeSort
 * @Description TODO 归并排序
 * @Date 2020/3/4 21:12
 * @Author Danrbo
 */
public class MergeSort implements Algorithm {
    private String name = "归并排序";

    public static void main(String[] args) {
        int[] array = {9, 1, 4, 2, 8, 6, 3, 5, 7, 0};
        new MergeSort().sort(array);
        System.out.println(Arrays.toString(array));
    }


    @Override
    public void sort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array,0,array.length-1,temp);

    }

    /**
     * 先分割数组
     * 比如：{9, 1, 4, 2, 8, 6, 3, 5, 7, 0}分割到最后为每个数组内只有一个元素
     * @param array 要排序的数组
     * @param left 数组的起始索引
     * @param right 数组的终止索引
     * @param temp 存储排序好后元素的临时数组
     */
    public void mergeSort(int[] array,int left,int right,int[] temp) {
        if (left < right){
            int mid = (left+right) / 2;
            mergeSort(array,left,mid,temp);
            mergeSort(array,mid+1,right,temp);
            merge(array,left,mid,right,temp);
        }
    }

    /**
     * 把两个有序数组合并在一起并排序
     *{9, 1, 4, 2, 8, 6, 3, 5, 7, 0}
     * 第一次比较合并：
     *              left = 0,right = 1
     *              i = 0 ,mid = 0 ,j = mid + 1 = 1，index = 0;
     *              array[0] = 9 和 array[1] = 1 ,
     *              比较好后放到temp数组里 temp[0] = 1,temp[1] = 9,此时的 i = 0+1 = 1,j = 1 + 1 = 2,index = 2
     *              把index  = 0，tempLeft = left = 0, right = 1,while循环 tempLeft <= right,
     *              temp = {1,9,0,0,0,0,0,0,0,0}
     *              第一次while循环----->array[0] = temp[0],tempLeft = 0 + 1 = 1,index = 0 + 1 = 1;
     *                     {1, 1, 4, 2, 8, 6, 3, 5, 7, 0}
     *              第二次while循环----->array[1] = temp[1] ,tempLeft = 1 + 1 = 2,index = 1 + 1 = 2,
     *                     {1, 9, 4, 2, 8, 6, 3, 5, 7, 0}
     * 第二次比较合并：
     *              {1, 9, 4, 2, 8, 6, 3, 5, 7, 0}
     *              left = 0，right = 2
     *              array[0] = 1、 array[1] = 9 、array[2] = 4, i = 0 ,mid = 1 ,j = mid + 1 = 2，index = 0;
     *              比较好后放到temp数组里 temp[0] = 1、temp[1] = 4、temp[2] = 9 ,此时的 i = 0 + 1 + 1= 2,j = 2 + 1 = 3,index = 3
     *              把index  = 0，tempLeft = left = 0, right = 2,while循环 tempLeft <= right,
     *              temp = {1,4,9,0,0,0,0,0,0,0}
     *              第一次while循环----->array[0] = temp[0] = 1,tempLeft = 0 + 1 = 1,index = 0 + 1 = 1;
     *                     {1, 1, 4, 2, 8, 6, 3, 5, 7, 0}
     *              第二次while循环----->array[1] = temp[1] = 4 ,tempLeft = 1 + 1 = 2,index = 1 + 1 = 2,
     *                     {1, 4, 4, 2, 8, 6, 3, 5, 7, 0}
     *              第三次while循环----->array[2] = temp[2] = 9 ,tempLeft = 2 + 1 = 3,index = 1 + 1 = 3,
     *                     {1, 4, 9, 2, 8, 6, 3, 5, 7, 0}
     * 第三次比较合并：
     *              {1, 4, 9, 2, 8, 6, 3, 5, 7, 0}
     *              left = 3，right = 4,i = 3 ,mid = 3 ,j = mid + 1 = 4，index = 0;
     *              array[3] = 2、array[4] = 8
     *              比较好后放到temp数组里 temp[0] = 2、temp[1] = 8 此时的 i = 3 + 1 = 4 ,j = 4 + 1 = 5,index = 2
     *              把index  = 0，tempLeft = left = 3, right = 4,while循环 tempLeft <= right,
     *              temp = {2,8,9,0,0,0,0,0,0,0}
     *              tempLeft = left = 3 、right = 4
     *              第一次while循环----->array[3] = temp[0],tempLeft = 3 + 1 = 4,index = 0 + 1 = 1;
     *                      {1, 4, 9, 2, 8, 6, 3, 5, 7, 0}
     *              第二次while循环----->array[4] = temp[1] ,tempLeft = 1 + 1 = 2,index = 1 + 1 = 2,
     *                     {1, 4, 9, 2, 8, 6, 3, 5, 7, 0}
     * 第四次比较合并：
     *              {1, 4, 9, 2, 8, 6, 3, 5, 7, 0}
     *              left = 0，right = 4,i = 0 ,mid = 2 ,j = mid + 1 = 3，index = 0;
     *              array[0] = 1、array[1] = 4、array[2] = 9、array[3] = 2、array[4] = 8
     *              比较好后放到temp数组里 temp = {1,2,4,8,9,0,0,0,0,0} 此时的 i = 3,j = 5,index = 5
     *              把index  = 0，tempLeft = left = 0, right = 4,while循环 tempLeft <= right,
     *              temp = {1,2,4,8,9,0,0,0,0,0}
     *              tempLeft = left = 0、right = 4
     *              第一次while循环----->array[0] = temp[0] = 1,tempLeft = 0 + 1= 1,index = 0 + 1 = 1;
     *                      {1, 4, 9, 2, 8, 6, 3, 5, 7, 0}
     *              第二次while循环----->array[1] = temp[1] = 2  ,tempLeft = 1 + 1 = 2,index = 1 + 1 = 2,
     *                     {1, 2, 9, 2, 8, 6, 3, 5, 7, 0}
     *              第三次while循环----->array[2] = temp[2] = 4,tempLeft = 2 + 1 = 3,index = 2 + 1 = 3,
     *                     {1, 2, 4, 2, 8, 6, 3, 5, 7, 0}
     *              第四次while循环----->array[3] = temp[3] = 8 ,tempLeft = 3 + 1 = 4,index = 3 + 1 = 4,
     *                     {1, 2, 4, 8, 8, 6, 3, 5, 7, 0}
     *              第五次while循环----->array[4] = temp[4] = 9 ,tempLeft = 4 + 1 = 5,index = 4 + 1 = 5,
     *                     {1, 2, 4, 8, 9, 6, 3, 5, 7, 0}
     * @param array 原数组
     * @param left  左半边数组的起始索引
     * @param mid   分割左右半边的索引
     * @param right 右半边数组的终止索引
     * @param temp  存储合并后元素的数组
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        //左边数组的起始索引
        int i = left;
        //右边数组的起始位置
        int j = mid + 1;
        //临时数组temp的索引指针
        int index = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[index++] = array[i++];
            } else {
                temp[index++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = array[i++];
        }
        while (j <= right) {
            temp[index++] = array[j++];
        }
        index = 0;
        int tempLeft = left;
        //有序的在temp数组里
        //重新排序right和right之前的数组的所有元素
        while (tempLeft <= right){
            array[tempLeft] = temp[index];
            index++;
            tempLeft++;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
