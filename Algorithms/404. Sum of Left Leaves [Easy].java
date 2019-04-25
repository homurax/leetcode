/**
 * 404. Sum of Left Leaves
 *
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {

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
    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        sum += sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        return sum;
    }

    /**
     * 迭代
     */
    public int sumOfLeftLeaves2(TreeNode root) {

        if (root == null)  return 0;

        int sum = 0;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            if (poll.right != null) deque.offer(poll.right);
            if (poll.left != null) {
                if(poll.left.left == null && poll.left.right == null){
                    sum += poll.left.val;
                }
                deque.offer(poll.left);
            }
        }

        return sum;
    }
}
