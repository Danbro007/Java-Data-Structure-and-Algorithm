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

    public void threadedNodes() {
        threadedNodes(root);
    }

    /**
     * 比如有这样的树：
     *                1
     *             /    \
     *            3     6
     *          / \    /
     *        8   10  14
     * 从当前节点线索化
     * @param node 要线索化的起始节点
     */
    public void threadedNodes(Node3 node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());
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
        threadedNodes(node.getRight());

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
        //树的顺序{}
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        Node3 left = node5.getLeft();
        System.out.println("node5的左节点：" + left);
        Node3 right = node5.getRight();
        System.out.println("node5的右节点：" + right);


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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Node3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.getLeft() != null) {
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.getLeft() != null) {
            this.getLeft().postOrder();
        }
        if (this.getRight() != null) {
            this.getRight().postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     *
     * @param id 要查找的node的id
     */
    public Node3 preOrderSearch(int id) {
        if (this.getId() == id) {
            return this;
        }
        Node3 resNode3 = null;
        if (this.getLeft() != null) {
            resNode3 = this.left.preOrderSearch(id);
        }
        if (resNode3 != null) {
            return resNode3;
        }
        if (this.getRight() != null) {
            return this.right.preOrderSearch(id);
        }
        return null;
    }

    /**
     * 中序查找
     *
     * @param id 要查找的id
     * @return 查找结果
     */
    public Node3 infixOrderSearch(int id) {
        Node3 resNode3 = null;
        if (this.getLeft() != null) {
            resNode3 = this.left.infixOrderSearch(id);
        }
        if (resNode3 != null) {
            return resNode3;
        }
        if (this.getId() == id) {
            return this;
        }
        if (this.getRight() != null) {
            return this.right.infixOrderSearch(id);
        }
        return null;
    }

    /**
     * 后序查找
     *
     * @param id 要查找的id
     * @return 查询结果
     */
    public Node3 postOrderSearch(int id) {
        Node3 resNode3 = null;
        if (this.getLeft() != null) {
            resNode3 = this.left.postOrderSearch(id);
        }
        if (resNode3 != null) {
            return resNode3;
        }
        if (this.getRight() != null) {
            return this.right.postOrderSearch(id);
        }
        if (this.getId() == id) {
            return this;
        }
        return null;
    }

    /**
     * 节点删除
     * 1、如果当前节点的left节点不为空则判断left节点是否是要删除的节点
     * 如果不是则递归查询下一个left节点，如果是则把当前节点的left设置为null，相当于删除left
     * 与当前节点的连接。
     * 2、如果当前节点的right节点不为空则判断right节点是否是要删除的节点
     * 如果不是则递归查询下一个right节点，如果是则把当前节点的right设置为null，相当于删除right
     * 与当前节点的连接
     *
     * @param id 要删除的id
     */
    public void delNode(int id) {
        if (this.getLeft() != null) {
            if (this.getLeft().getId() == id) {
                this.setLeft(null);
            } else {
                this.getLeft().delNode(id);
            }
        }
        if (this.getRight() != null) {
            if (this.getRight().getId() == id) {
                this.setRight(null);
            } else {
                this.getRight().delNode(id);
            }
        }
    }
}
