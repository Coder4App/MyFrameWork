package com.cw.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chenwei
 * <p>
 * Description:二叉树的所有路径 解题
 */
public class Exercise257 {


    public List<String> binaryTreePaths(TreeNode root) {

        List<String> array = new ArrayList<>();

        if (root != null ) {
            solution(array, root, root.val + "->");
        }

        return array;

    }

    private void solution(List<String> array, TreeNode node, String content) {

        //当该节点左右都没叶子时返回
        if (node.left == null && node.right == null) {
            array.add(content.substring(0, content.length() - 2));
            return;
        }

        //递归左边的叶子
        if (node.left != null) {
            solution(array, node.left, content + node.left.val + "->");
        }

        //递归右边的叶子
        if (node.right != null) {
            solution(array, node.right, content + node.right.val + "->");
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] argrs) {
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(3);

        treeNode1.left.left = new TreeNode(4);
        treeNode1.left.right = new TreeNode(5);

        treeNode1.left.left.left = new TreeNode(6);

        treeNode1.right.left = new TreeNode(7);
        treeNode1.right.right = new TreeNode(8);


        for (String content : new Exercise257().binaryTreePaths(treeNode1)) {
            System.out.println(content);
        }

    }
}
