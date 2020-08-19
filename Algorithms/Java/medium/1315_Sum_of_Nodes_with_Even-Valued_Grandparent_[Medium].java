/**
 * 1315. Sum of Nodes with Even-Valued Grandparent
 *
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
 *
 * If there are no nodes with an even-valued grandparent, return 0.
 *
 *
 * Example 1:
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 */
public class SumOfNodesWithEvenValuedGrandparent {

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

    private int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        travel(root);
        return sum;
    }

    private void travel(TreeNode node) {
        if (node == null) return;
        if ((node.val & 1) == 0) {
            if (node.left != null) {
                if (node.left.left != null)
                    sum += node.left.left.val;
                if (node.left.right != null)
                    sum += node.left.right.val;
            }
            if (node.right != null) {
                if (node.right.left != null)
                    sum += node.right.left.val;
                if (node.right.right != null)
                    sum += node.right.right.val;
            }
        }
        travel(node.left);
        travel(node.right);
    }

}
