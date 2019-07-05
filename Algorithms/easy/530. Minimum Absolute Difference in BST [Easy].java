/**
 * 530. Minimum Absolute Difference in BST
 *
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * Output:
 * 1
 *
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 *
 *
 * Note: There are at least two nodes in this BST.
 */
public class MinimumAbsoluteDifferenceInBST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 先中序遍历BST 在求差值绝对值的最小值
     */
    public int getMinimumDifference(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            ans = Math.min(ans, Math.abs(list.get(i) - list.get(i+1)));
        }
        return ans;
    }
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }





    private int diff = Integer.MAX_VALUE;
    private TreeNode prev;
    public int getMinimumDifference2(TreeNode root) {
        inorder(root);
        return diff;
    }
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (prev != null) {
            diff = Math.min(diff, root.val - prev.val);
        }
        prev = root;
        inorder(root.right);
    }
}
