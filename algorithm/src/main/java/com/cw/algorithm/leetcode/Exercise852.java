package com.cw.algorithm.leetcode;


/**
 * Author: chenwei
 * <p>
 * Description:山脉数组的峰顶索引 解题
 */
public class Exercise852 {

    public int peakIndexInMountainArray(int[] A) {
        return interceptArray(A,0,A.length-1);
    }

    private int interceptArray(int[] array, int start, int end) {
         int mid;

         if ((end - start) == 1) {
             return array[start] > array[end] ? start : end;
         } else {
             mid = (end + start) / 2;//取中间索引
             if (array[mid] > array[mid - 1] && array[mid] > array[mid + 1]) {
                 return mid;
             } else if (array[mid] < array[mid - 1]) {
                 //最高在左边
                return interceptArray(array, start, mid);

             } else {
                 //最高在右边
                return interceptArray(array, mid, end);
             }
         }

     }


    public static void main(String[] argrs) {
        int[] A = {1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1};
        System.out.println("indx="+new Exercise852().peakIndexInMountainArray(A));
    }
}
