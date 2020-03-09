package tree;

/**
 * @Classname BinaryTree
 * @Description TODO
 * @Date 2020/3/6 22:16
 * @Author Danrbo
 */
public class BinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        isEmptyTree();
        this.getRoot().preOrder();
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        isEmptyTree();
        this.getRoot().infixOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        isEmptyTree();
        this.getRoot().postOrder();
    }

    /**
     * 判断是否是空树
     */
    private void isEmptyTree() {
        if (this.getRoot() == null) {
            throw new RuntimeException("空树");
        }
    }

    public Node preOrderSearch(int id) {
        isEmptyTree();
        return this.getRoot().preOrderSearch(id);
    }

    public Node infixOrderSearch(int id) {
        isEmptyTree();
        return this.getRoot().infixOrderSearch(id);
    }

    public Node postOrderSearch(int id) {
        isEmptyTree();
        return this.getRoot().postOrderSearch(id);
    }

    /**
     * 递归删除节点
     *
     * @param id 要删除的节点id
     */
    public void delNode(int id) {
        isEmptyTree();
        if (this.getRoot().getId() == id) {
            this.setRoot(null);
        }
        this.root.delNode(id);
    }


    public static void main(String[] args) {
        try {
            BinaryTree binaryTree = new BinaryTree();
            Node root = new Node(1, "john1");
            Node node3 = new Node(3, "john2");
            Node node6 = new Node(6, "john3");
            Node node8 = new Node(8, "john4");
            Node node10 = new Node(10, "john5");
            Node node14 = new Node(14, "john5");
            root.setLeft(node3);
            root.setRight(node6);
            node3.setLeft(node8);
            node3.setRight(node10);
            node6.setLeft(node14);
            binaryTree.setRoot(root);
            System.out.println("----");
            binaryTree.postOrder();
            System.out.println("-----");
            //前序查询次数：1---->2---->3----->5 共4次
            Node resNode = binaryTree.preOrderSearch(5);
            if (resNode != null) {
                System.out.println("前序查询到节点：" + resNode);
            } else {
                System.out.println("没有查询到节点:" + 5);
            }
            //中序查询次数：2---->1----->5 共3次
            Node resNode2 = binaryTree.infixOrderSearch(5);
            if (resNode != null) {
                System.out.println("中序查询到节点：" + resNode2);
            } else {
                System.out.println("没有查询到节点:" + 5);
            }

            //后序查询次数:2------->5 共2次
            Node resNode3 = binaryTree.postOrderSearch(5);
            if (resNode != null) {
                System.out.println("后序查询到节点：" + resNode3);
            } else {
                System.out.println("没有查询到节点:" + 5);
            }
            binaryTree.delNode(6);
            binaryTree.preOrder();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * 节点类
 */
class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

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

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Node{" +
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
    public Node preOrderSearch(int id) {
        if (this.getId() == id) {
            return this;
        }
        Node resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
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
    public Node infixOrderSearch(int id) {
        Node resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
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
    public Node postOrderSearch(int id) {
        Node resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
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