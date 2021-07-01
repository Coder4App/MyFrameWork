package com.cw.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Author: chenwei
 * <p>
 * Description:二叉树的所有路径 解题
 */
public class Exercise15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 如果为null或者长度小于3则返回空
        if(nums == null || nums.length < 3){
            return result;
        }

        //先给数组排个序 这样可以做超过0值的判断，然后结束遍历
        Arrays.sort(nums);

        //排序后，如果第一个值大于0，或着最后一个值小于0，就都不可能有组合是加起来为0的
        if (nums[0]>0 || nums[nums.length-1]<0){
            return result;

        }


        // 定义左右2个指针
        int left ,right;
        for (int i = 0;i<nums.length-2;i++){
            if (nums[i]>0){
                break;
            }


            //去重复
            if (i>0 && nums[i]==nums[i-1]){
                continue;
            }

            left = i+1;
            right = nums.length-1;

            while (left < right){
                int add = nums[i]+nums[left]+nums[right];

                if (add == 0){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));

                    while (left<right && nums[left]==nums[left+1]){
                        //去重复
                        left++;
                    }

                    while (left<right && nums[right]==nums[right-1]){
                        //去重复
                        right--;
                    }

                    left++;
                    right--;
                }else if (add>0){
                    //加起来的值过大 那么right就要向左移动 减小和值
                    right--;
                }else {
                    //加起来的值过小 那么left就要向右移动 增大和值
                    left++;
                }


            }
        }
        return result;
    }

    public static void main(String[] argrs) {
        int[][] test = {
                {-1, 0, 1, 2, -1, -4}
        };

        Exercise15 exercise15 = new Exercise15();
        for (int[] content : test){
            List<List<Integer>> result = exercise15.threeSum(content);
            System.out.println("---------");
            for (List<Integer> list : result){
                System.out.println("["+list.get(0)+","+list.get(1)+","+list.get(2)+"]");
            }
            System.out.println("---------");
        }
    }
}
