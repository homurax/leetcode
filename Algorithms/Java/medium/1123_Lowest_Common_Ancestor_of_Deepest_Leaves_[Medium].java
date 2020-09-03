/**
 * 1123. Lowest Common Ancestor of Deepest Leaves
 *
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The deepest leaves are the nodes with values 2 and 3.
 * The lowest common ancestor of these leaves is the node with value 1.
 * The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
 *
 * Example 2:
 *
 * Input: root = [1,2,3,4]
 * Output: [4]
 * Example 3:
 *
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 *
 *
 * Constraints:
 *
 * The given tree will have between 1 and 1000 nodes.
 * Each node of the tree will have a distinct value between 1 and 1000.
 */
public class LowestCommonAncestorOfDeepestLeaves {

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


    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int depthLeft = dfs(root.left);
        int depthRight = dfs(root.right);
        if (depthLeft == depthRight) {
            return root;
        }
        return depthLeft > depthRight ? lcaDeepestLeaves(root.left) : lcaDeepestLeaves(root.right);
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(dfs(node.left), dfs(node.right)) + 1;
    }




    //
    private TreeNode ans;
    private int maxDepth = 0;

    public TreeNode lcaDeepestLeaves2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth + 1);
        depth = Math.max(left, right);
        if (left == right && depth >= maxDepth) {
            maxDepth = depth;
            ans = node;
        }
        return depth;
    }
}
