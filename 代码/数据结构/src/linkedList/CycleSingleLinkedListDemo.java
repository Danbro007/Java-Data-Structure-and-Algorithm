package linkedList;

/**
 * @Classname linkedList.CycleSingleLinkedListDemo
 * @Description TODO 单向环形链表的使用
 * @Date 2020/3/1 13:36
 * @Author Danrbo
 */
public class CycleSingleLinkedListDemo {

    public static void main(String[] args) {
        CycleSingleLinkedList cycleSingleLinkedList = new CycleSingleLinkedList();
        cycleSingleLinkedList.addNum(5);
        cycleSingleLinkedList.cycleOutQueue(2);
    }

}


/**
 * 单向环形列表,没有头结点
 */
class CycleSingleLinkedList {
    private Node3 first;
    private Node3 last;

    public CycleSingleLinkedList() {
    }

    /**
     * 在链表尾部添加元素
     * 1、判断链表是否为空
     *
     * @param node 要添加的节点
     */
    public void add(Node3 node) {
        if (node == null) {
            throw new RuntimeException("添加的元素为null");
        }
        //判断链表是否为空 是否的话 front指针和last指针都指向这个节点
        if (isEmpty()) {
            first = node;
            last = node;
            node.setNext(node);
        }
        //原来的链表不为空，尾部添加元素
        else {

            last.setNext(node);
            last = last.getNext();
            last.setNext(first);
        }
    }


    /**
     * 显示链表内的所有元素
     */
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        }
        Node3 cur = first;
        while (cur != last) {
            System.out.println(cur);
            cur = cur.getNext();
        }
        System.out.println(cur);
    }

    /**
     * 返回链表内的元素个数
     */
    public int size() {
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        }
        Node3 cur = first;
        int num = 1;
        while (cur != last) {
            cur = cur.getNext();
            num++;
        }
        return num;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    /**
     * 指定要添加的节点个数，生成节点添加到单向链表里。
     * @param num 要添加的节点参数
     */
    public void addNum(int num) {
        if (num < 2) {
            throw new RuntimeException("添加的个数不能小于2");
        }
        for (int i = 0; i < num; i++) {
            add(new Node3(i+1,String.format("jack%d",i+1)));
        }
    }

    /**
     * 从1开始报数，数到m的那个节点出列，它的下一位又从1开始报数，、
     * 数到m的那个节点又出列，依次类推，直到所有节点出列为止，
     * 由此产生一个出队编号的序列。
     * @param num 报数的数字
     */
    public void cycleOutQueue(int num){
        if (num < 2){
            throw new RuntimeException("数字不能小于2");
        }
        //指向当前节点的光标
        Node3 cur = first;
        //指向当前节点上一个节点的光标
        Node3 pre = last;
        while (true){
            //说明只有链表只有一个元素
            if (cur == pre){
                System.out.println(cur);
                break;
            }
            //指针后移m-1次
            for (int i = 0; i < num-1; i++) {
                pre = cur;
                cur = cur.getNext();
            }
            //出列的节点的上一个节点指向出列节点的下一个节点
            System.out.println(cur);
            //把上一个节点的next指向已经出列的下一个节点
            pre.setNext(cur.getNext());
            //cur指针指向下一个节点
            cur = cur.getNext();
        }
    }
}

/**
 * 节点
 * 只有单向
 */
class Node3 {
    private int no;
    private String name;
    private Node3 next;

    public Node3(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node3 getNext() {
        return next;
    }

    public void setNext(Node3 next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "linkedList.Node3{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}