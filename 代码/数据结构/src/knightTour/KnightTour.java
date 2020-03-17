package knightTour;

import java.awt.*;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Classname KnightTour
 * @Description TODO 骑士周游问题
 * @Date 2020/3/17 11:22
 * @Author Danrbo
 */
public class KnightTour {
    private static int X;
    private static int Y;
    private static boolean[][] isVisited;
    private static boolean finished;

    public static void main(String[] args) {
        //设置棋盘大小
        X = 8;
        Y = 8;
        //初始化棋盘每个格子是否被访问过 True：已经访问过 False：没有被访问过
        isVisited = new boolean[X][Y];
        //棋子的初始位置
        int row = 5;
        int column = 5;
        //创建 8 * 8的棋盘
        int[][] chessBoard = new int[X][Y];
        //由于第一个棋子已经放好了，所以算第一步
        int step = 1;
        travelChessBoard(chessBoard, row - 1, column - 1, step);
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                System.out.printf("%4d", chessBoard[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 1、先取得当前棋子是第几步 step
     * 2、把当前棋子的位置标记为已访问过 true
     * 3、获取当前棋子下一步棋的所有位置的列表 nextPointList
     * 4、while循环nextPointList，递归遍历下一步棋的路数 直到 step 等于 棋盘格数
     * 5、如果step小于棋盘的格数并且finished标记为false说明失败，把当前棋子的步数清0，访问标记为false
     * 6、step等于棋盘个数表示任务完成 finished = true
     *
     * @param chessBoard 棋盘
     * @param row        棋子的行既棋子的Y轴位置
     * @param column     棋子的列既棋子的X轴位置
     * @param step       第几步棋
     */
    public static void travelChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[column][row] = step;
        isVisited[column][row] = true;
        ArrayList<Point> nextPointList = next(new Point(column, row));
        sort(nextPointList);
        while (!nextPointList.isEmpty()) {
            Point p = nextPointList.remove(0);
            if (!isVisited[p.x][p.y]) {
                travelChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessBoard[column][row] = 0;
            isVisited[column][row] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 把当前点的所有可能的下一步棋的位置上存储到ArrayList里
     *
     * @param curPoint 当前点的位置
     * @return 当前点下一步的位置list
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> nextPointList = new ArrayList<>();
        Point nextPoint = new Point();
        if ((nextPoint.x = curPoint.x - 2) >= 0 && (nextPoint.y = curPoint.y + 1) < Y) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x - 1) >= 0 && (nextPoint.y = curPoint.y + 2) < Y) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x + 1) < X && (nextPoint.y = curPoint.y + 2) < Y) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x + 2) < X && (nextPoint.y = curPoint.y + 1) < Y) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x + 2) < X && (nextPoint.y = curPoint.y - 1) >= 0) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x + 1) < X && (nextPoint.y = curPoint.y - 2) >= 0) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x - 1) >= 0 && (nextPoint.y = curPoint.y - 2) >= 0) {
            nextPointList.add(new Point(nextPoint));
        }
        if ((nextPoint.x = curPoint.x - 2) >= 0 && (nextPoint.y = curPoint.y - 1) >= 0) {
            nextPointList.add(new Point(nextPoint));
        }
        return nextPointList;
    }

    /**
     * 优化查找速度，给pointsList按照他们的下一个位置的list里的point总数从小到大排序
     * @param pointsList 棋子的位置list
     */
    public static void sort(List<Point> pointsList){
        pointsList.sort((a,b) -> Integer.compare(next(a).size(),next(b).size()));
    }
}
