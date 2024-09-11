/**
 * 2476. Closest Nodes Queries in a Binary Search Tree
 *
 *
 * You are given the root of a binary search tree and an array queries of size n consisting of positive integers.
 *
 * Find a 2D array answer of size n where answer[i] = [mini, maxi]:
 *
 * mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
 * maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
 * Return the array answer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * Output: [[2,2],[4,6],[15,-1]]
 * Explanation: We answer the queries in the following way:
 * - The largest number that is smaller or equal than 2 in the tree is 2, and the smallest number that is greater or equal than 2 is still 2. So the answer for the first query is [2,2].
 * - The largest number that is smaller or equal than 5 in the tree is 4, and the smallest number that is greater or equal than 5 is 6. So the answer for the second query is [4,6].
 * - The largest number that is smaller or equal than 16 in the tree is 15, and the smallest number that is greater or equal than 16 does not exist. So the answer for the third query is [15,-1].
 *
 *
 * Example 2:
 *
 *
 * Input: root = [4,null,9], queries = [3]
 * Output: [[-1,4]]
 * Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and the smallest number that is greater or equal to 3 is 4. So the answer for the query is [-1,4].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 10^5].
 * 1 <= Node.val <= 10^6
 * n == queries.length
 * 1 <= n <= 10^5
 * 1 <= queries[i] <= 10^6
 */
public class ClosestNodesQueriesInABinarySearchTree {

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

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<>();
        dfs(root, arr);

        int n = arr.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = arr.get(i);
        }

        List<List<Integer>> ans = new ArrayList<>(queries.size());
        for (int q : queries) {
            int j = lowerBound(a, q);
            int max = j == n ? -1 : a[j];
            if (j == n || a[j] != q) {
                j--;
            }
            int min = j < 0 ? -1 : a[j];
            ans.add(List.of(min, max));
        }
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> a) {
        if (node == null) {
            return;
        }
        dfs(node.left, a);
        a.add(node.val);
        dfs(node.right, a);
    }

    private int lowerBound(int[] a, int target) {
        int left = -1, right = a.length; // 开区间 (left, right)
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (a[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
