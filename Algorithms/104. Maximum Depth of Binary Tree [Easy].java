/**
 * 104. Maximum Depth of Binary Tree
 *
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 */
public class MaximumDepthOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归
     */
    public int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 迭代
     */
    public int maxDepth2(TreeNode root) {

        int depth = 0;
        // javafx.util.Pair
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();

        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int currentDepth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                stack.add(new Pair(root.left, currentDepth + 1));
                stack.add(new Pair(root.right, currentDepth + 1));
            }
        }
        return depth;
    }

    public int maxDepth3(TreeNode root) {

        int depth = 0;
        if (root == null)  return depth;

        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size > 0) {
                TreeNode node = stack.poll();
                if (node.left != null) stack.offer(node.left);
                if (node.right != null) stack.offer(node.right);
                size--;
            }
            depth++;
        }
        return depth;
    }
}
