/**
 * 94. Binary Tree Inorder Traversal
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        travel(root, ans);
        return ans;
    }

    private void travel(TreeNode node, List<Integer> ans) {
        if (node == null) return;
        if (node.left != null) travel(node.left, ans);
        ans.add(node.val);
        if (node.right != null) travel(node.right, ans);
    }



    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !deque.isEmpty()) {
            while (curr != null) {
                deque.push(curr);
                curr = curr.left;
            }
            curr = deque.pop();
            ans.add(curr.val);
            curr = curr.right;
        }
        return ans;
    }



    /**
     * Morris Traversal
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;
            }
        }
        return ans;
    }

}
