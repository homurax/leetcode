/**
 * 101. Symmetric Tree
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {

    class TreeNode {
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
    public boolean isSymmetric(TreeNode root) {

        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {

        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return node1.val == node2.val && isMirror(node1.right, node2.left) && isMirror(node1.left, node2.right);
    }

    /**
     * 迭代
     */
    public boolean isSymmetric2(TreeNode root) {

        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        deque.add(root);

        while (!deque.isEmpty()) {
            TreeNode node1 = deque.poll();
            TreeNode node2 = deque.poll();
            if (node1 == null && node2 == null)
                continue;
            if (node1 == null || node2 == null)
                return false;
            if (node1.val != node2.val)
                return false;
            deque.add(node1.left);
            deque.add(node2.right);
            deque.add(node1.right);
            deque.add(node2.left);
        }

        return true;
    }
}
