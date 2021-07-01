package com.cw.algorithm.leetcode;

/**
 * Author: chenwei
 * <p>
 * Description:验证二叉搜索树
 */
public class Exercise98 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] argrs) {


    }

    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        //先遍历左子树
        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val > pre) {
            return false;
        }

        pre = root.val;

        //左数遍历完后遍历节点的右数
        return isValidBST(root.right);
    }
}
