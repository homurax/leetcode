/**
 * 515. Find Largest Value in Each Tree Row
 *
 * You need to find the largest value in each row of a binary tree.
 *
 * Example:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * Output: [1, 3, 9]
 */
public class FindLargestValueInEachTreeRow {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> largestValues(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode pop = deque.pop();
                max = Math.max(max, pop.val);
                if (pop.left != null) deque.offer(pop.left);
                if (pop.right != null) deque.offer(pop.right);
            }
            ans.add(max);
        }

        return ans;
    }



    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        travel(root, ans, 0);
        return ans;
    }

    private void travel(TreeNode node, List<Integer> ans, int depth) {
        if (node == null) return;
        if (depth == ans.size()) {
            ans.add(node.val);
        } else {
            ans.set(depth, Math.max(node.val, ans.get(depth)));
        }
        travel(node.left, ans, depth + 1);
        travel(node.right, ans, depth + 1);
    }

}
