/**
 * 1161. Maximum Level Sum of a Binary Tree
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 *
 * Note:
 *
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
 */
public class MaximumLevelSumOfABinaryTree {

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

    private final int[] count = new int[10001];
    private int maxLevel = 0;

    public int maxLevelSum(TreeNode root) {
        count(root, 1);
        int ans = 1, max = count[1];
        for (int i = 2; i <= maxLevel; i++) {
            if (count[i] > max) {
                max = count[i];
                ans = i;
            }
        }
        return ans;
    }

    private void count(TreeNode node, int level) {
        if (node == null) return;
        count[level] += node.val;
        maxLevel = Math.max(maxLevel, level);
        count(node.left, level + 1);
        count(node.right, level + 1);
    }

}
