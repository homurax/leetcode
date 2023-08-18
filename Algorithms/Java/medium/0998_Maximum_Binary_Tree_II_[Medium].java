/**
 * 998. Maximum Binary Tree II
 *
 *
 * A maximum tree is a tree where every node has a value greater than any other value in its subtree.
 *
 * You are given the root of a maximum binary tree and an integer val.
 *
 * Just as in the previous problem, the given tree was constructed from a list a (root = Construct(a)) recursively with the following Construct(a) routine:
 *
 * If a is empty, return null.
 * Otherwise, let a[i] be the largest element of a. Create a root node with the value a[i].
 * The left child of root will be Construct([a[0], a[1], ..., a[i - 1]]).
 * The right child of root will be Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]).
 * Return root.
 * Note that we were not given a directly, only a root node root = Construct(a).
 *
 * Suppose b is a copy of a with the value val appended to it. It is guaranteed that b has unique values.
 *
 * Return Construct(b).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,1,3,null,null,2], val = 5
 * Output: [5,4,null,1,3,null,null,2]
 * Explanation: a = [1,4,2,3], b = [1,4,2,3,5]
 *
 *
 * Example 2:
 *
 *
 * Input: root = [5,2,4,null,1], val = 3
 * Output: [5,2,4,null,1,null,3]
 * Explanation: a = [2,1,5,4], b = [2,1,5,4,3]
 *
 *
 * Example 3:
 *
 *
 * Input: root = [5,2,3,null,1], val = 4
 * Output: [5,2,4,null,1,3]
 * Explanation: a = [2,1,5,3], b = [2,1,5,3,4]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * 1 <= Node.val <= 100
 * All the values of the tree are unique.
 * 1 <= val <= 100
 */
public class MaximumBinaryTreeII {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // 题意相对不易理解
    // root 代表的是一个 nums 基于规则构建的树, 题意为将 val 添加到 nums 尾部, 用相同规则重新构建树
    // 构造规则有若 i < j, 则 nums[i] 必然在 nums[j] 水平线左边
    // 如果 val 为 nums 中最大值, 因为 val 追加在 nums 尾部, 因此将 root 作为左字树挂在 val 节点下即可, val 节点为新 root
    // 否则需要在 root 的右子树中找到目标位置, 因为 val 追加在 nums 尾部, 不会有节点出现在 val 的右边
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (val > root.val) {
            return new TreeNode(val, root, null);
        }
        TreeNode prev = null, cur = root;
        while (cur != null && cur.val > val) {
            prev = cur;
            cur = cur.right;
        }
        prev.right = new TreeNode(val);
        prev.right.left = cur;
        return root;
    }

    public TreeNode insertIntoMaxTree1(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode prev = null, cur = root;
        while (cur != null && cur.val > val) {
            prev = cur;
            cur = cur.right;
        }
        if (prev == null) {
            node.left = cur;
            return node;
        } else {
            prev.right = node;
            node.left = cur;
            return root;
        }
    }

}
