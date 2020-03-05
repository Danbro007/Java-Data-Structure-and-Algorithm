package algorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/3/4 12:26
 * @Author Danrbo
 */
public class Test {
    public static void main(String[] args) {
        getEfficiency(new BubbleSort());
        getEfficiency(new SelectSort());
        getEfficiency(new InsertSort());
        getEfficiency(new ShellSort());
        getEfficiency(new ShellSort2());
        getEfficiency(new QuickSort());
        getEfficiency(new MergeSort());
        getEfficiency(new RadixSort());

    }


    public static void getEfficiency(Algorithm algorithm) {
        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        Date start1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println(String.format("%s开始：", algorithm.getName()) + format.format(start1));
        algorithm.sort(array);
        Date end = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        System.out.println(String.format("%s结束：", algorithm.getName()) + format2.format(end));
        System.out.println("-------------------------------------------");
    }
}
