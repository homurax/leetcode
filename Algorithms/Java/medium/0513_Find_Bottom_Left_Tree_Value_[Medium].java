/**
 * 513. Find Bottom Left Tree Value
 *
 * Given a binary tree, find the leftmost value in the last row of the tree.
 *
 * Example 1:
 * Input:
 *
 *     2
 *    / \
 *   1   3
 *
 * Output:
 * 1
 *
 * Example 2:
 * Input:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * Output:
 * 7
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
 */
public class FindBottomLeftTreeValue {


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int findBottomLeftValue(TreeNode root) {

        int ans = root.val;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = deque.pop();
                if (i == 0) ans = pop.val;
                if (pop.left != null) deque.offer(pop.left);
                if (pop.right != null) deque.offer(pop.right);
            }
        }
        return ans;
    }


    private int depth = -1;
    private int ans;

    public int findBottomLeftValue2(TreeNode root) {
        travel(root, 0);
        return ans;
    }

    private void travel(TreeNode node, int currDepth) {

        if (node == null) return;

        int temp = currDepth++;
        travel(node.left, currDepth);
        travel(node.right, currDepth);

        if (temp > depth) {
            ans = node.val;
            depth = temp;
        }
    }


}
