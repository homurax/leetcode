/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 *
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 *
 *
 *
 * Example 1:
 *
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 *
 * Note:
 *
 * 1 <= preorder.length <= 100
 * The values of preorder are distinct.
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {

        return travel(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode travel(int[] preorder, int min, int max) {

        if (i == preorder.length || preorder[i] < min || preorder[i] > max)
            return null;

        int rootVal = preorder[i++];
        TreeNode root = new TreeNode(rootVal);
        root.left = travel(preorder, min, rootVal);
        root.right = travel(preorder, rootVal, max);

        return root;
    }

}
