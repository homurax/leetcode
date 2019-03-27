/**
 * 872. Leaf-Similar Trees
 *
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 *
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 * Note:
 *
 * Both of the given trees will have between 1 and 100 nodes.
 */
public class LeafSimilarTrees {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * DFS
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> r1List = new ArrayList<>();
        List<Integer> r2List = new ArrayList<>();

        dfs(root1, r1List);
        dfs(root2, r2List);

        return r1List.equals(r2List);
    }

    private void dfs(TreeNode node, List<Integer> list) {

        if (node == null)
            return;

        if (node.left == null && node.right == null)
            list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);

    }
}
