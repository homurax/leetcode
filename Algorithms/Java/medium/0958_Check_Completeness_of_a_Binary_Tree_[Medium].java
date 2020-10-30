/**
 * 958. Check Completeness of a Binary Tree
 *
 *
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Note:
 *
 * The tree will have between 1 and 100 nodes.
 */
public class CheckCompletenessOfABinaryTree {

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


    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.peek();
            if (curr.left != null && curr.right != null) {
                queue.poll();
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
            if (curr.left == null && curr.right != null) {
                return false;
            }
            // curr 之后的所有节点都应为叶子节点
            if ((curr.left != null && curr.right == null) || (curr.left == curr.right)) {
                if (curr.left != null && curr.right == null) {
                    queue.offer(curr.left);
                }
                queue.poll();
                while (!queue.isEmpty()) {
                    curr = queue.peek();
                    if (curr.left == curr.right) {
                        queue.poll();
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }


    /**
     * 用 (depth, position) 描述一个节点的位置信息
     * 对于一个节点:
     *  左孩子为：(depth, position) -> (depth + 1, position * 2)
     *  右孩子为 (depth, position) -> (depth + 1, position * 2 + 1)
     *
     * 也就是说用 1 表示根节点，对于任意一个节点 v，它的左孩子为 2*v 右孩子为 2*v + 1
     */
    private int count = 0;
    private int index = 0;

    public boolean isCompleteTree2(TreeNode root) {
        dfs(root, 1);
        return count == index;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) return;
        count++;
        index = Math.max(index, i);
        dfs(root.left, i * 2);
        dfs(root.right, i * 2 + 1);
    }

}
