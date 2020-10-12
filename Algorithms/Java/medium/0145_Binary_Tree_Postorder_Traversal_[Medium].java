/**
 * 145. Binary Tree Postorder Traversal
 *
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 *
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Example 4:
 *
 *
 * Input: root = [1,2]
 * Output: [2,1]
 *
 *
 * Example 5:
 *
 *
 * Input: root = [1,null,2]
 * Output: [2,1]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up:
 *
 * Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

    class TreeNode {
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

    // 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postOrder(root, ans);
        return ans;
    }

    private void postOrder(TreeNode node, List<Integer> ans) {
        if (node == null) return;
        postOrder(node.left, ans);
        postOrder(node.right, ans);
        ans.add(node.val);
    }


    // 迭代
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !deque.isEmpty()) {
            while (cur != null) {
                deque.addLast(cur);
                cur = cur.left;
            }
            if (!deque.isEmpty()) {
                cur = deque.pollLast();
                // 右节点都访问过（或者为空不需要访问）则访问 cur 这个根节点
                if (cur.right == null || cur.right == pre) {
                    ans.add(cur.val);
                    pre = cur;
                    cur = null;
                } else {
                    // push back 待访问的根节点 访问右节点
                    deque.addLast(cur);
                    cur = cur.right;
                }
            }
        }
        return ans;
    }

    // Morris 后序遍历
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    // 倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点
                    addPath(cur.left, ans);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        addPath(root, ans);
        return ans;
    }

    private void addPath(TreeNode node, List<Integer> ans) {
        int count = 0;
        while (node != null) {
            ans.add(node.val);
            node = node.right;
            count++;
        }
        int left = ans.size() - count;
        int right = ans.size() - 1;
        while (left < right) {
            int temp = ans.get(left);
            ans.set(left, ans.get(right));
            ans.set(right, temp);
            left++;
            right--;
        }
    }

}
