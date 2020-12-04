/**
 * 113. Path Sum II
 *
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumII {

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


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, sum, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode node, int target, List<List<Integer>> ans, List<Integer> path) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == node.right && node.val == target) {
            ans.add(new ArrayList<>(path));
        } else {
            dfs(node.left, target - node.val, ans, path);
            dfs(node.right, target - node.val, ans, path);
        }
        path.remove(path.size() - 1);
    }

}
