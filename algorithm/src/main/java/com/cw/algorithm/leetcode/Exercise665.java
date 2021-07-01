package com.cw.algorithm.leetcode;

/**
 * Author: chenwei
 * <p>
 * Description:非递减数列 解题
 */
public class Exercise665 {

    public boolean checkPossibility(int[] nums) {

        if (nums == null ||nums.length <= 2) {
            return true;
        } else {

            int changeIndex = -1;
            for (int i = 0; i <= nums.length-2; i++) {


                //array[i] <= array[i + 1]是判断条件 不满足就需要改变一个值
                if (nums[i]>nums[i+1]){

                    if (changeIndex!=-1){
                        // 如果之前已经又要改变的值了
                        return false;
                    }

                    if (i==0){
                        changeIndex = 0;
                        nums[0] = nums[i+1];
                    } else if (nums[i+1]>=nums[i-1]){
                        //右边的值大于等于左边的值时,改变当前值
                        changeIndex = i;
                        nums[changeIndex] = nums[i+1];
                    }else if (nums[i+1]<nums[i-1]){
                        //右边的值小于左边的值时,改变后一个值
                        changeIndex = i+1;
                        nums[changeIndex] = nums[i];
                    }
                }

            }

            return true;
        }

    }


    public static void main(String[] argrs) {
        int[] array = {-1,4,2,3};
//        int[] array = {3,4,2,3};
//        int[] array = {4,2,1};
//        int[] array = {1,2,2,2,1};
        System.out.println("result="+new Exercise665().checkPossibility(array));
    }
}