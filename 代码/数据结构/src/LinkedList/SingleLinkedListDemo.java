package LinkedList;

import java.util.Stack;



public class SingleLinkedListDemo{

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        Node node1 = new Node(1, "John1", "jack1");
        Node node2 = new Node(2, "John2", "jack2");
        Node node3 = new Node(3, "John3", "jack3");
        Node node4 = new Node(4, "John4", "jack4");
        Node node5 = new Node(5, "John5", "jack5");

        Node node6 = new Node(6, "John6", "jack6");
        Node node7 = new Node(7, "John7", "jack7");
        Node node8 = new Node(8, "John8", "jack8");
        Node node9 = new Node(9, "John9", "jack9");
        Node node10 = new Node(10, "John10", "jack10");
        try {
            singleLinkedList1.addByOrder(node4);
            singleLinkedList1.addByOrder(node2);
            singleLinkedList2.addByOrder(node9);
            singleLinkedList2.addByOrder(node8);
            singleLinkedList2.addByOrder(node7);
            singleLinkedList2.addByOrder(node6);
            singleLinkedList1.addByOrder(node1);
            singleLinkedList1.addByOrder(node5);
            singleLinkedList2.addByOrder(node10);
            singleLinkedList1.addByOrder(node3);
            SingleLinkedList singleLinkedList = SingleLinkedList.mergeSingleLinkedList(singleLinkedList1, singleLinkedList2);
            singleLinkedList.list();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


class SingleLinkedList {


    private Node head;

    public SingleLinkedList() {
        this.head = new Node(0, "", "");
    }

    /**
     * 在链表尾节点添加新节点
     *
     * @param node 新节点
     */
    public void add(Node node) {
        Node temp = head;
        //循环遍历找到尾节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 在节点与节点之间按照节点的no顺序插入，如果要添加的节点no与链表里的no重复则不添加。
     *
     * @param node 新节点
     */
    public void addByOrder(Node node) {
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("添加的节点no重复，不添加到链表里。");
        } else {
            node.next = temp.next;
            temp.next = node;
        }

    }

    /**
     * 打印链表所有节点
     */
    public void list() {
        Node temp = head.next;
        while (true) {
            if (temp.next == null) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 修改no相同的节点 no不同则不修改
     *
     * @param node 修改的节点
     */
    public void update(Node node) {
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = node.name;
            temp.next.nickName = node.nickName;

        } else {
            System.out.println("没找到要修改的节点");
        }
    }

    /**
     * 通过no删除Node的no相同的节点
     *
     * @param no
     */
    public void del(int no) {
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没找到要删除的节点");
        }
    }


    /**
     * 获得头结点
     *
     * @return 头结点
     */
    public Node getHead() {
        return head;
    }

    /**
     * 获取链表里有效元素的个数
     *
     * @param head 头结点
     * @return 有效元素的个数
     */
    public static int getLength(Node head) {
        if (head.next == null) {
            return 0;
        }
        Node cur = head;
        int num = 0;
        while (true) {
            if (cur.next == null) {
                break;
            }
            num++;
            cur = cur.next;
        }
        return num;
    }

    /**
     * 获取倒数第K个的Node
     *
     * @param head      头结点
     * @param lastIndex 倒数的数
     * @return 倒数的Node
     */
    public static Node getLastIndexNode(Node head, int lastIndex) {
        if (head.next == null) {
            throw new RuntimeException("此链表为空");
        }
        if (lastIndex <= 0) {
            throw new RuntimeException("倒数所有数必须大于0");
        }
        Node cur = head.next;
        for (int i = 0; i < getLength(head) - lastIndex; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static void reverse(Node head) {
        if (head.next == null) {
            throw new RuntimeException("此链表为空");
        }
        Node reverseNode = new Node(0, "", "");
        Node cur = head.next;
        Node next = null;
        int length = getLength(head);
        for (int i = 0; i < length; i++) {
            next = cur.next;
            cur.next = reverseNode.next;
            reverseNode.next = cur;
            cur = next;
        }
        head.next = reverseNode.next;
    }

    /**
     * 反向打印
     * 先遍历链表把元素一个个的存储到栈里，使用栈的先进后出的特点，逐步取出打印。
     *
     * @param head
     */
    public static void reversePrint(Node head) {
        if (head.next == null) {
            throw new RuntimeException("此链表为空");
        }

        Stack<Node> nodeStack = new Stack<>();
        Node temp = head.next;
        while (temp != null) {
            nodeStack.add(temp);
            temp = temp.next;
        }

        while (nodeStack.size() > 0) {
            Node pop = nodeStack.pop();
            System.out.println(pop);
        }
    }

    /**
     * 合并两个单链表
     * @param leftSingleLinkedList 需要合并的链表1
     * @param rightSingleLinkedList 需要合并的链表2
     * @return 合并后的单链表
     */
    public static SingleLinkedList mergeSingleLinkedList(SingleLinkedList leftSingleLinkedList, SingleLinkedList rightSingleLinkedList) {
        Node leftTemp = leftSingleLinkedList.getHead().next;
        Node rightTemp = rightSingleLinkedList.getHead().next;
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        while (true) {
            if (leftTemp == null) {
                break;
            }
            Node left = leftTemp;
            leftTemp = leftTemp.next;
            singleLinkedList.addByOrder(left);
        }
        while (true) {
            if (rightTemp == null) {
                break;
            }
            Node right = rightTemp;
            rightTemp = rightTemp.next;
            singleLinkedList.addByOrder(right);
        }
        return singleLinkedList;
    }


}


class Node {
    public int no;
    public String name;
    public String nickName;
    public Node next;


    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    @Override
    public String toString() {
        return "LinkedList.Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
