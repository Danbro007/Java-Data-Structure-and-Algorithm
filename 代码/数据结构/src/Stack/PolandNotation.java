package Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Classname PolandNotation
 * @Description TODO 通过逆波兰表达式来计算算式
 * @Date 2020/3/2 13:58
 * @Author Danrbo
 */
public class PolandNotation {


    public static void main(String[] args) {
        //逆波兰表达式
        String expression = "30 4 + 5 * 6 -";
        List<String> listFromString = getListFromString(expression);
        try {
            int caculate = calculate(listFromString);
            System.out.println("计算结果：" + caculate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //中缀表达式转换后缀表达式
        String infixExpress = "1+((2+3)*4)-5";
        //转换成List [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> list = toInfixExpress(infixExpress);
        //将[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]转换成[1, 2, 3, +, 4, *, +, 5, -]
        List<String> suffixExpressionList = parseSuffixExpressionList(list);
        System.out.println(suffixExpressionList);
        int calculate = calculate(suffixExpressionList);
        //结果为16
        System.out.println(calculate);
    }

    /**
     * 把字符串转换成List
     *
     * @param expression 逆波兰表达式
     * @return 转换成List
     */
    public static List<String> getListFromString(String expression) {
        List<String> stringArrayList = new ArrayList<>();
        String[] split = expression.split(" ");
        Collections.addAll(stringArrayList, split);
        return stringArrayList;
    }

    /**
     * 计算通过逆波兰表达式转换成List的结果
     *
     * @param list 元素List
     * @return 计算结果
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            //正则匹配多位数字
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int res = 0;
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if ("+".equals(s)) {
                    res = num2 + num1;
                } else if ("-".equals(s)) {
                    res = num2 - num1;
                } else if ("*".equals(s)) {
                    res = num2 * num1;
                } else if ("/".equals(s)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(Integer.toString(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 把中缀表达式转换成List
     *
     * @param express 中缀表达式
     * @return 转换后的List
     */
    public static List<String> toInfixExpress(String express) {
        List<String> list = new ArrayList<>();
        char[] chars = express.toCharArray();
        String num = "";
        for (char c : chars) {
            if (c < 52 && c > 48) {
                num = num + String.valueOf(c);
            } else {
                if (!num.isEmpty()) {
                    list.add(num);
                    num = "";
                }
                list.add(String.valueOf(c));
            }
        }
        return list;
    }


    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String s : list) {
            //判断是否是数字，如果是则压入s2栈
            if (s.matches("\\d+")) {
                s2.add(s);
            }
            //如果是 ( 则压入 s1 栈
            else if ("(".equals(s)) {
                s1.push(s);
            }
            //如果是 ) 则 s1 不停出栈压入 s2 直到 s1 的栈顶为 ( ,然后再把 ( 弹出栈
            else if (")".equals(s)) {
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //把 ( 弹出 s1
                s1.pop();
            }
            //如果是运算符
            else {
                //当 s1 内有元素，并且新的元素优先级小于等于 s1 栈顶元素则不停地弹出 s1 的栈顶 压入 s2，
                //当 s1 为空或者新的元素优先级大于 s1 栈顶元素,则把新的元素压入 s1 栈内
                while (s1.size() > 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                //新元素压入 s1
                s1.push(s);
            }

        }
        //把 s1 内的所有元素 压入 s2
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DVI = 2;

    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "*":
                res = MUL;
                break;
            case "/":
                res = DVI;
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            default:
                break;
        }
        return res;
    }
}


