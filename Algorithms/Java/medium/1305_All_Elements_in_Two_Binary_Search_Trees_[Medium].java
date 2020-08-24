/**
 * 1305. All Elements in Two Binary Search Trees
 *
 * Given two binary search trees root1 and root2.
 *
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 *
 * Example 2:
 *
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 *
 * Example 3:
 *
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 *
 * Example 4:
 *
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 *
 * Example 5:
 *
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 *
 * Constraints:
 *
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 */
public class AllElementsInTwoBinarySearchTrees {

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

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        List<Integer> ans = new ArrayList<>(list1.size() + list2.size());
        int i = 0, j = 0;
        while (i < list1.size() || j < list2.size()) {
            if (i < list1.size() && (j == list2.size() || list1.get(i) <= list2.get(j))) {
                ans.add(list1.get(i++));
            } else {
                ans.add(list2.get(j++));
            }
        }
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }


    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> tree = new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        travel(root1, tree);
        merge(root2, ans, tree);
        ans.addAll(tree);
        return ans;
    }

    private void travel(TreeNode node, LinkedList<Integer> list) {
        if (node == null) return;
        travel(node.left, list);
        list.add(node.val);
        travel(node.right, list);
    }

    private void merge(TreeNode node, LinkedList<Integer> ans, LinkedList<Integer> tree) {
        if (node == null) return;
        merge(node.left, ans, tree);
        while (!tree.isEmpty() && tree.peek() < node.val) {
            ans.add(tree.poll());
        }
        ans.add(node.val);
        merge(node.right, ans, tree);
    }

}
