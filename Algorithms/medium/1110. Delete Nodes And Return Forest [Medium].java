/**
 * 1110. Delete Nodes And Return Forest
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<TreeNode> ans;
    private boolean[] toDelete;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        this.ans = new ArrayList<>();
        this.toDelete = new boolean[1001];
        for (int val: to_delete) {
            toDelete[val] = true;
        }
        travel(root, true);
        return ans;
    }

    private boolean travel(TreeNode node, boolean currNew) {

        if (node == null) {
            return false;
        }

        if (toDelete[node.val]) {
            travel(node.left, true);
            travel(node.right, true);
            return true;
        } else {
            if (currNew) {
                ans.add(node);
            }
            if (travel(node.left, false)) {
                node.left = null;
            }
            if (travel(node.right, false)) {
                node.right = null;
            }
            return false;
        }
    }

    private TreeNode travel(TreeNode node) {

        if (node == null) return null;

        node.left = travel(node.left);
        node.right = travel(node.right);

        if (toDelete[node.val]) {
            if (node.left != null)
                ans.add(node.left);
            if (node.right != null)
                ans.add(node.right);
            return null;
        }

        return node;
    }

}
