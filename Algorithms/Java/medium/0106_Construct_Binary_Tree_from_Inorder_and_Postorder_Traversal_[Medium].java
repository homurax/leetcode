/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 *
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public class TreeNode {
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

    // 后序遍历的最后一个元素即为根节点
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.element();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }


    private int index = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        index = inorder.length - 1;
        return buildTree(inorder, postorder, -1, index);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int a, int b) {
        if (a == b) return null;
        int val = postorder[index--];
        TreeNode root = new TreeNode(val);
        int i = b;
        while (i > a) {
            if (inorder[i] == val)
                break;
            i--;
        }
        root.right = buildTree(inorder, postorder, i, b);
        root.left = buildTree(inorder, postorder, a, i - 1);
        return root;
    }

}
