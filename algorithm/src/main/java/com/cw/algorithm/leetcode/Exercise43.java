package com.cw.algorithm.leetcode;

/**
 * Author: chenwei
 * <p>
 * Description:字符串相乘
 *
 * 解题目思路 竖相乘 用一个num1.length()+num2.length长度的数组记录每个位的值
 */
public class Exercise43 {

    public String multiply(String num1, String num2) {

        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        int[] content = new int[num1.length()+num2.length()];
        int result;
        int index;
        for (int i = num1.length()-1;i>=0;i--){
            int Multiplicand = num1.charAt(i)-'0';

            for (int j = num2.length()-1;j>=0;j--){
                index = j+i+1;//j+i+
                result = (num2.charAt(j)-'0') *  Multiplicand + content[index] ;
                content[index] = result%10;
                content[index-1] += result/10;

            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content.length; i++) {
            if (i == 0 && content[i] == 0) continue;
            sb.append(content[i]);
        }
        return sb.toString();
    }


    public static void main(String[] argrs) {
        String[][] test = {
                {"2","3"},
                {"777","8888"},
                {"0","0"},
                {"123","456"},
                {"9999","0000"}
        };
//
        for (String[] content : test){
            Exercise43 exercise43 = new Exercise43();
            System.out.println(exercise43.multiply(content[0],content[1]));
        }
//        int a = '9'-'0';


    }
}
