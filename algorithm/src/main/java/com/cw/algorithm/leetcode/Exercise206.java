package com.cw.algorithm.leetcode;

/** 反转链表 */
public class Exercise206 {

      public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] argrs) {

          //输入
          int[] array = {1,2,3,4,5};
          //开始正式运行
          if (array.length <=0){
              return;
          }

          ListNode nodeHead = new ListNode(array[0]);
          ListNode previous = nodeHead;
          for (int i = 1;i<array.length;i++){
              ListNode now = new ListNode(array[i]);
              previous.next = now;
              previous  = now;

          }


          //输入nodeHead
//        previous = null;
//        ListNode next  = null;
//
//        do {
//            next = nodeHead.next;
//            nodeHead.next = previous;
//            previous = nodeHead;
//            nodeHead = next;
//        }while (nodeHead != null);

        ListNode pre = null;
        ListNode cur = nodeHead;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;

        }

        System.out.print(""+previous);

    }
}