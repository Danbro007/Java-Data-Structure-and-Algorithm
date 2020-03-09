package huffManTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Classname HuffManTree
 * @Description TODO 霍夫曼树
 * @Date 2020/3/9 13:14
 * @Author Danrbo
 */
public class HuffManTree {
    public static void main(String[] args) {
        int[] array = {13,7,8,3,29,6,1};
        Node node = createHuffManTree(array);
        node.preOrer();
    }

    /**
     * 创建霍夫曼树
     * @param array 要创建霍夫曼树的数组
     */
    public static Node createHuffManTree(int[] array){
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(new Node(array[i]));
        }
        //给list按从小到大排序
        Collections.sort(list);
        //当list内只有一个元素时说明已经生成霍夫曼树
        while (list.size() > 1){
            //由于list是按照从小到大排列，所以list的头两个元素时list里第一和第二个小的
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            //创建一个新的节点，新的节点的value是那两个节点value的和，
            //新节点的左节点是leftNode,右节点是rightNode。
            Node newNode = new Node(leftNode.getValue() + rightNode.getValue());
            newNode.setLeft(leftNode);
            newNode.setRight(rightNode);
            //把list的那两个节点删除
            list.remove(leftNode);
            list.remove(rightNode);
            //在list尾部添加新节点
            list.add(newNode);
            //再重新排序
            Collections.sort(list);
        }
        return list.get(0);
    }
}

class Node implements Comparable{
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 节点的前序遍历
     */
    public void preOrer(){
        System.out.println(this);
        if (this.getLeft() != null){
            this.getLeft().preOrer();
        }
        if (this.getRight() != null){
            this.getRight().preOrer();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Object o) {
        Node o1 = (Node) o;
        return this.getValue() - o1.getValue();
    }
}