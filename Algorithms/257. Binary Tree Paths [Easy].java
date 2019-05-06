/**
 * 257. Binary Tree Paths
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> ans = new ArrayList<>();
        if (root == null) return ans;

        path(root, "", ans);

        return ans;
    }

    private void path(TreeNode node, String prev, List<String> ans) {

        prev = prev.isEmpty() ? Integer.toString(node.val) : prev + "->" + node.val;

        if (node.left == null && node.right == null) {
            ans.add(prev);
            return;
        }

        if (node.left != null) {
            path(node.left, prev, ans);
        }

        if (node.right != null) {
            path(node.right, prev, ans);
        }
    }
}
