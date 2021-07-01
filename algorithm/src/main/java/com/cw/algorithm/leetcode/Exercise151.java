package com.cw.algorithm.leetcode;

/**
 * Author: chenwei
 * <p>
 * Description:翻转字符串里的单词 解题
 *
 * 解题目思路 滑动窗口
 */
public class Exercise151 {

    public String reverseWords(String s) {
        if (s.isEmpty() || s.equals(" ")){
            return "";
        }


        StringBuilder sb = new StringBuilder();
        int start =-1;
        int end = -1;
        for (int i = s.length()-1;i>=0;i--){

            if (start==-1 && s.charAt(i)!=' '){
                start = i;
            }else if (start!=-1 && s.charAt(i) ==' '){
                end = i+1;
            }

            if (i == 0 && s.charAt(i)!=' '){
                end = 0;
            }

            if (start!=-1 && end!=-1){
                sb.append(s.substring(end,start+1)).append(" ");
                start = -1;
                end = -1;
            }
        }

        if (sb.length()==0){
            return "";
        }else {
            return sb.toString().substring(0,sb.length()-1);
        }
    }


    public static void main(String[] argrs) {
        String[] test = {
//                "the sky is blue",
//                "  the sky is blue  ",
//                " the sky is blue ",
//                "  the   sky    is    blue  ",
//                "",
//                "ab",
                " ",
                "   "
        };

        for (String content : test){
            Exercise151 exercise151 = new Exercise151();
            System.out.println("result >"+exercise151.reverseWords(content)+"<");
        }

    }
}
