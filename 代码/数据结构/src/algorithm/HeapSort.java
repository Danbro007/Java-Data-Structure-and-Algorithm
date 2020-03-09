package algorithm;

import java.util.Arrays;

/**
 * @Classname HeapSort
 * @Description TODO
 * @Date 2020/3/8 19:26
 * @Author Danrbo
 */
public class HeapSort implements Algorithm {
    private String name = "堆排序";

    /**
     *    arr = {1,3,4,0,9,7,5,2,8,6}
     * 		1
     * 	   / \
     *   3   4
     * 	 / \ / \
     * 	0  9 7  5
     * /\ /
     *2 8 6
     *
     * 更新每个非叶子节点的顺序,确定 在 k = 2*k+1的值都为k所在的叶子节点的最大值
     *
     * 第一次更新：
     * 	i = arr.length / 2 - 1 = 10 / 2 -1 = 4
     * 	a[i] = a[4] = 9
     * 		1
     * 	   / \
     *   3   4
     * 	 / \ / \
     * 	0  9 7  5
     * /\ /
     *2 8 6
     *     arr = {1,3,4,0,9,7,5,2,8,6}
     *
     * 第二次更新：
     * 	i = arr.length / 2 - 1 = 10 / 2 - 1 - 1 = 3
     * 	a[i] = a[3] = 0
     * 		1
     * 	   / \
     *   3   4
     * 	 / \ / \
     * 	8  9 7  5
     * /\ /
     *2 0 6
     *     arr = {1,3,4,8,9,7,5,2,0,6}
     *
     * 第三次更新：
     * 	i = arr.length / 2 - 1 = 10 / 2 - 1 - 1 - 1= 2
     * 	a[i] = a[4] = 9
     * 		 1
     * 	   /  \
     *   3    7
     * 	 / \  / \
     * 	8  9 4  5
     * /\ /
     *2 0 6
     *   arr = {1,3,7,8,9,4,5,2,0,6}
     *
     * 第四次更新：
     * 	i = arr.length / 2 - 1 = 10 / 2 - 1 - 1 - 1 - 1= 1
     * 	a[i] = a[4] = 9
     * 		 1
     * 	   /  \
     *   9    7
     *  / \  / \
     *  8  6 4  5
     * /\ /
     *2 0 3
     *  arr = {1,9,7,8,3,4,5,2,0,6}
     *
     * 第五次更新：
     * 	i = arr.length / 2 - 1 = 10 / 2 - 1 - 1 - 1 - 1 - 1= 0
     * 	a[i] = a[4] = 9
     * 		 9
     * 	   /  \
     *    8    7
     * 	 / \  / \
     * 	2  6 4  5
     * /\ /
     *1 0 3
     *       arr = {9,8,7,2,6,4,5,1,0,3}
     *  把数组里的最大值 9 放在堆顶,非叶子节点的值都为它所在的子树的最大值
     *
     * 把最大值放在数组尾部
     *
     * 第一次：
     * 	调整前：
     * 	     3
     * 	   /  \
     *    8    7
     * 	 / \  / \
     * 	2  6 4  5
     * /\ /
     *1 0 9
     * 		j = arr.length / 2 - 1 = 9
     * 		arr = {3,8,7,2,6,4,5,1,0,9}
     * 		然后从索引0到8重新进行排序找到最大值放到堆顶
     * 		此时的i - 0 ,length = 9
     *
     * 	调整后：
     * 		 8
     * 	   /  \
     *   6    7
     * 	 / \  / \
     * 	2  3 4  5
     * /\ /
     *1 0 9
     *
     *
     * 第二次：
     * 	调整前：
     * 		 0
     * 	   /  \
     *    6    7
     * 	 / \  / \
     * 	2  3 4  5
     * /\ /
     *1 8 9
     * 		j = arr.length / 2 - 1 = 8
     * 		arr = {0,6,7,2,3,4,5,1,8,9}
     * 		然后从索引0到7重新进行排序找到最大值放到堆顶
     * 		此时的i = 0 ,length = 8
     *
     * 	调整后：
     * 		 7
     * 	   /  \
     *   6    5
     * 	 / \  / \
     * 	2  3 4   0
     * /\ /
     *1 8 9
     *
     * 接着不断按照这样的方法，把数组里的值进行排序得到{0,1,2,3,4,5,6,7,8,9}
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1,3,4,0,9,7,5,2,8,6};
        new HeapSort().sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void sort(int[] array) {
        int temp;
        /**
         * 遍历每个非叶子节点，让每个非叶子节点的值为所在子树的最大值
         */
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        /**
         * 把堆顶的最大值挪到数组尾部,不断循环排序。
         */
        for (int j = array.length - 1; j > 0; j--) {
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, j);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param array 要排序的数组
     * @param i 非叶子节点的索引
     * @param length 数组长度
     */
    public void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (temp < array[k]) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }
}
