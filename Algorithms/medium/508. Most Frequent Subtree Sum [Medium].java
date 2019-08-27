/**
 * 508. Most Frequent Subtree Sum
 *
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 *
 * Examples 2
 * Input:
 *
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 *
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Map<Integer, Integer> map;
    private List<Integer> list;
    private int max = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        dfs(root);
        int[] ans = new int[this.list.size()];
        for (int i = 0; i < this.list.size(); i++) {
            ans[i] = this.list.get(i);
        }
        return ans;
    }

    private int dfs(TreeNode currNode) {

        if (currNode == null)
            return 0;
        int currSum = dfs(currNode.left) + dfs(currNode.right) + currNode.val;
        int count = this.map.getOrDefault(currSum, 0);
        this.map.put(currSum, count + 1);

        if (this.max < count) {
            this.max = count;
            this.list = new ArrayList<>();
            this.list.add(currSum);
        } else if (this.max == count) {
            this.list.add(currSum);
        }
        return currSum;
    }


}
