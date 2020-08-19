/**
 * 1302. Deepest Leaves Sum
 *
 * Given a binary tree, return the sum of values of its deepest leaves.
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 */
public class DeepestLeavesSum {

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

    private int maxLevel = 0;
    private int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        travel(root, 1);
        return sum;
    }

    private void travel(TreeNode node, int curLevel) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            if (curLevel == maxLevel) {
                sum += node.val;
            } else if (curLevel > maxLevel) {
                maxLevel = curLevel;
                sum = node.val;
            }
        }
        travel(node.left, curLevel + 1);
        travel(node.right, curLevel + 1);
    }

}
