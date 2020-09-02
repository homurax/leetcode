/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 *
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 *
 * Example 2:
 *
 *
 *
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 *
 * Input: root = [9]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The given binary tree will have between 1 and 10^5 nodes.
 * Node values are digits from 1 to 9.
 */
public class PseudoPalindromicPathsInABinaryTree {

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

    private int ans = 0;

    public int pseudoPalindromicPaths (TreeNode root) {
        dfs(root, new int[10]);
        return ans;
    }

    private void dfs(TreeNode node, int[] count) {
        count[node.val]++;
        if (node.left == null && node.right == null) {
            int oddCount = 0;
            for (int frequency : count) {
                if ((frequency & 1) == 1) {
                    oddCount++;
                }
            }
            if (oddCount <= 1) {
                ans++;
            }
        }
        if (node.left != null) {
            dfs(node.left, count);
        }
        if (node.right != null) {
            dfs(node.right, count);
        }
        count[node.val]--;
    }


    //
    public int pseudoPalindromicPaths2(TreeNode root) {
        return dfs2(root, 0);
    }

    private int dfs2(TreeNode root, int count) {
        if (root == null) {
            return 0;
        }
        count ^= 1 << (root.val - 1);
        int ans = dfs2(root.left, count) + dfs2(root.right, count);
        if (root.left == root.right && (count & (count - 1)) == 0) {
            ans++;
        }
        return ans;
    }

}
