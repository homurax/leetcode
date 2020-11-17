/**
 * 103. Binary Tree Zigzag Level Order Traversal
 *
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

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


    public List<List<Integer>> zigzagLevelOrder0(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int left = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.remove();
                level.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            if ((left ^= 1) == 1) {
                Collections.reverse(level);
            }
            ans.add(level);
        }
        return ans;
    }



    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(ans, root, 0);
        return ans;
    }

    private void dfs( List<List<Integer>> ans, TreeNode node, int depth) {
        if (node == null) return;
        if (ans.size() == depth) {
            LinkedList<Integer> level = new LinkedList<>();
            ans.add(level);
        }
        LinkedList<Integer> level = (LinkedList<Integer>) ans.get(depth);
        if ((depth & 1) == 0) {
            level.addLast(node.val);
        } else {
            level.addFirst(node.val);
        }
        dfs(ans, node.left, depth + 1);
        dfs(ans, node.right, depth + 1);
    }
}
