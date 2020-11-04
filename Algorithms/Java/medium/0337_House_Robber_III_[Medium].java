/**
 * 337. House Robber III
 *
 *
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {

    public class TreeNode {
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


    public int rob(TreeNode root) {
        int[] status = dfs(root);
        return Math.max(status[0], status[1]);
    }


    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        int[] L = dfs(node.left);
        int[] R = dfs(node.right);
        // 选择当前，左右都不可选
        int selectCurr = node.val + L[1] + R[1];
        // 不选择当前，左右均为可选可不选
        int notSelectCurr = Math.max(L[0], L[1]) + Math.max(R[0], R[1]);
        return new int[]{selectCurr, notSelectCurr};
    }

}
