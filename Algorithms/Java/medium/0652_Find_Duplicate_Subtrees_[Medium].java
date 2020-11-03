/**
 * 652. Find Duplicate Subtrees
 *
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 *
 * Example 2:
 *
 *
 * Input: root = [2,1,1]
 * Output: [[1]]
 *
 * Example 3:
 *
 *
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
 */
public class FindDuplicateSubtrees {

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


    private final Map<String, Integer> count = new HashMap<>();
    private final List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        collect(root);
        return ans;
    }

    public String collect(TreeNode node) {
        if (node == null) return "#";
        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
        if (count.merge(serial, 1, Integer::sum) == 2) {
            ans.add(node);
        }
        return serial;
    }


    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        lookup(root, new HashMap<>(), ans);
        return ans;
    }

    private long lookup(TreeNode node, Map<Long, Integer> map, List<TreeNode> ans) {
        if (node == null) {
            return 31;
        }
        long val = node.val + 5381;
        long left = lookup(node.left, map, ans);
        long right = lookup(node.right, map, ans);
        long hash = val + val * left + val * left * right;
        if (map.merge(hash, 1, Integer::sum) == 2) {
            ans.add(node);
        }
        return hash;
    }

}
