package queue;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 数组模拟队列
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("g(get):获取元素");
            System.out.println("a(add):添加元素");
            System.out.println("h(show head):显示队列第一个元素");
            System.out.println("e(exit):退出");
            char key = scanner.next().charAt(0);

            try {
                switch (key) {
                    case 's': {
                        arrayQueue.showQueue();
                        break;
                    }
                    case 'a': {
                        System.out.println("请输入要添加的元素");
                        arrayQueue.add(scanner.nextInt());
                        break;
                    }
                    case 'g': {
                        System.out.println("获取元素：" + arrayQueue.get());
                        break;
                    }
                    case 'h': {
                        System.out.println("头元素为："+arrayQueue.showHead());

                        break;
                    }
                    case 'e': {
                        loop = false;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("退出程序");
        scanner.close();

    }


}


class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        front = -1;
        rear = -1;
    }


    public boolean isFull() {
        return rear == maxSize -1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        rear++;
        arr[rear] = n;
    }


    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        front++;
        return arr[front];
    }


    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front+1];
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i + "]" + arr[i]);
        }
    }
}
