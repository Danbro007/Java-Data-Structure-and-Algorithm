package kmp;

import java.util.Calendar;

/**
 * @Classname ViolenceMatch
 * @Description TODO 用暴力破解匹配字符串
 * @Date 2020/3/13 13:47
 * @Author Danrbo
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String s1 = "硅硅谷 尚硅谷你硅谷 尚硅谷你尚硅谷你尚硅你好";
        String s2 = "尚硅谷你尚硅你";
        int res = violenceMatch(s1, s2);
        if (res != -1) {
            System.out.println("匹配成功，起始索引为：" + res);
        }else {
            System.out.println("匹配失败！");
        }
    }

    public static int violenceMatch(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        //指向char1的指针
        int i = 0;
        //指向char2的指针
        int j = 0;
        while (i < char1.length && j < char2.length) {
            if (char1[i] == char2[j]) {
                i++;
                j++;
            } else {
                //匹配失败则把 i 放回到匹配前字符的下一个
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == char2.length) {
            return i - j;
        } else {
            return -1;
        }
    }

}
