package algorithm;


import java.util.Arrays;

/**
 * @Classname RadixSort
 * @Description TODO 基数排序
 * @Date 2020/3/5 13:31
 * @Author Danrbo
 */
public class RadixSort implements Algorithm {
    private String name = "基数排序";

    public static void main(String[] args) {
        int[] array = {53, 3, 542, 748, 14, 214};
        new RadixSort().sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void sort(int[] array) {
        //获取数组内最大数的位数
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int maxLength = String.valueOf(max).length();
        //创建木桶
        int[][] buckets = new int[10][array.length];
        //每个桶的元素数量的数组
        int[] bucketElementCounts = new int[10];
        int index = 0;
        for (int i = 0; i < maxLength; i++) {
            //遍历Array，把里面的元素放入指定的桶内
            for (int j = 0; j < array.length; j++) {
                int m;
                int n = (int) Math.pow(10, i);
                //元素要要插入木桶的索引值
                m = array[j] / n % 10;
                //在木桶里插入元素
                buckets[m][bucketElementCounts[m]] = array[j];
                //相应的木桶元素个数加一
                bucketElementCounts[m]++;
            }
            //从木桶里取出元素
            for (int j = 0; j < buckets.length; j++) {
                //如果木桶里有元素就逐个取出放到array数组里
                if (bucketElementCounts[j] > 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        array[index] = buckets[j][k];
                        index++;
                    }
                    //取完后把相应的木桶个数清零
                    bucketElementCounts[j] = 0;
                }
            }
            index = 0;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
