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
            System.out.println("计算结果："+caculate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                }else if ("-".equals(s)){
                    res = num2 - num1;
                }else if ("*".equals(s)){
                    res = num2 * num1;
                }else if ("/".equals(s)){
                    res = num2 / num1;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(Integer.toString(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }

}
