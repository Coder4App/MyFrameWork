package com.cw.algorithm.leetcode;

/**
 * Author: chenwei
 * <p>
 * Description:亲密字符串 解题
 */
public class Exercise859 {


//    public boolean buddyStrings(String A, String B) {
//
//        char[] arrayA = A.toCharArray();
//        char[] arrayB = B.toCharArray();
//
//
//        if (A.length()!=B.length() || A.length()==0 || B.length()==0){
//            return false;
//        }
//
//
//        String diffA="";
//        String diffB="";
//        String same="";
//        for (int i =0;i<A.length();i++){
//
//            if (arrayA[i]!=arrayB[i]){
//                //相同位置字符出现不同
//                diffA += arrayA[i];
//                diffB += arrayB[i];
//            }else{
//                //出现的相同字符
//                if (!same.contains(arrayA[i]+"")){
//                    same += arrayA[i];
//                }
//            }
//        }
//
//
//        if (diffA.length() == 2 && diffA.toCharArray()[0]==diffB.toCharArray()[1] && diffA.toCharArray()[1]==diffB.toCharArray()[0]){
//            //俩字符串有不同，且不同处只有2处，并且2不同的字符串抛开顺序包含相同字符
//            return true;
//        }
//
//        if (A.equals(B) && !A.equals(same)){
//            return true;
//        }
//
//
//        return false;
//
//    }


    public boolean buddyStrings(String A, String B) {

        char[] arrayA = A.toCharArray();
//        char[] arrayB = B.toCharArray();


        if (A.length()!=B.length() || A.length()==0 || B.length()==0){
            return false;
        }


        for (int i =0;i<A.length();i++){

        }

        return true;
    }


    /**
     * 测试用例
     * 1ab/ba true
     * 2ab/ab false
     * 3aa/aa true
     * 4aaaaaaabc/aaaaaaacb ture
     * 5""/aa false
     * 6 aabcd/aacdb false
     * 7 aabac/aabad false
     * 8 aaaaaab/aaaaaab true
     * 9 ""/"" false
     * 10 aaabcd/aaabcd true
     * 11 abcd/abcd false
     * 12 acb/acb false
     * 13 aabcde/aacbdf false
     */
    public static void main(String[] argrs) {
        String A1 = "ab";
        String B1 = "ba";

        String A2 = "ab";
        String B2 = "ab";

        String A3 = "aa";
        String B3 = "aa";

        String A4 = "aaaaaaabc";
        String B4 = "aaaaaaacb";

        String A5 = "";
        String B5 = "aa";

        String A6 = "aabcd";
        String B6 = "aacdb";

        String A7 = "aabac";
        String B7 = "aabad";

        String A8 = "aaaaaab";
        String B8 = "aaaaaab";

        String A9 = "";
        String B9 = "";

        String A10 = "aaabcd";
        String B10 = "aaabcd";

        String A11 = "abcd";
        String B11 = "abcd";

        String A12 = "acb";
        String B12 = "acb";

        String A13 = "aabcde";
        String B13 = "aacbdf";

        System.out.println("reslut="+new Exercise859().buddyStrings(A1,B1));
        System.out.println("reslut="+new Exercise859().buddyStrings(A2,B2));
        System.out.println("reslut="+new Exercise859().buddyStrings(A3,B3));
        System.out.println("reslut="+new Exercise859().buddyStrings(A4,B4));
        System.out.println("reslut="+new Exercise859().buddyStrings(A5,B5));
        System.out.println("reslut="+new Exercise859().buddyStrings(A6,B6));
        System.out.println("reslut="+new Exercise859().buddyStrings(A7,B7));
        System.out.println("reslut="+new Exercise859().buddyStrings(A8,B8));
        System.out.println("reslut="+new Exercise859().buddyStrings(A9,B9));
        System.out.println("reslut="+new Exercise859().buddyStrings(A10,B10));
        System.out.println("reslut="+new Exercise859().buddyStrings(A11,B11));
        System.out.println("reslut="+new Exercise859().buddyStrings(A12,B12));
        System.out.println("reslut="+new Exercise859().buddyStrings(A13,B13));

    }
}
