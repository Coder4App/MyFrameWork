package com.cw.algorithm.leetcode;

import java.util.Iterator;
import java.util.Stack;

/**
 * Author: chenwei
 * <p>
 * Description:简化路径
 *
 * 解题目思路 栈
 */
public class Exercise71 {

    public String simplifyPath(String path) {

        String[] array = path.split("/");

        if (array.length==1 && (array[0]==""|| array[0]==" ")){
            return "";
        }

        Stack<String> stack = new Stack<>();
        for (String content : array){
            if (content.equals("..") && stack.size()!=0){
                stack.pop();
            } else if (content.equals(".") && stack.size()==0){
                continue;
            }else if (!content.equals("") && !content.equals(".") && !content.equals("..")){
                stack.push(content);
            }
        }

        StringBuilder sb = new StringBuilder().append("/");
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next()).append("/");
        }

        if (sb.length()==1){
            return sb.toString();
        }else {
            return sb.toString().substring(0,sb.length()-1);
        }
    }


    public static void main(String[] argrs) {
        String[] test = {
                "/home/",                   //  /home
                "/../",                     //  /
                "/home//foo/",              //  /home/foo
                "/a/./b/../../c/",          //  /c
                "/a/../../b/../c//.//",     //  /c
                "/a//b////c/d//././/./",    //  /a/b/c
                "",                          // 空
                "   ",
                "/."
        };

        for (String content : test){
            Exercise71 exercise71 = new Exercise71();
            System.out.println(exercise71.simplifyPath(content));
        }
    }
}
