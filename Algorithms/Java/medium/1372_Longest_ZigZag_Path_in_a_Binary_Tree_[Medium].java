/**
 * 1372. Longest ZigZag Path in a Binary Tree
 *
 *
 * You are given the root of a binary tree.
 *
 * A ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 *
 *
 * Example 2:
 *
 *
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 *
 *
 * Example 3:
 *
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the tree is in the range [1, 5 * 10^4].
 * 2. 1 <= Node.val <= 100
 */
public class LongestZigZagPathInABinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    private int ans = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root.left, true, 0);
        dfs(root.right, false, 0);
        return ans;
    }

    private void dfs(TreeNode node, boolean fromLeft, int path) {
        ans = Math.max(ans, path);
        if (node == null) {
            return;
        }
        if (fromLeft) {
            dfs(node.right, false, path + 1);
            dfs(node.left, true, 0);
        } else {
            dfs(node.left, true, path + 1);
            dfs(node.right, false, 0);
        }
    }


}
