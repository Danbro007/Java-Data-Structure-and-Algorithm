package stack;

/**
 * @Classname stack.InfixExpressionDemo
 * @Description TODO 用中缀表达式和栈来实现计算器
 * @Date 2020/3/2 11:07
 * @Author Danrbo
 */
public class InfixExpressionDemo {
    public static void main(String[] args) {
        //数字栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);
        //要运算的计算表达式
        String expression = "1*(2+6)-11";
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int index = 0;

        boolean isBracket = false;
        String str = "";
        //压栈
        while (index < expression.length()) {
            //获取
            char ch = expression.charAt(index);
            //判断这个字符是否是操作符
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    //判断符号优先级，如果要压入的符号优先级比符号栈的栈顶符号优先级低，
                    //比如符号 + 的优先级小于符号 * 则先从数字栈里弹出两个数字比如是 2 和 3 ，从符号栈
                    // 把符号 * 弹出，计算 2 * 3的值，然后得出6再压入数字栈，最后再把符号 + 压入符号栈
                    if (operStack.getOperPriority(ch) < operStack.getOperPriority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        int lastOper = operStack.pop();
                        res = numStack.calculate(num1, num2, lastOper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }

            }
            //判断是否是前括号 (
            else if (operStack.isLeftHalBracket(ch)){
                operStack.push(ch);
            }
            //判断是否是后括号 )
            else if (operStack.isRightHalBracket(ch)){
                int oper = 0;
                while (true){
                    //取出两个数字和一个操作符
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper =  operStack.pop();
                    //计算结果
                    res = numStack.calculate(num1,num2,oper);
                    //把结果压入栈内
                    numStack.push(res);
                    //获取符号栈的栈顶
                    oper = operStack.peek();
                    //判断是不是 （,如果是则代表括号内计算结束
                    if (oper == '('){
                        //把符号 ( 弹出栈
                        operStack.pop();
                        break;
                    }
                }
            }

            else {
                //字符串追加
                str += ch;
                try {
                    //
                    char nextCh = expression.substring(index+1,index+2).charAt(0);
                    if (operStack.isOper(nextCh) || operStack.isRightHalBracket(nextCh) || operStack.isLeftHalBracket(nextCh)){
                        numStack.push(Integer.parseInt(str));
                        str = "";
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    numStack.push(Integer.parseInt(str));
                    break;
                }
            }
            index++;
        }
        //出栈计算
        while (!operStack.isEmpty()){
            //从numStack弹出两个数然后从符号栈弹出一个操作符然后计算
            num1 = numStack.pop();
            num2 = numStack.pop();
            int oper = operStack.pop();
            res = numStack.calculate(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println("计算结果为:"+numStack.pop());



    }

}


/**
 * 用数组来模拟栈
 */
class ArrayStack2 {

    private int maxSize;
    private int top;
    /**
     * 数组模拟栈
     */
    private int[] arr;


    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        this.top = -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int n) {
        if (isFull()) {
            throw new RuntimeException("栈已满");
        }
        top++;
        arr[top] = n;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int value = arr[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int cur = 0;
        while (cur <= top) {
            System.out.print(arr[cur] + " ");
            cur++;
        }
        System.out.println();
    }

    /**
     * 获取符号的优先级
     *
     * @param oper 符号
     * @return 符号的优先级值
     */
    public int getOperPriority(int oper) {
        if (oper == '*' || oper == '%') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    /**
     * 判断字符是否是操作符
     * @param c 要判断的字符
     * @return true:是操作符 false:不是操作符
     */
    public boolean isOper(char c) {
        return c == '*' || c == '%' || c == '+' || c == '-';
    }

    /**
     * 判断字符是否是前括号
     * @param c 字符
     * @return true：是 false:不是
     */
    public boolean isLeftHalBracket(char c){
        return c == '(';
    }

    /**
     * 判断字符是否是后括号
     * @param c 字符
     * @return true：是 false:不是
     */
    public boolean isRightHalBracket(char c){
        return c == ')';
    }


    /**
     * 返回栈顶的元素 元素并不出栈
     *
     * @return 栈顶元素
     */
    public int peek() {
        return arr[top];
    }

    /**
     * 计算结果
     *
     * @param num1 第一个出栈的数字
     * @param num2 第二个出栈的数字
     * @param oper 操作符
     * @return 计算结果
     */
    public int calculate(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '%':
                res = num2 % num1;
                break;
        }
        return res;

    }


}

