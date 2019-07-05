/**
 * 112. Path Sum
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        return dfs(root, 0, sum);
    }

    private boolean dfs(TreeNode node, int currSum, int target) {

        if (node == null) return false;

        if (node.left == null && node.right == null) return currSum + node.val == target;

        return dfs(node.left, node.val + currSum, target) || dfs(node.right, node.val + currSum, target);
    }

}
