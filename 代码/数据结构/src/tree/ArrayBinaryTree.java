package tree;

/**
 * @Classname ArrayBinaryTree
 * @Description TODO 顺序存储二叉树
 * @Date 2020/3/7 19:08
 * @Author Danrbo
 */
public class ArrayBinaryTree {

    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder();
        System.out.println();
        arrayBinaryTree.infixOrder();
        System.out.println();
        arrayBinaryTree.postOrder();

    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 前序遍历
     * 1、如果当前节点有左节点既 a[2 * n + 1] 存在则递归查询
     * 2、如果当前节点有右节点既 a[2 * n + 2] 存在则递归查询
     * @param n 指定遍历的起始索引
     */
    public void preOrder(int n) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
        } else {
            System.out.printf("%d\t",array[n]);
            if (2 * n + 1 < array.length) {
                preOrder(2 * n + 1);
            }
            if (2 * n + 2 < array.length) {
                preOrder(2 * n + 2);
            }
        }
    }

    /**
     * 中序遍历
     * 1、如果当前节点有左节点既 a[2 * n + 1] 存在则递归查询
     * 2、如果当前节点有右节点既 a[2 * n + 2] 存在则递归查询
     * @param n 指定遍历的起始索引
     */
    public void infixOrder(int n) {
        if (array == null || array.length == 0) {
            System.out.print("数组为空");
        } else {
            if (2 * n + 1 < array.length) {
                preOrder(2 * n + 1);
            }
            System.out.printf("%d ",array[n]);
            if (2 * n + 2 < array.length) {
                preOrder(2 * n + 2);
            }
        }
    }

    /**
     * 后序查找
     * @param n 要遍历的起始索引
     */
    public void postOrder(int n) {
        if (array == null || array.length == 0) {
            System.out.print("数组为空");
        } else {
            if (2 * n + 1 < array.length) {
                preOrder(2 * n + 1);
            }
            if (2 * n + 2 < array.length) {
                preOrder(2 * n + 2);
            }
            System.out.printf("%d ",array[n]);
        }
    }

}
