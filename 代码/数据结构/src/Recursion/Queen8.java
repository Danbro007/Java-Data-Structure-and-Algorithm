package Recursion;

/**
 * @Classname Queen8
 * @Description TODO 用递归-回溯解决8皇后问题
 * @Date 2020/3/3 12:44
 * @Author Danrbo
 */
public class Queen8 {
    public static void main(String[] args) {

        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("共有解法：%d个",queen8.count);
    }

    /**
     * 皇后个数为8
     */
    private int max = 8;
    /**
     * 用一维数组表示棋盘
     * 例如array = [0,4,7,5,2,6,1,3,]
     * array[2] = 7 表示 棋盘第三行的第8列
     * 既皇后的位置为 array[i] = value
     * 表示皇后的位置在棋盘的 i+1 行 value+1 列
     */
    private int[] array = new int[max];

    public int count = 0;

    /**
     *
     * @param n 第几个皇后
     */
    public void check(int n){
        //当要设置第9个皇后的位置时说明之前的8位置都摆好了
        //则直接打印皇后的位置
        if ( n == max){
            print();
            count++;
        }
        //8个皇后还没放好
        else {
            //摆放的每一个皇后都要在8个列上尝试
            for (int i = 0; i < max; i++) {
                //当前皇后的位置
                array[n] = i;
                //尝试摆放 成功就摆放下一个皇后 失败则尝试放在下一列
                if (judge(n)){
                    check(n+1);
                }
            }
        }
    }


    /**
     * 皇后摆放的逻辑策略
     *
     * @param n 第几个皇后
     */
    public boolean judge(int n) {
        //遍历n之前的所有皇后 与当前的皇后比较 看是否与皇后的摆法冲突
        for (int i = 0; i < n; i++) {
            //如果两个皇后在同一列或者 两个皇后在同一个斜线
            //Math.abs(n - i) == Math.abs(array[n] - array[i])
            //表示两个皇后坐标的斜率一样
            // 例如皇后 a 的 坐标为[1,1] 皇后 b 的坐标为 [3,3]
            //两者的横坐标相减取绝对值 纵坐标相减取绝对值相等则表示在同一斜线上
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 打印一种摆法8个皇后位置
     */
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d\t", array[i]);
        }
        System.out.println();
    }

}


