/**
 * 701. Insert into a Binary Search Tree
 *
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * For example,
 *
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * And the value to insert: 5
 * You can return this binary search tree:
 *
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 * This tree is also valid:
 *
 *          5
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *          \
 *           4
 */
public class InsertIntoABinarySearchTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {

        TreeNode node = root;
        boolean insert = false;

        while (node != null && !insert) {
            if (val > node.val) {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    insert = true;
                } else {
                    node = node.right;
                }
            } else {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    insert = true;
                } else {
                    node = node.left;
                }
            }
        }

        return root;
    }



    public TreeNode insertIntoBST2(TreeNode root, int val) {

        if (root == null) {
            root = new TreeNode(val);
        } else if (val > root.val) {
            root.right = insertIntoBST2(root.right, val);
            return root;
        } else {
            root.left = insertIntoBST2(root.left, val);
            return root;
        }
        return root;
    }
}
