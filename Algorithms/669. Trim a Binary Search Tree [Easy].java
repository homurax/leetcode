/**
 * 669. Trim a Binary Search Tree
 *
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 *
 * Example 1:
 * Input:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 *
 * Output:
 *     1
 *       \
 *        2
 *
 * Example 2:
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 */
public class TrimABinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }


    /**
     * 递归
     *
     * node.val < L 则必定左子树都小于L 取节点右子树
     * node.val > R 则必定右子树都大于R 取节点左子树
     * 在[L, R]内 则修剪两边
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {

        if (root == null)
            return null;
        if (root.val > R)
            return trimBST(root.left, L, R);
        if (root.val < L)
            return trimBST(root.right, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    /**
     * 迭代
     *
     * 查找新的根节点 然后删除左右子树中的无效节点
     */
    public TreeNode trimBST2(TreeNode root, int L, int R) {

        if (root == null) return null;

        while (root.val < L || root.val > R) { // 查找新的根节点
            if (root.val < L) root = root.right;
            if (root.val > R) root = root.left;
        }

        TreeNode temp = root;

        while (temp != null) { // 处理左子树
            while (temp.left != null && temp.left.val < L) {
                temp.left = temp.left.right;
            }
            temp = temp.left;
        }

        temp = root;

        while (temp != null) { // 处理右子树
            while (temp.right != null && temp.right.val > R) {
                temp.right = temp.right.left;
            }
            temp = temp.right;
        }

        return root;
    }

}
