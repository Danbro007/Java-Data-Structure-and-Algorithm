/**
 * @Classname ArrayStackDemo
 * @Description TODO
 * @Date 2020/3/1 15:22
 * @Author Danrbo
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.list();
        int pop = arrayStack.pop();
        System.out.println(pop);
        arrayStack.list();
    }
}

/**
 * 用数组来模拟栈
 */
class ArrayStack{

    private int maxSize;
    private int top;
    /**
     * 数组模拟栈
     */
    private int[] arr;


    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        this.top = -1;
    }


    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int n){
        if (isFull()){
            throw new RuntimeException("栈已满");
        }
        top++;
        arr[top] = n;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈已空");
        }
        int value = arr[top];
        top--;
        return value;
    }

    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈已空");
        }
        int cur = 0;
        while (cur <= top){
            System.out.print(arr[cur] + " ");
            cur++;
        }
        System.out.println();
    }
}
