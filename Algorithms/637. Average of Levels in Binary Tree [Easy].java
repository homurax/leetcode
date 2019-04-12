/**
 * 637. Average of Levels in Binary Tree
 *
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 */
public class AverageOfLevelsInBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }


    /**
     * 迭代 层序遍历 BFS
     */
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> ans = new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            double sum = 0;
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = nodes.poll();
                sum += curr.val;
                if (curr.left != null) {
                    nodes.add(curr.left);
                }
                if (curr.right != null) {
                    nodes.add(curr.right);
                }
            }
            ans.add(sum/size);
        }

        return ans;
    }

    /**
     * 递归 DFS
     */
    public List<Double> averageOfLevels2(TreeNode root) {

        List<Double> ans = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        average(root, 0, ans, count);

        for (int i = 0; i < ans.size(); i++) {
            ans.set(i, ans.get(i) / count.get(i));
        }

        return ans;
    }

    private void average(TreeNode treeNode, int i, List<Double> sum, List<Integer> count) {

        if (treeNode == null) {
            return;
        }
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + treeNode.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * treeNode.val);
            count.add(1);
        }

        average(treeNode.left, i + 1, sum, count);
        average(treeNode.right, i + 1, sum, count);
    }
}
