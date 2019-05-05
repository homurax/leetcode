/**
 * 107. Binary Tree Level Order Traversal II
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 按层迭代 记录结果
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        LinkedList<List<Integer>> ans = new LinkedList<>();

        if (root == null) return ans;

        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);

        while (!deque.isEmpty()) {

            int size = deque.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                level.add(poll.val);
                if (poll.left != null) deque.offer(poll.left);
                if (poll.right != null) deque.offer(poll.right);
            }
            ans.offerFirst(level);
        }

        return ans;
    }


    /**
     * 第一次递归初始化集合
     * 第二次递归获取对应层级的集合记录结果
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        init(root, 1, ans);
        add(root, 1, ans);
        return ans;
    }

    private void init(TreeNode node, int level, List<List<Integer>> list) {

        if (node == null) return;

        if (list.size() < level) {
            list.add(new ArrayList<>());
        }

        init(node.left, level + 1, list);
        init(node.right, level + 1, list);
    }

    private void add(TreeNode node, int level, List<List<Integer>> list) {

        if (node == null) return;

        int index = list.size() - level;
        list.get(index).add(node.val);

        add(node.left, level + 1, list);
        add(node.right, level + 1, list);
    }
}
