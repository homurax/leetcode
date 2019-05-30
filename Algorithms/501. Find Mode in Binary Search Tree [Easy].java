/**
 * 501. Find Mode in Binary Search Tree
 *
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * For example:
 * Given BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 *
 *
 * return [2].
 *
 * Note: If a tree has more than one mode, you can return them in any order.
 *
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class FindModeInBinarySearchTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 获取最大值、元素、转数组 繁琐
     */
    public int[] findMode(TreeNode root) {

        if (root == null) return new int[]{};

        Map<Integer, Integer> map = new HashMap<>();
        travel(root, map);
        int max = Integer.MIN_VALUE;
        for (Integer value : map.values()) {
            max = Math.max(max, value);
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max)
                list.add(entry.getKey());
        }
        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void travel(TreeNode node, Map<Integer, Integer> map) {

        if (node == null) return;

        travel(node.left, map);
        int val = node.val;
        map.put(val, map.getOrDefault(val, 0) + 1);
        travel(node.right, map);
    }


    /**
     * 在递归中直接得到集合
     */
    private int max = 1;
    private int curr = 0;
    private List<Integer> list = new ArrayList<>();
    private TreeNode prev = null;
    public int[] findMode2(TreeNode root) {

        travel(root);
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void travel(TreeNode node) {

        if (node == null) return;

        travel(node.left);

        if (prev != null && prev.val == node.val) {
            curr++;
        } else {
            curr = 1;
        }
        // check
        if (max == curr) {
            list.add(node.val);
        } else if (max < curr) {
            max = curr;
            list.clear();
            list.add(node.val);
        }

        prev = node;

        travel(node.right);
    }


}
