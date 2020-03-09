package tree.threadedBinaryTreeDemo;

/**
 * @Classname ThreadedBinaryTree 线索化二叉树
 * @Description TODO
 * @Date 2020/3/7 19:52
 * @Author Danrbo
 */
public class ThreadedBinaryTree {

    private Node3 root;
    /**
     * 当前节点的前一个节点
     */
    private Node3 pre;

    /**
     * 按照中序线索化节点
     */
    public void threadedInfixOrderNodes() {
        threadedInfixOrderNodes(root);
    }

    public void threadedPreOrderNodes() {
        threadedPreOrderNodes(root);
    }

    public void threadedPostOrderNodes() {
        threadedPostOrderNodes(root);
    }

    /**
     *                1
     *             /    \
     *            3     6
     *          / \    /
     *        8   10  14
     */
    public void threadedPreOrder(){
        Node3 cur = root;
        while (cur != null){
            System.out.println(cur);
            while (cur.isLeftNodeType()){

            }
        }
    }

    /**
     * 比如有这样的树：
     * 1
     * /    \
     * 3     6
     * / \    /
     * 8   10  14
     * 从当前节点线索化
     *
     * @param node 要线索化的起始节点
     */
    public void threadedInfixOrderNodes(Node3 node) {
        if (node == null) {
            return;
        }
        threadedInfixOrderNodes(node.getLeft());
        /**
         *                1
         *             /    \
         *            3     6
         *          / \    /
         *        8   10  14
         * 如果当前节点的左节点为空，说明left指向的是前驱节点不是左子树，
         * 把left设置为它的前驱节点
         *  比如当前的node = 8 按照中序遍历顺序是{8,3,10,1,14,6}
         *  8是第一个元素，此时pre = null,它没有前驱节点 所以它的left指向pre（null），
         *  left的类型指向的是前驱节点类型（true）
         *  再比如node = 10，此时的pre = 3 ，原来它的left指向的是null，
         *  我们把它的left指向 3，left类型是true既前驱节点类型
         */
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftNodeType(true);
        }
        /**
         *                1
         *             /    \
         *            3     6
         *          / \    /
         *        8   10  14
         * 如果当前节点的前驱不为空并且前驱节点的right为空,
         * 这是为了给比如8与后继节点建立关系
         * 比如：pre = 8 ,node = 3,pre的right = null
         * 我们把 pre 节点的right指向为 node 既 8 的right指向 3
         * 同时修改 8 的right节点类型（true）
         *
         */
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightNodeType(true);
        }
        /**
         * 把pre指向下一个节点
         */
        pre = node;
        /**
         * 往右节点线索化
         */
        threadedInfixOrderNodes(node.getRight());
    }

    /**
     *                1
     *             /    \
     *            3     6
     *          / \    /
     *        8   10  14
     * 第一次： cur = 1 ,pre = null
     * 第二次：cur = 3 , pre = 1
     * 第三次： cur = 8 ,pre = 8
     * 通过 8 left = null,判断出 8 是要线索化的节点，
     * 把 8 的 left 指向 pre既 3 ，leftNodeType = true
     * 第四次： cur = 10 ,pre = 8
     * 通过 10 的left = null 判断出 10 是要线索化的节点
     * 把 8 的 left 指向 pre 既 8，leftNodeType = true
     * 再把 pre 的 right 指向 10 ,pre的rightNodeType = true
     * 第五次 ： cur = 6 ，pre = 10
     * 再把 pre 的 right 指向 6 ,pre的rightNodeType = true
     * 第六次: cur = 14 ，pre = 6
     * 通过 14 left = null,判断出 14 是要线索化的节点，
     * 把 14 的 left 指向 pre既 6 ，leftNodeType = true
     *
     * @param node 要线索化的树的起点{1,3,8,10,6,14}
     */
    public void threadedPreOrderNodes(Node3 node) {
        if (node == null) {
            return;
        }
        boolean flag = false;
        Node3 left = node.getLeft();
        Node3 right = node.getRight();
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftNodeType(true);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightNodeType(true);
            if (node.getLeft() != pre){
                node.setLeft(pre);
                flag = true;
            }
        }
        pre = node;
        if (!node.isLeftNodeType()) {
            threadedPreOrderNodes(left);

        }if (!node.isRightNodeType()){
            threadedPreOrderNodes(right);
        }
        if (flag){
            node.setLeftNodeType(true);
            threadedPreOrderNodes(left);
        }
    }

    /**
     *                1
     *             /    \
     *            3     6
     *          / \    /
     *        8   10  14
     * @param node
     *{8,10,3,14,6,1}
     */
    public void threadedPostOrderNodes(Node3 node){
        if (node == null){
            return;
        }
        threadedPostOrderNodes(node.getLeft());
        threadedPostOrderNodes(node.getRight());
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftNodeType(true);
        }

        if (pre!=null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightNodeType(true);
        }
        if (pre!=null && pre.getRight() != node){
            pre.setRight(node);
        }

        pre = node;

    }

    /**
     * 从root节点开始遍历整棵树。
     * 按照中序遍历
     */
    public void threadedInfixOrder() {
        /**
         * 从root节点开始遍历
         */
        Node3 cur = root;
        while (cur != null) {
            /**
             *                1
             *             /    \
             *            3     6
             *          / \    /
             *        8   10  14
             * 如果当前节点的左节点类型是false说明当前节点是还没被线索化的节点既就继续向左循环寻找
             * 直到找到是左节点类型是true的，比如 8 这个节点
             */
            while (!cur.isLeftNodeType()) {
                cur = cur.getLeft();
            }
            /**
             * 找到了后打印该节点
             */
            System.out.println(cur);
            /**
             * 查找当前节点的右节点
             * 如果当前节点的右节点类型是true说明是被线索化的节点则cur挪到那个右节点然后打印这个节点
             * 直到当前右节点类型是false------>退出
             *
             */
            while (cur.isRightNodeType()) {
                cur = cur.getRight();
                System.out.println(cur);
            }
            cur = cur.getRight();
        }
    }

    public Node3 getRoot() {
        return root;
    }

    public void setRoot(Node3 root) {
        this.root = root;
    }

    public Node3 getPre() {
        return pre;
    }

    public void setPre(Node3 pre) {
        this.pre = pre;
    }

    public static void main(String[] args) {
        Node3 root = new Node3(1, "john1");
        Node3 node2 = new Node3(3, "john3");
        Node3 node3 = new Node3(6, "john6");
        Node3 node4 = new Node3(8, "john8");
        Node3 node5 = new Node3(10, "john10");
        Node3 node6 = new Node3(14, "john14");
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedPreOrderNodes();
        threadedBinaryTree.threadedPreOrder();
//        System.out.println("开始前序线索化二叉树");
//        threadedBinaryTree.threadedPreOrderNodes();
//        System.out.println("前序线索化二叉树完毕");
//        Node3 left = node3.getLeft();
//        Node3 right = node3.getRight();
//        System.out.println("节点6的上一个节点：" + left);
//        System.out.println("节点6的下一个节点：" + right);
//        threadedBinaryTree.threadedInfixOrderNodes();
//        Node3 left = node5.getLeft();
//        System.out.println("node5的左节点：" + left);
//        Node3 right = node5.getRight();
//        System.out.println("node5的右节点：" + right);
//        threadedBinaryTree.threadedInfixOrder();//{8,3,1,10,14,6}
    }

}

class Node3 {
    private int id;
    private String name;
    private Node3 left;
    private Node3 right;
    /**
     * leftNodeType   false：左节点指向的左子树
     * true：指向的前驱节点
     */
    private boolean leftNodeType;

    /**
     * rightNodeType   false：右节点指向的右子树
     * true：指向的后继节点
     */
    private boolean rightNodeType;

    public boolean isLeftNodeType() {
        return leftNodeType;
    }

    public void setLeftNodeType(boolean leftNodeType) {
        this.leftNodeType = leftNodeType;
    }

    public boolean isRightNodeType() {
        return rightNodeType;
    }

    public void setRightNodeType(boolean rightNodeType) {
        this.rightNodeType = rightNodeType;
    }

    public Node3 getLeft() {
        return left;
    }

    public void setLeft(Node3 left) {
        this.left = left;
    }

    public Node3 getRight() {
        return right;
    }

    public void setRight(Node3 right) {
        this.right = right;
    }

    public Node3(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
