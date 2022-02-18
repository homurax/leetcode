/**
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
 *
 *
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 *
 * Example 2:
 *
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the tree is n.
 * 2. 2 <= n <= 10^5
 * 3. 1 <= Node.val <= n
 * 4. All the values in the tree are unique.
 * 5. 1 <= startValue, destValue <= n
 * 6. startValue != destValue
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {

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

    /**
     * 根节点向下到 s 的路径组成的字符串为 path1
     * 根节点向下到 t 的路径组成的字符串为 path2
     * path1 与 path2 的最长公共前缀即为根节点到 s 与 t 最近公共祖先节点的路径对应的方向字符串
     * 在 path1 与 path2 中截去这部分前缀
     * 将处理后的 path1 中的所有字符均修改成代表向上的 U，并将处理后的 path2 拼接至尾部
     */
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder path1 = dfs(root, startValue, new StringBuilder());
        StringBuilder path2 = dfs(root, destValue, new StringBuilder());
        int idx = 0;
        while (idx < path1.length() && idx < path2.length() && path1.charAt(idx) == path2.charAt(idx)) {
            idx++;
        }
        StringBuilder rst = new StringBuilder();
        for (int i = 0; i < path1.length() - idx; i++) {
            rst.append("U");
        }
        rst.append(path2.substring(idx));
        return rst.toString();
    }

    private StringBuilder dfs(TreeNode node, int target, StringBuilder sb) {
        if (node.val == target) {
            return sb;
        }
        if (node.left != null) {
            sb.append("L");
            StringBuilder path = dfs(node.left, target, sb);
            if (path != null) {
                return path;
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (node.right != null) {
            sb.append("R");
            StringBuilder path = dfs(node.right, target, sb);
            if (path != null) {
                return path;
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return null;
    }

}
