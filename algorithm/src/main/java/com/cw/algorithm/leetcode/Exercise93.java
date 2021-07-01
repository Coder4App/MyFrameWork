package com.cw.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chenwei
 * <p>
 * Description:复原IP地址
 *
 * 解题目思路 递归
 */
public class Exercise93 {

    public List<String> restoreIpAddresses(String s) {

        List<String> list = new ArrayList<>();


        if (s.length()>=4){
            StringBuilder sb = new StringBuilder();
            resolve(s,list,sb,0,1);
        }


        return list;
    }

    private void resolve(String s,List<String> list,StringBuilder sb,int start,int level){

        if (level == 4){

            String result = s.substring(start,s.length());

            if (!isValid(result)){
                return;
            }

            list.add(sb.append(result).toString());
            sb.replace(sb.length()-result.length(),sb.length(),"");
        }else {
            for (int i=1;i<=3;i++){

                String result = s.substring(start,start+i);
                if (start+i >= s.length()){
                    return;
                }

                if (!isValid(result)){
                    continue;
                }

                sb.append(result).append(".");
                resolve(s,list,sb,start+i,level+1);
                sb.replace(sb.length()-result.length()-1,sb.length(),"");
            }
        }

    }

    private boolean isValid(String ip){

        if (ip.length()<=3 && Integer.parseInt(ip)<=255 && ((ip.length()>=2&&ip.charAt(0)!='0') || ip.length()==1) ){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] argrs) {
        String[] test = {
//                "25525511135",
//                "4444"
//                "111",
//                "",
                "010010"
        };

        for (String content : test){

            List<String> list = new Exercise93().restoreIpAddresses(content);
            for (String result : list){
                System.out.println(result);
            }
        }
    }
}
