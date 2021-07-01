package com.cw.algorithm.leetcode;

public class Exercise14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length ==0){
            return "";
        }

        String result = strs[0];

        for (int i = 1;i<strs.length;i++){



            String compare = strs[i];
            result = result.substring(0,Math.min(result.length(),compare.length()));

            for (int j = 0;j<result.length();j++){

                if (result.charAt(j) != compare.charAt(j)){
                    result = result.substring(0,j);

                    if (result.isEmpty()){
                        return "";
                    }

                    break;
                }

            }

        }


        return result;
    }


    public static void main(String[] argrs) {
//        String[] test = {"flower"};
//        String[] test = {"","flow","flight"};
//        String[] test = {"","",""};
//        String[] test = {"dog","racecar","car"};
//        String[] test = {"flower","flow","flight"};
//        String[] test = {""};
//        String[] test = {"f","fffff","ff"};
        String[][] test = {
                {"flower"},                           //flower
                {"","flow","flight"},                 //""
                {"","",""},                           //""
                {"dog","racecar","car"},              //""
                {"flower","flow","flight"},           //"fl"
                {""},                                 //""
                {"f","fffff","ff"},                 //"f"
                {"ffff","f","acb"},                  //""
                {"bbbb","bbbbbbb","b"}
        };

        for (String[] content : test){

            Exercise14 exercis14 = new Exercise14();
            System.out.println(exercis14.longestCommonPrefix(content));
        }
    }
}
