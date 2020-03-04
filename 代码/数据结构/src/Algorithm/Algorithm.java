package Algorithm;

/**
 * @Classname Algorithm
 * @Description TODO 算法继承的接口,方便测试。
 * @Date 2020/3/4 12:20
 * @Author Danrbo
 */
public interface Algorithm {
    /**
     * 算法的排序方法
     * @param array 要排序的数组
     */
    void sort(int[] array);

    /**
     *返回算法的名字
     * @return 算法的名字
     */
    String getName();
}
