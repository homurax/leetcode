/**
 * 988. Smallest String Starting From Leaf
 *
 *
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 *
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [0,1,2,3,4,3,4]
 * Output: "dba"
 *
 * Example 2:
 *
 *
 *
 * Input: [25,1,3,1,3,0,2]
 * Output: "adz"
 *
 * Example 3:
 *
 *
 *
 * Input: [2,2,1,null,1,0,null,0]
 * Output: "abc"
 *
 *
 * Note:
 *
 * 1. The number of nodes in the given tree will be between 1 and 8500.
 * 2. Each node in the tree will have a value between 0 and 25.
 */
public class SmallestStringStartingFromLeaf {

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

    private String ans = "~";

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    // 叶子节点为进行比较字典序的字符串的首字母 免不了遍历一遍
    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append((char) ('a' + node.val));
        if (node.left == node.right) {
            sb.reverse();
            String temp = sb.toString();
            sb.reverse();
            if (temp.compareTo(ans) < 0) {
                ans = temp;
            }
        }
        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

}
