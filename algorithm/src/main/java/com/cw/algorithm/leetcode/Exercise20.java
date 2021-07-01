package com.cw.algorithm.leetcode;

import java.util.Stack;

public class Exercise20 {

    public static void main(String[] argrs) {
        String input = "(]){";


       System.out.println("result "+resolve(input));

    }

    private static boolean resolve(String input){
        if (input.isEmpty()){
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (Character c:input.toCharArray()){
            if ('(' == c){
                stack.push(')');
            }else if ('{' == c){
                stack.push('}');
            }else if ('[' == c){
                stack.push(']');
            }else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }

        if (stack.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
