/**
 * 1339. Maximum Product of Splitted Binary Tree
 *
 *
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 *
 *
 * Example 2:
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 *
 * Example 3:
 *
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 *
 * Example 4:
 *
 * Input: root = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. Each tree has at most 50000 nodes and at least 2 nodes.
 * 2. Each node's value is between [1, 10000].
 */
public class MaximumProductOfSplittedBinaryTree {

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


    private int sum = 0;
    private int mid = 0;

    // sum 为定值, a 越接近 sum 的一半, a * (sum - a) 越大
    public int maxProduct(TreeNode root) {
        sumNode(root);
        findMid(root);
        return (int) (((long) mid * (sum - mid)) % 1000000007);
    }

    private void sumNode(TreeNode node) {
        if (node == null) {
            return;
        }
        sum += node.val;
        sumNode(node.left);
        sumNode(node.right);
    }

    private int findMid(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int cur = node.val + findMid(node.left) + findMid(node.right);
        if (Math.abs(cur * 2 - sum) < Math.abs(mid * 2 - sum)) {
            mid = cur;
        }
        return cur;
    }

}
