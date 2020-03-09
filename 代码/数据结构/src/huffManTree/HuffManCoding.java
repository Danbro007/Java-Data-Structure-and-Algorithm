package huffManTree;


import java.util.*;

/**
 * @Classname HuffManCoding
 * @Description TODO 霍夫曼编码
 * @Date 2020/3/9 14:37
 * @Author Danrbo
 */
public class HuffManCoding {

    static StringBuilder stringBuilder = new StringBuilder();
    static HashMap<Byte, String> huffManCodes = new HashMap<>(16);

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("编码前长度为：" + bytes.length);
        //获取节点的list，里面包含每种字符的节点对象
        List<Node2> nodes = getNodes(bytes);
        //创建好霍夫曼树
        Node2 node = createHuffManTree(nodes);
        //把获取的霍夫曼编码存到huffManCodes里
        getHuffManCodes(node);
        System.out.println(huffManCodes);
    }

    /**
     * 把字节数组转换成node的list，每个node包含一种字符的字节
     * @param bytes 传入的字节数组
     * @return node list
     */
    public static List<Node2> getNodes(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>(16);
        for (int i = 0; i < bytes.length; i++) {
            if (map.get(bytes[i]) == null) {
                map.put(bytes[i], 1);
            } else {
                Integer count = map.get(bytes[i]);
                map.put(bytes[i], count + 1);
            }
        }
        List<Node2> nodes = new ArrayList<>();
        map.keySet().forEach(key -> nodes.add(new Node2(key, map.get(key))));
        return nodes;
    }

    /**
     * 创建霍夫曼树
     * @param nodes 节点list
     * @return 霍夫曼树
     */
    public static Node2 createHuffManTree(List<Node2> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node2 leftNode = nodes.get(0);
            Node2 rightNode = nodes.get(1);
            //新的二叉树不需要data，只有权值weight
            Node2 parent = new Node2(leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 中序遍历霍夫曼树
     * @param root 要遍历的起始节点
     */
    public static void preOrder(Node2 root) {
        if (root == null) {
            System.out.println("此树为空树");
        } else {
            root.preOrer();
        }
    }

    /**
     * 获得霍夫曼编码
     * @param node 起始节点
     */
    public static void getHuffManCodes(Node2 node) {
        if (node == null){
            System.out.println("此树为空树");
        }else {
            //从根节点的左节点开始
            getHuffManCodes(node.getLeft(), "0", stringBuilder);
            //从根节点的右节点开始
            getHuffManCodes(node.getRight(), "1", stringBuilder);
        }
    }

    /**
     * 获取霍夫曼编码存入到huffManCodes的map里
     * @param node 传入节点
     * @param code 路径： 1：右节点   0：左节点
     * @param stringBuilder 用于拼接路径
     */
    public static void getHuffManCodes(Node2 node, String code, StringBuilder stringBuilder) {

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            /**
             * node的data = null说明是非叶子节点
             */
            if (node.getData() == null) {
                getHuffManCodes(node.getLeft(), "0", stringBuilder2);
                getHuffManCodes(node.getRight(), "1", stringBuilder2);
            } else {
                huffManCodes.put(node.getData(), stringBuilder2.toString());
            }
        }
    }

}

/**
 * 节点类
 * 存储字符，权重值，左节点和右节点
 */
class Node2 implements Comparable {
    private Byte data;
    private Integer weight;
    private Node2 left;
    private Node2 right;

    public Node2(Byte data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node2(Integer weight) {
        this.weight = weight;
    }

    /**
     * 节点的前序遍历
     */
    public void preOrer() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrer();
        }
        if (this.getRight() != null) {
            this.getRight().preOrer();
        }
    }

    @Override
    public String toString() {
        return "Node2{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Node2 getLeft() {
        return left;
    }

    public void setLeft(Node2 left) {
        this.left = left;
    }

    public Node2 getRight() {
        return right;
    }

    public void setRight(Node2 right) {
        this.right = right;
    }

    @Override
    public int compareTo(Object o) {
        Node2 o1 = (Node2) o;
        return this.getWeight() - o1.getWeight();
    }
}
