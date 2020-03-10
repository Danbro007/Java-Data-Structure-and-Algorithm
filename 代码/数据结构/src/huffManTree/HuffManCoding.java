package huffManTree;


import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname HuffManCoding
 * @Description TODO 霍夫曼编码
 * @Date 2020/3/9 14:37
 * @Author Danrbo
 */
public class HuffManCoding {
    /**
     * 用于拼接霍夫曼编码
     */
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 存储每个字符的霍夫曼编码
     */
    static HashMap<Byte, String> huffManCodes = new HashMap<>(16);

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("霍夫曼编码前长度为：" + bytes.length);
        byte[] huffManZip = huffManZip(bytes);
        System.out.println("霍夫曼编码后长度为：" + huffManZip.length);
        BigDecimal beforeZipLength = new BigDecimal(bytes.length);
        BigDecimal afterZipLength = new BigDecimal(bytes.length - huffManZip.length);
        System.out.println("压缩率：" + (afterZipLength.divide(beforeZipLength).multiply(new BigDecimal(100)).toString()) + "%");
        //解码
        decode(huffManZip);
        String srcFile = "D:\\javaProject\\Java-Data-Structure-and-Algorithm\\代码\\数据结构\\src\\huffManTree\\src.bmp";
        String destFile = "D:\\javaProject\\Java-Data-Structure-and-Algorithm\\代码\\数据结构\\src\\huffManTree\\src.zip";
        String srcFile1 = "D:\\javaProject\\Java-Data-Structure-and-Algorithm\\代码\\数据结构\\src\\huffManTree\\src.zip";
        String destFile2 = "D:\\javaProject\\Java-Data-Structure-and-Algorithm\\代码\\数据结构\\src\\huffManTree\\src2.bmp";
        zipFile(srcFile, destFile);
        unZipFile(srcFile1, destFile2);

    }

    /**
     * 把字符串转换成的byte数组--->霍夫曼压缩后的byte数组
     *
     * @param bytes 要压缩的字节数组
     */
    public static byte[] huffManZip(byte[] bytes) {
        //获取节点的list，里面包含每种字符的节点对象
        List<Node2> nodes = getNodes(bytes);
        //创建好霍夫曼树
        Node2 node = createHuffManTree(nodes);
        //把获取的霍夫曼编码存到huffManCodes里
        getHuffManCodes(node);
        //压缩成霍夫曼编码
        return zip(bytes, huffManCodes);
    }

    /**
     * 把字节数组转换成node的list，每个node包含一种字符的字节
     *
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
     *
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
     *
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
     *
     * @param node 起始节点
     */
    public static void getHuffManCodes(Node2 node) {
        if (node == null) {
            System.out.println("此树为空树");
        } else {
            //从根节点的左节点开始
            getHuffManCodes(node.getLeft(), "0", stringBuilder);
            //从根节点的右节点开始
            getHuffManCodes(node.getRight(), "1", stringBuilder);
        }
    }

    /**
     * 获取霍夫曼编码存入到huffManCodes的map里
     *
     * @param node          传入节点
     * @param code          路径： 1：右节点   0：左节点
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

    /**
     * 把霍夫曼编码的byte转换成二进制
     *
     * @param huffManCode 霍夫曼编码的byte
     * @param flag        是否需要补高位 true：需要 false：不需要
     *                    负数需要补高位 因为负数的最高位为1，正数为0 可以不显示
     */
    public static String byteToString(byte huffManCode, boolean flag) {
        int temp = huffManCode;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 把霍夫曼编码的byte解码成二进制
     *
     * @param huffManCodes 霍夫曼拜编码的byte
     */
    public static byte[] decode(byte[] huffManCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffManCodes.length; i++) {
            boolean flag = (i == huffManCodes.length - 1);
            String s = byteToString(huffManCodes[i], !flag);
            stringBuilder.append(s);
        }
        return stringToContent(stringBuilder.toString());

    }

    /**
     * 把byte数组转化成霍夫曼编码的数组
     *
     * @param contentBytes 字符串转换成的byte数组
     * @param huffManCodes 霍夫曼编码后的数组
     * @return 霍夫曼编码的byte数组
     */
    public static byte[] zip(byte[] contentBytes, HashMap<Byte, String> huffManCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < contentBytes.length; i++) {
            stringBuilder.append(huffManCodes.get(contentBytes[i]));
        }
        int len = (stringBuilder.length() + 7) / 8;
        byte[] huffManCodeArray = new byte[len];
        int index = 0;
        /**
         * 通过二进制码转成byte字节（10进制）
         * 比如：二进制码 1101 1000(补码) ---> 1010 0111（反码） ----> 1101 1000（原码）------>2^6 + 2^4 + 2^3 = -88
         * 比如一个正数与一个负数相加：1+（-2）
         * 1 的补码与原码和反码一样 0000 0001
         * -2 的补码：1000 0010（原码）----> 1111 1101（反码） -----> 1111 1110（补码）
         * 1的补码和-2的补码按位与-----> 1111 1111(补码) ------> 1111 1110（反码）----->0000 0001（原码） -----> 1
         */
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {
                /**
                 * 最后一个可能位数不到8位，则用0补全
                 * 比如这里的最后一个的 11100---->用0补全----->0001 1100 ---->byte----> 2^4 + 2^3 + 2^2 = 16 + 8 + 4 = 28
                 */
                huffManCodeArray[index++] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffManCodeArray[index++] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
        }
        return huffManCodeArray;
    }

    /**
     * 把传入的二进制转换成字符码
     *
     * @param str 把传入的二进制
     * @return 返回字符数组
     */
    public static byte[] stringToContent(String str) {
        List<Byte> list = new ArrayList<>();
        HashMap<String, Byte> map = new HashMap<>(16);
        huffManCodes.entrySet().forEach(e -> {
            map.put(e.getValue(), e.getKey());
        });
        for (int i = 0; i < str.length(); ) {
            boolean flag = true;
            int count = 1;
            Byte b = null;
            while (flag) {
                String key = str.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        int index = 0;
        for (Byte aByte : list) {
            bytes[index++] = aByte;
        }
        return bytes;

    }

    /**
     * 文件的压缩
     *
     * @param srcFile 要压缩的文件
     * @param dstFile 目标文件
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建文件输入流、对象输出流
        FileInputStream fis = null;
        ObjectOutputStream oos = null;
        try {
            //通过文件路径获取文件输入流
            fis = new FileInputStream(srcFile);
            //通过读取的文件输入流获取文件大小，创建一样大小的缓冲区
            byte[] bytes = new byte[fis.available()];
            //读取文件到缓冲区里
            fis.read(bytes);
            ///获取霍夫曼编码的byte数组
            byte[] huffManBytes = huffManZip(bytes);
            //写入到文件里 用对象输出流是为了之后读取
            oos = new ObjectOutputStream(new FileOutputStream(dstFile));
            oos.writeObject(huffManBytes);
            //写入这个文件的霍夫曼编码表，下次读取文件可以通过这个霍夫曼表解码
            oos.writeObject(huffManCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                oos.close();
                System.out.println("压缩完成！");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 解压文件
     * @param srcFile 要解压的文件路径
     * @param destFile 解压后的文件路径
     */
    public static void unZipFile(String srcFile, String destFile) {
        //创建文件输出流和对象输入流
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(srcFile));
            //读取到霍夫曼编码和霍夫曼编码表
            byte[] huffManBytes = (byte[]) ois.readObject();
            huffManCodes = (HashMap<Byte, String>) ois.readObject();
            //解码写入文件
            fos = new FileOutputStream(destFile);
            byte[] bytes = decode(huffManBytes);
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
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
