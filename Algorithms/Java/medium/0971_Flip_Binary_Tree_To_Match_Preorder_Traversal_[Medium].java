/**
 * 971. Flip Binary Tree To Match Preorder Traversal
 *
 *
 * You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n. You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
 *
 * Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:
 *
 *
 * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
 *
 * Return a list of the values of all flipped nodes. You may return the answer in any order. If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2], voyage = [2,1]
 * Output: [-1]
 * Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.
 *
 * Example 2:
 *
 *
 * Input: root = [1,2,3], voyage = [1,3,2]
 * Output: [1]
 * Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.
 *
 * Example 3:
 *
 *
 * Input: root = [1,2,3], voyage = [1,2,3]
 * Output: []
 * Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the tree is n.
 * 2. n == voyage.length
 * 3. 1 <= n <= 100
 * 4. 1 <= Node.val, voyage[i] <= n
 * 5. All the values in the tree are unique.
 * 6. All the values in voyage are unique.
 */
public class FlipBinaryTreeToMatchPreorderTraversal {

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

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> ans = new ArrayList<>();
        if (dfs(root, voyage, ans, 0) == -1) {
            ans.clear();
            ans.add(-1);
        }
        return ans;
    }

    private int dfs(TreeNode cur, int[] voyage, List<Integer> ans, int index) {
        if (cur == null) {
            return index;
        }
        if (cur.val != voyage[index]) {
            return -1;
        }
        int left = dfs(cur.left, voyage, ans, index + 1);
        if (left != -1) {
            return dfs(cur.right, voyage, ans, left);
        }
        ans.add(cur.val);
        int right = dfs(cur.right, voyage, ans, index + 1);
        if (right != -1) {
            return dfs(cur.left, voyage, ans, right);
        }
        return -1;
    }



    //
    private int index;

    public List<Integer> flipMatchVoyage1(TreeNode root, int[] voyage) {
        this.index = 0;
        List<Integer> ans = new ArrayList<>();
        dfs(root, voyage, ans);
        if (!ans.isEmpty() && ans.get(0) == -1) {
            ans.clear();
            ans.add(-1);
        }
        return ans;
    }

    private void dfs(TreeNode cur, int[] voyage, List<Integer> ans) {
        if (cur == null) {
            return;
        }
        if (cur.val != voyage[index++]) {
            ans.clear();
            ans.add(-1);
            return;
        }
        if (cur.left != null && cur.left.val != voyage[index]) {
            ans.add(cur.val);
            dfs(cur.right, voyage, ans);
            dfs(cur.left, voyage, ans);
        } else {
            dfs(cur.left, voyage, ans);
            dfs(cur.right, voyage, ans);
        }
    }

}
