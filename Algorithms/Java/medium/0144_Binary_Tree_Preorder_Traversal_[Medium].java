/**
 * 144. Binary Tree Preorder Traversal
 *
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
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
 * Output: [1,2]
 *
 *
 * Example 5:
 *
 *
 * Input: root = [1,null,2]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up:
 *
 * Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal {

    static class TreeNode {
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

    // recursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preOrder(root, ans);
        return ans;
    }

    private void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    // iteratively
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            ans.add(node.val);
            if (node.right != null) deque.offerLast(node.right);
            if (node.left != null) deque.offerLast(node.left);
        }
        return ans;
    }

    /**
     * Morris 遍历
     *
     * 二叉树的线索化:
     *  所有原本为空的左指针改为指向该节点的中序序列的前驱
     *  所有原本为空的右指针改为指向该节点在中序序列中的后继
     *
     * 对于当前节点，从其左节点沿着右指针一直往右走，直到走到叶子节点为止，这个叶子节点就是当前节点在中序遍历中的前驱节点。
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                // 找前驱节点
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 前驱节点右指针为空（说明是第一次被访问到）
                if (pre.right == null) {
                    ans.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次被访问到
                    pre.right = null;
                    cur = cur.right;
                }
            } else {
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        TreeNode t2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode t4 = new TreeNode(4, t2, new TreeNode(5));
        TreeNode t7 = new TreeNode(7, null, new TreeNode(8));
        TreeNode t9 = new TreeNode(9, t7, new TreeNode(10));
        TreeNode root = new TreeNode(6, t4, t9);

        travel(root);
    }

    private static void travel(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    // System.out.println(cur.val); // Pre Order
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    // System.out.println(cur.val); // In Order
                    cur = cur.right;
                }
            } else {
                System.out.println(cur.val); // always
                cur = cur.right;
            }
        }
    }

}
