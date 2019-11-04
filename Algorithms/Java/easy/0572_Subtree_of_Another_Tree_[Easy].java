/**
 * 572. Subtree of Another Tree
 *
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 */
public class SubtreeOfAnotherTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 层序遍历找可以开始比较的目标节点
     * 找到后进行比较
     * 需要优化 存在重复遍历的部分
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (s == null) return false;

        if (s.val == t.val && isSame(s, t)) return true;

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    private boolean isSame(TreeNode a, TreeNode b) {

        if (a == null && b == null) return true;

        if (a == null || b == null) return false;

        return a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right);
    }


    /**
     * 找到比较的目标节点后层级比较 否则比较子节点
     */
    public boolean isSubtree2(TreeNode s, TreeNode t) {

        return travel(s, t, true);
    }

    private boolean travel(TreeNode s, TreeNode t, boolean isRoot) {

        if (s == null || t == null) return s == t;

        if (s.val != t.val) {

            return isRoot && travel(s.left, t, true) || travel(s.right, t, true);
        } else {

            return travel(s.left, t.left, false) && travel(s.right, t.right, false)
                    || travel(s.left, t, true)
                    || travel(s.right, t, true);
        }
    }

}
