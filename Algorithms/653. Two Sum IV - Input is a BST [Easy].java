/**
 * 653. Two Sum IV - Input is a BST
 *
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * Example 1:
 *
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * Output: True
 *
 *
 * Example 2:
 *
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * Output: False
 */
public class TwoSumIVInputIsABST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * BFS
     */
    public boolean findTarget(TreeNode root, int k) {

        Set<Integer> set = new HashSet<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);

        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            if (set.contains(k - poll.val)) {
                return true;
            }
            set.add(poll.val);
            if (poll.left != null) {
                deque.offer(poll.left);
            }
            if (poll.right != null) {
                deque.offer(poll.right);
            }
        }
        return false;
    }

    /**
     * BST
     * 二叉搜索树中序遍历获得递增集合 再使用双指针遍历
     */
    private List<Integer> valList = new ArrayList<>();
    public boolean findTarget2(TreeNode root, int k) {
        inorder(root);
        int low = 0;
        int high = valList.size() - 1;
        while (low < high) {
            int sum = valList.get(low) + valList.get(high);
            if (sum == k) {
                return true;
            }

            if (sum > k) {
                high--;
            } else {
                low++;
            }
        }
        return false;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        valList.add(root.val);
        inorder(root.right);
    }
}
