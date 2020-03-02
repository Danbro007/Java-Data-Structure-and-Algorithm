package Stack;

/**
 * @Classname Stack.SingleLinkedListStackDemo
 * @Description TODO 用单链表来实现栈
 * @Date 2020/3/1 21:33
 * @Author Danrbo
 */
public class SingleLinkedListStackDemo {

    public static void main(String[] args) {
        SingleLinkedListStack singleLinkedListStack = new SingleLinkedListStack(4);

        try {
            for (int i = 0; i < 4; i++) {
                singleLinkedListStack.push(new Node(i+1,String.format("Jack%d",i+1),String.format("John%d",i+1)));
            }
            singleLinkedListStack.reverse();
            singleLinkedListStack.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


class SingleLinkedListStack {
    private int maxSize;
    private Node top;
    private Node head;


    public SingleLinkedListStack(int maxSize) {
        if (maxSize < 1){
            throw new RuntimeException("栈的容量不能小于1");
        }
        this.maxSize = maxSize;
        head = new Node(0, "", "");
        top = head;
    }


    public boolean isEmpty() {
        return head.next  == null;
    }

    public boolean isFull() {
        return getSize() == maxSize;
    }

    public int getSize() {
        if (isEmpty()) {
            return 0;
        }
        int num = 1;
        Node cur = head.next;
        while (cur != top) {
            cur = cur.next;
            num++;
        }
        return num;
    }


    public void push(Node node) {
        if (isFull()) {
            throw new RuntimeException("栈已满");
        }
        top.next = node;
        top = node;
    }

    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        Node cur = head;
        while (cur.next != top) {
            cur = cur.next;
        }
        cur.next = null;
        Node value = top;
        top = cur;
        return value;
    }


    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        Node cur = head.next;
        while (cur != null){
            System.out.println(cur);
            cur = cur.next;
        }
    }

    public void reverse(){
        Node reverseNode = new Node(0, "", "");
        Node cur = head.next;
        Node next;
        int size = getSize();
        for (int i = 0; i <size; i++) {
            next = cur.next;
            cur.next = reverseNode.next;
            reverseNode.next = cur;
            cur = next;
        }
        head.next = reverseNode.next;
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

