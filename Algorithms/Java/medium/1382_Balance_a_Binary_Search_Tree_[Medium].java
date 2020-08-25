/**
 * 1382. Balance a Binary Search Tree
 *
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 *
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
 *
 * If there is more than one answer, return any of them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The tree nodes will have distinct values between 1 and 10^5.
 */
public class BalanceABinarySearchTree {

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


    public TreeNode balanceBST(TreeNode root) {
        // 有长度限制 可以换成数组
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return build(list, 0, list.size() - 1);
    }

    private TreeNode build(List<Integer> list, int left, int right) {
        int mid = (left + right) >> 1;
        TreeNode tree = new TreeNode(list.get(mid));
        if (left <= mid - 1) {
            tree.left = build(list, left, mid - 1);
        }
        if (mid + 1 <= right) {
            tree.right = build(list, mid + 1, right);
        }
        return tree;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

}
