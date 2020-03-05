package recursion;

/**
 * @Classname LabyrinthBacktrackingDemo
 * @Description TODO 迷宫回溯 递归方法
 * @Date 2020/3/3 10:06
 * @Author Danrbo
 */
public class LabyrinthBacktrackingDemo {

    public static void main(String[] args) {
        //创建一个8*7的迷宫
        int[][] map = new int[8][7];
        //设置围墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设立围挡
        map[3][1] = 1;
        map[3][2] = 1;
        //打印围墙
        System.out.println("------走之前-----");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }

        setWay(map,1,1);
        System.out.println("------走之后-----");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 迷宫起点为[1][1] , 终点为[6][5]，
     * 点的状态标记：
     *      0 表示未走过
     *      1 表示是围墙 不能通行
     *      2 表示可以通行，既迷宫的行径路线
     *      3 表示已经走过但是不能通行
     * 行走策略：下->右->上->左
     *
     * @param map 迷宫
     * @param i   当前点的横坐标
     * @param j   当前点的纵坐标
     * @return true:可以通行 false:不可以通行
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //如果到达终点则返回true 跳出递归
        if (map[6][5] == 2) {
            return true;
        }
        //没到达终点
        else {
            //如果当前的点没有走过
            if (map[i][j] == 0){
                //假定当前点可以通行
                map[i][j] = 2;
                //如果能向下走
                if (setWay(map,i+1,j)){
                    return true;
                }
                //如果能向右走
                else if (setWay(map,i,j+1)){
                    return true;
                }
                //如果能向上走
                else if (setWay(map,i-1,j)){
                    return true;
                }
                //如果能向左走
                else if (setWay(map,i,j-1)){
                    return true;
                }
                //如果以上都失败则把这个点设置为3 既已经走过但是不通
                else {
                    map[i][j] = 3;
                    return false;
                }
            }
            //表示可能是围墙、走不通
            else {
                return false;
            }
        }
    }

}
