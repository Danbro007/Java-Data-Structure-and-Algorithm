import java.io.*;


/**
 * 实现一个二维数组与稀疏数组的相互转换
 */
public class SparseArrayTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //创建11*11的二维数组 0代表没有棋子 1代表黑子 2代表蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("转换前");
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                System.out.printf("%d\t", chessArr1[i][j]);
            }
            System.out.println();
        }


        //统计非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("二维数组里的非零数据个数为：" + sum);
        //创建稀疏数组,并把数组的长度和有效数据个数插入在稀疏数组的第一行
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //把有效数据插入到系数数组里
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //打印稀疏数组数据
        System.out.println("稀疏数组的数据：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        //存储稀疏数组
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\Java数据结构与算法\\代码\\数据结构\\src\\map.data"));
        oos.writeObject(sparseArray);
        System.out.println("data.map存储数据完毕");


        //读取稀疏数组
        System.out.println("从data.map读取数据");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\Java数据结构与算法\\代码\\数据结构\\src\\map.data"));
        int[][] sparseArray2 = (int[][]) ois.readObject();
        for (int i = 0; i < sparseArray2.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        int[][] chessArr3 = new int[sparseArray2[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray2.length; i++) {
            chessArr3[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
        }
        System.out.println("转换到二维数组后：");
        for (int i = 0; i < chessArr3.length; i++) {
            for (int j = 0; j < chessArr3[i].length; j++) {
                System.out.printf("%d\t", chessArr1[i][j]);
            }
            System.out.println();
        }
    }

}

