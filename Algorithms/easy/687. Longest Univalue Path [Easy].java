/**
 * 687. Longest Univalue Path
 *
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output: 2
 *
 *
 *
 * Example 2:
 *
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 2
 *
 *
 *
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUnivaluePath {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int count = 0;

    public int longestUnivaluePath(TreeNode root) {

        if (root == null) { return 0; }
        travel(root);
        return count;
    }

    private int travel(TreeNode node) {

        if (node == null) { return 0; }

        int left = travel(node.left);
        int right = travel(node.right);

        int sumLeft = 0, sumRight = 0;
        if (node.left != null && node.left.val == node.val) {
            sumLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            sumRight += right + 1;
        }

        count = Math.max(count, sumLeft + sumRight);
        return Math.max(sumLeft, sumRight);
    }


}
