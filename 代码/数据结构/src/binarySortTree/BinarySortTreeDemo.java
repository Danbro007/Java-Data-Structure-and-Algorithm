package binarySortTree;

/**
 * @Classname BinarySortTreeDemo
 * @Description TODO 二叉排序树
 * @Date 2020/3/10 14:50
 * @Author Danrbo
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] array = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < array.length; i++) {
            binarySortTree.add(new Node(array[i]));
        }
        System.out.println("中序遍历：");
        binarySortTree.infixOrder();//[1,3,5,7,9,10,12]
        System.out.println("开始删除节点");
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        System.out.println("删除完成");
        binarySortTree.infixOrder();
    }
}

/**
 * 二叉排序树
 */
class BinarySortTree {
    private Node root;
    private Node pre;

    /**
     * 在树上添加节点
     *
     * @param node 要添加的节点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 树的中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("此树是空树");
        } else {
            root.infixOrder();
        }
    }


    public Node searchNode(int value) {
        if (root == null) {
            System.out.println("此树是空树");
            return null;
        } else {
            return this.root.searchNode(value);
        }
    }

    /**
     * 查询要删除节点的父节点
     * @param value 要删除节点的值
     * @return 父节点
     */
    public Node searchParentNode(int value) {
        if (root == null) {
            System.out.println("此树是空树");
            return null;
        } else {
            return this.root.searchParentNode(value);
        }
    }

    /**
     * 删除节点
     *
     * @param value 要删除的节点的值
     */
    public void delNode(int value) {
        if (root == null) {
            System.out.println("此树是空树");
            return;
        }
        Node targetNode = searchNode(value);
        //判断有没有找到要删除的节点
        if (targetNode == null) {
            System.out.println("没有找到要删除的节点");
            return;
        }
        //如果删除的节点是root既root的左右节点都为null 则直接把这个树的root设置为null
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
        } else {
            Node parentNode = searchParentNode(value);
            //如果要删除的节点是叶子节点
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                //如果要删除的点在父节点得左节点位置，则把父节点的左节点置null
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == targetNode.getValue()) {
                    parentNode.setLeft(null);
                    //如果要删除的点在父节点得右节点位置，则把父节点的右节点置null
                } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == targetNode.getValue()) {
                    parentNode.setRight(null);
                }
            }
            //如果要删除的节点是有两个子树
            else if (targetNode.getRight() != null && targetNode.getLeft() != null) {
                //在要删除的右节点找到比它大但是在右子树里最小的节点，删除这个最小值节点并返回它的值
                int minValue = delRightTreeMin(targetNode.getRight());
                //把要删除节点的value设置为刚刚找到的最小值节点的value，相当于删除了要删除的节点
                targetNode.setValue(minValue);
            }
            //如果要删除的节点只有一个子树
            else {
                //如果要删除节点的子树在左节点
                if (targetNode.getLeft() != null) {
                    /**
                     * 如果父节点为空
                     * 比如在只有10和1 的情况下 10 为root节点，此时要删除 10
                     * 10 的父节点为null， 只要判断10的左节点有子节点还是右节点有子节点
                     * 然后把root指向那个子节点就可以了
                     *      10
                     *     /
                     *   1
                     */
                    if (parentNode != null){
                        //如果要删除的点在父节点的左节点则把父节点的左节点设置为要删除的节点的左节点
                        //相当于删除节点
                        if (parentNode.getLeft().getValue() == value) {
                            parentNode.setLeft(targetNode.getLeft());
                        } else {
                            //设置父节点的右节点为要删除节点的左节点
                            parentNode.setRight(targetNode.getLeft());
                        }
                    }else {
                        //root指向要删除节点的子节点
                        root = targetNode.getLeft();
                    }

                } else {
                    if (parentNode != null){
                        if (parentNode.getRight().getValue() == value) {
                            parentNode.setRight(targetNode.getRight());
                        } else {
                            parentNode.setLeft(targetNode.getRight());
                        }
                    }else {
                        root = targetNode.getRight();
                    }

                }
            }
        }
    }

    /**
     * 在要删除节点的右子树里找到最小值，然后把他删除，并返回这个最小值节点的value
     *这个最小值说明它的值在大于要删除的节点
     * 比如：
     *             7
     *         /     \
     *        3      10
     *      / \     / \
     *    1   5    9  12
     *   /\   /\
     *  0  2 4 6
     *
     *  你要删除节点 3,它的右节点为 5 ,既在3的右子树里找到最小值 4 ，它的值在 3 和 5 之间,
     * @param node 要删除的节点的右节点
     * @return 最小值的value
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环遍历left节点找到最小值，这个最小值大于要删除的节点小于node
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        //删除找到的最小值节点
        delNode(target.getValue());
        return target.getValue();
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
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
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 在二叉排序树上添加节点
     *
     * @param node 要添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            System.out.println("要添加的节点为空节点");
        } else {
            if (node.getValue() < this.getValue()) {
                if (this.getLeft() == null) {
                    this.setLeft(node);
                } else {
                    this.getLeft().add(node);
                }
            } else {
                if (this.getRight() == null) {
                    this.setRight(node);
                } else {
                    this.getRight().add(node);
                }
            }
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
     * 寻找节点
     *
     * @param value 要寻找的值
     * @return 找打返回该node 没找到返回null
     */
    public Node searchNode(int value) {
        if (value == this.getValue()) {
            return this;
        }
        if (value < this.getValue()) {
            if (this.getLeft() != null) {
                return this.getLeft().searchNode(value);
            }
            return null;
        } else {
            if (this.getRight() != null) {
                return this.getRight().searchNode(value);
            }
            return null;
        }
    }

    /**
     * 查找value相同的节点的父节点
     *
     * @param value 要查找的值
     * @return value相同的父节点
     */
    public Node searchParentNode(int value) {
        if ((this.getLeft() != null && value == this.getLeft().getValue()) ||
                (this.getRight() != null && value == this.getRight().getValue())) {
            return this;
        } else {
            if (this.getLeft() != null && value < this.getValue()) {
                return this.getLeft().searchParentNode(value);
            } else if (value > this.getValue()) {
                return this.getRight().searchParentNode(value);
            } else {
                return null;
            }
        }
    }
}
