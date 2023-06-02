/**
 * 2415. Reverse Odd Levels of Binary Tree
 *
 *
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
 *
 * For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
 * Return the root of the reversed tree.
 *
 * A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
 *
 * The level of a node is the number of edges along the path between it and the root node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,3,5,8,13,21,34]
 * Output: [2,5,3,8,13,21,34]
 * Explanation:
 * The tree has only one odd level.
 * The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
 *
 *
 * Example 2:
 *
 *
 * Input: root = [7,13,11]
 * Output: [7,11,13]
 * Explanation:
 * The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
 *
 *
 * Example 3:
 *
 * Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * Explanation:
 * The odd levels have non-zero values.
 * The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
 * The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the tree is in the range [1, 2^14].
 * 2. 0 <= Node.val <= 10^5
 * 3. root is a perfect binary tree.
 */
public class ReverseOddLevelsOfBinaryTree {

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



    // DFS
    public TreeNode reverseOddLevels(TreeNode root) {
        dfs(root.left, root.right, true);
        return root;
    }

    private void dfs(TreeNode node1, TreeNode node2, boolean isOddLevel) {
        if (node1 == null) {
            return;
        }
        if (isOddLevel) {
            int t = node1.val;
            node1.val = node2.val;
            node2.val = t;
        }
        dfs(node1.left, node2.right, !isOddLevel);
        dfs(node1.right, node2.left, !isOddLevel);
    }



    // BFS
    public TreeNode reverseOddLevels1(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        for (int l = 0; nodes.get(0).left != null; l ^= 1) {
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                list.add(nodes.get(i).left);
                list.add(nodes.get(i).right);
            }
            nodes = list;
            if (l == 0) {
                for (int i = 0, n = list.size(); i < n / 2; i++) {
                    TreeNode a = list.get(i), b = list.get(n - 1 - i);
                    int t = a.val;
                    a.val = b.val;
                    b.val = t;
                }
            }
        }
        return root;
    }



}
