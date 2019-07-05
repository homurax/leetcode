package com.homurax.algorithm.medium;

/**
 * 1038. Binary Search Tree to Greater Sum Tree
 *
 * Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 100.
 * Each node will have value between 0 and 100.
 * The given tree is a binary search tree.
 */
public class BinarySearchTreeToGreaterSumTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private int currNum = 0;

    public TreeNode bstToGst(TreeNode root) {

        travel(root);
        return root;
    }

    private void travel(TreeNode node) {

        if (node == null) return;
        travel(node.right);
        node.val = currNum += node.val;
        travel(node.left);
    }
}
