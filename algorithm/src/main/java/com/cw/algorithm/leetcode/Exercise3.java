package com.cw.algorithm.leetcode;

import java.util.HashMap;


/**
 * Author: chenwei
 * <p>
 * Description:无重复字符的最长子串
 *
 * 解题目思路 滑动窗口
 */
public class Exercise3 {


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){ return 0;}

        HashMap<Character,Integer> map = new HashMap<>();
        int start=0,length=0;

        for (int i =0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                start= Math.max(start,map.get(s.charAt(i))+1);
            }

            map.put(s.charAt(i),i);
            length = Math.max(length,i-start+1);
        }

        return length;
    }

    public static void main(String[] argrs) {
//        String[] test = {"abcabccbb","bbbbb","pwwkew",""," ","au","dvdf","abcafgh"};
        String[] test = {"abba"};
        Exercise3 exercise3 = new Exercise3();
        for (String s : test){
            System.out.println(exercise3.lengthOfLongestSubstring(s));
        }
    }

}
