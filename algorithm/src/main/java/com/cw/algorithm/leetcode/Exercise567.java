package com.cw.algorithm.leetcode;

/**
 * Author: chenwei
 * <p>
 * Description:字符串的排列 解题
 *
 * 解题目思路 滑动窗口+hash表
 */
public class Exercise567 {


    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;

        int[] s1map = new int[26];
        int[] s2map = new int[26];

        //构建一个26长度的数组，记录a-z每个字母出现的个数，循环次数为长度最小的数组的长度
        for (int i = 0;i<s1.length();i++){
            s1map[s1.charAt(i)-'a']++;
            s2map[s2.charAt(i)-'a']++;
        }



        //使用滑动窗口

        for (int i = 0;i<(s2.length()-s1.length()+1);i++){
            if (check(s1map,s2map)){
                return true;
            }

            //最后一次只需要比较不能在移动窗口了
            if ((i+s1.length()<=(s2.length()-1))){
                //向右滑动窗口
                s2map[s2.charAt(i)-'a']--;
                s2map[s2.charAt(i+s1.length())-'a']++;

            }
        }

        return false;

    }

    private boolean check(int[] s1map,int[] s2map){

        for (int j = 0;j<26;j++){
            if (s1map[j] != s2map[j]){
                return false;
            }
        }

        return true;
    }


    public static void main(String[] argrs) {
        String[][] test = {
                {"ab","eidbaooo"},
                {"ab","ooba"},
                {"ab","eidboaoo"},
                {"",""},
                {"ab","a"}
        };

        for (String[] content : test){

            Exercise567 exercise567 = new Exercise567();
            System.out.println("result "+exercise567.checkInclusion(content[0],content[1]));
        }
    }
}
