package com.cw.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Exercise1 {


    public static void main(String[] argrs) {
        int[] num = {2,7,11,15};
        int target = 9;

        int[] result = new Exercise1().twoSum(num,target);
        System.out.print("["+result[0]+","+result[1]+"]");

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            if (hashtable.containsKey(target-nums[i])){
                return new int[]{i,hashtable.get(target-nums[i])};
            }
            hashtable.put(nums[i],i);
        }

        return new int[]{};
    }

}
