/**
 * 111. Minimum Depth of Binary Tree
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 */
public class MinimumDepthOfBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int ans = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {

        if (root == null) { return 0; }
        travel(root, 0);
        return ans;
    }

    private void travel(TreeNode node, int depth) {

        depth++;

        if (node.left == null && node.right == null) {
            ans = Math.min(depth, ans);
            return;
        }

        if (node.left != null) { travel(node.left, depth); }
        if (node.right != null) { travel(node.right, depth); }
    }

}
