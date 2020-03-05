package queue;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 数组模拟环形队列
 */
public class CycleArrayQueueDemo {
    public static void main(String[] args) {
        CycleArrayQueue cycleArrayQueue = new CycleArrayQueue(4);
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
                        cycleArrayQueue.showQueue();
                        break;
                    }
                    case 'a': {
                        System.out.println("请输入要添加的元素");
                        cycleArrayQueue.add(scanner.nextInt());
                        break;
                    }
                    case 'g': {
                        System.out.println("获取元素：" + cycleArrayQueue.get());
                        break;
                    }
                    case 'h': {
                        System.out.println("头元素为：" + cycleArrayQueue.showHead());

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

class CycleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CycleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        //front和rear都指向索引为0的位置
        front = 0;
        rear = 0;
    }


    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }


    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }


    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = front; i < front + showNum(); i++) {
            System.out.println("arr[" + i % maxSize + "]" + arr[i % maxSize]);
        }
    }

    public int showNum() {
        return (rear + maxSize - front) % maxSize;
    }
}
