/**
 * 226. Invert Binary Tree
 *
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 *
 * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 */
public class InvertBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }


    /**
     * 递归
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }

        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;

        return root;
    }


    /**
     * 迭代
     */
    public TreeNode invertTree2(TreeNode root) {

        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            TreeNode left = poll.left;
            if (left != null) {
                queue.offer(left);
            }
            TreeNode right = poll.right;
            if (right != null) {
                queue.offer(right);
            }
            poll.left = right;
            poll.right = left;
        }

        return root;
    }

}
