package com.cw.algorithm.leetcode;

/** 反转链表 */
public class Exercise24 {

      public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] argrs) {

        //输入
        int[] array = {1,2,3,4};
        //开始正式运行
        if (array.length <= 0) {
            return;
        }

        ListNode nodeHead = new ListNode(array[0]);
        ListNode previous = nodeHead;
        for (int i = 1; i < array.length; i++) {
            ListNode now = new ListNode(array[i]);
            previous.next = now;
            previous = now;

        }


//        int count = 0;
//        ListNode cur = nodeHead;
//        ListNode pre = null;
//        do {
//
//            if (count%2!=0){
//                int temp = pre.val;
//                pre.val = cur.val;
//                cur.val = temp;
//            }
//            pre = cur;
//            cur = cur.next;
//            count++;
//        }while (cur!= null);

        ListNode start = new ListNode(0);
        start.next = nodeHead;
        ListNode pre = start;
        while (pre.next != null && pre.next.next != null){
            ListNode a = pre.next;
            ListNode b = pre.next.next;
            pre.next = b;
            a.next = b.next;
            b.next = a;
            pre = a;

        }

        System.out.print(start.next);
    }

}