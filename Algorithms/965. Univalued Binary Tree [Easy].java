/**
 * 965. Univalued Binary Tree
 *
 * A binary tree is univalued if every node in the tree has the same value.
 *
 * Return true if and only if the given tree is univalued.
 *
 * Example 1:
 *
 * Input: [1,1,1,1,1,null,1]
 * Output: true
 *
 * Example 2:
 *
 * Input: [2,2,2,5,2]
 * Output: false
 *
 * Note:
 *
 * The number of nodes in the given tree will be in the range [1, 100].
 * Each node's value will be an integer in the range [0, 99].
 */
public class UnivaluedBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 递归
     */
    public boolean isUnivalTree(TreeNode root) {

        int val = root.val;
        TreeNode left = root.left;
        TreeNode right = root.right;

        boolean leftVal = left == null || (val == left.val && isUnivalTree(left));
        boolean rightVal = right == null || (val == right.val && isUnivalTree(right));

        return leftVal && rightVal;
    }

    /**
     * 深度优先
     */
    public boolean isUnivalTree2(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        addVal(root, set);
        if (set.size() > 1)
            return false;
        return true;
    }

    public void addVal(TreeNode node, Set<Integer> set) {
        if (node != null) {
            set.add(node.val);
            addVal(node.left, set);
            addVal(node.right, set);
        }
    }

    /**
     * 迭代
     */
    public boolean isUnivalTree3(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            set.add(node.val);
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }

        if (set.size() > 1)
            return false;
        return true;
    }
}
