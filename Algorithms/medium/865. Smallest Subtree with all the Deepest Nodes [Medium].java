/**
 * 865. Smallest Subtree with all the Deepest Nodes
 *
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 *
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is that node, plus the set of all descendants of that node.
 *
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation:
 *
 * We return the node with value 2, colored in yellow in the diagram.
 * The nodes colored in blue are the deepest nodes of the tree.
 * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
 * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
 * Both the input and output have TreeNode type.
 *
 *
 * Note:
 *
 * The number of nodes in the tree will be between 1 and 500.
 * The values of each node are unique.
 */
public class SmallestSubtreeWithAllTheDeepestNodes {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private Map<TreeNode, Integer> depthMap;

    private int maxDepth;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depthMap = new HashMap<>();
        dfs(root, 0);
       return find(root);
    }

    private void dfs(TreeNode node, int depth) {
        if (node != null) {
            depthMap.put(node, depth);
            maxDepth = Math.max(maxDepth, depth);
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }

    private TreeNode find(TreeNode node) {
        if (node == null || depthMap.get(node) == maxDepth) {
            return node;
        }

        TreeNode left = find(node.left);
        TreeNode right = find(node.right);
        if (left != null && right != null) return node;

        if (left != null) {
            return left;
        } else {
            return right;
        }
    }


    /**
     * 转换一下就是找左右子节点最大深度相同且最深的节点
     */
    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        if (node == null) return null;
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);
        if (leftDepth == rightDepth) return node;
        return leftDepth > rightDepth ? dfs(node.left) : dfs(node.right);
    }

    private int getDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

}
