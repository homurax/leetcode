/**
 * 671. Second Minimum Node In a Binary Tree
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 *
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 *
 * Example 2:
 *
 * Input:
 *     2
 *    / \
 *   2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinimumNodeInABinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 注意 Second Minimum 可能为 Integer.MAX_VALUE
     * 所以 ans 使用Long.MAX_VALUE 初始化
     */
    private long ans = Long.MAX_VALUE;
    private int min;

    public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        dfs(root);
        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }

    private void dfs(TreeNode node) {

        int val = node.val;
        if (val > min && val < ans) {
            ans = val;
        } else if (min == val){
            if (node.right != null) dfs(node.right);
            if (node.left != null) dfs(node.left);
        }
    }
}
