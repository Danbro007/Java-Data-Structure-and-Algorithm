package linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        Node2 node1 = new Node2(1, "John1", "jack1");
        Node2 node2 = new Node2(2, "John2", "jack2");
        Node2 node3 = new Node2(3, "John3", "jack3");
        Node2 node4 = new Node2(4, "John4", "jack4");
        Node2 node5 = new Node2(5, "John5", "jack5");
        try {
            doubleLinkedList.addByOrder(node2);
            doubleLinkedList.addByOrder(node5);
            doubleLinkedList.addByOrder(node3);
            doubleLinkedList.addByOrder(node1);
            doubleLinkedList.addByOrder(node4);

            doubleLinkedList.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


class DoubleLinkedList {

    private Node2 head;


    public DoubleLinkedList() {

        this.head = new Node2(0, "", "");

    }

    public Node2 getHead() {
        return this.head;
    }


    public void add(Node2 node) {
        Node2 temp = getHead();
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;

    }


    public void list() {
        Node2 temp = getHead().next;
        if (temp == null) {
            throw new RuntimeException("此链表为空");
        }
        while (true) {
            if (temp.next == null) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void del(int num) {
        if (num <= 0) {
            throw new RuntimeException("删除的索引数不能小于零");
        }
        Node2 temp = getHead().next;
        if (temp == null) {
            throw new RuntimeException("此链表为空");
        }
        while (true) {
            if (temp.no == num) {
                if (temp.next == null) {
                    temp.pre.next = null;
                    temp.pre = null;
                } else {
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                }
                break;
            }
            temp = temp.next;
        }
    }

    public void update(Node2 node) {
        Node2 temp = getHead().next;
        if (temp == null) {
            throw new RuntimeException("此链表为空");
        }
        while (true) {
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                break;
            }
            temp = temp.next;
        }
    }


    public void addByOrder(Node2 node) {
        boolean flag = false;
        boolean isHead = false;
        Node2 temp = getHead();
        while (true) {
            if (temp.next == null) {
                isHead = true;
                break;
            } else if (node.no < temp.next.no) {
                temp = temp.next;
                break;
            } else if (node.no == temp.next.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            throw new RuntimeException("不能插入no相同的元素");
        }
        if (isHead){
            temp.next = node;
            node.pre = temp;
        }else {
            temp.pre.next = node;
            node.next = temp;
            node.pre = temp.pre;
            temp.pre = node;
        }



    }
}


class Node2 {
    public int no;
    public String name;
    public String nickName;
    public Node2 next;
    public Node2 pre;


    public Node2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    @Override
    public String toString() {
        return "linkedList.Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
