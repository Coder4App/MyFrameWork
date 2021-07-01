package com.cw.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: chenwei
 * <p>
 * Description:滑动窗口最大值
 */
public class Exercise239 {

    public static void main(String[] argrs) {

        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;


        //开始
        int[] result = new int[nums.length-k+1];

        if (nums == null || nums.length < 2) {
            result = nums;
        }

        Deque<Integer> deque = new LinkedList();
        for (int i = 0; i < nums.length; i++) {
            //
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.add(i);
            if (i<k){
                result[0] = nums[deque.peekFirst()];
            }else {
                if (deque.peekFirst() < i-k+1){
                    deque.pollFirst();
                }
                result[i-k+1] = nums[deque.peekFirst()];
            }
        }

        for (int s :result){
            System.out.println("----->"+s);
        }
    }
}
