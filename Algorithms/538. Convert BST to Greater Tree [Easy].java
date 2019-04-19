/**
 * 538. Convert BST to Greater Tree
 *
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 *
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *           20     13
 */
public class ConvertBSTToGreaterTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 按照右根左顺序遍历
     */
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {

        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public TreeNode convertBST2(TreeNode root) {

        int sum = 0;
        TreeNode node = root;
        LinkedList<TreeNode> deque = new LinkedList<>();
        while (!deque.isEmpty() || node != null) {
            while (node != null) {
                deque.offer(node);
                node = node.right;
            }
            node = deque.pollLast();
            sum += node.val;
            node.val = sum;
            node = node.left;
        }
        return root;
    }
}
