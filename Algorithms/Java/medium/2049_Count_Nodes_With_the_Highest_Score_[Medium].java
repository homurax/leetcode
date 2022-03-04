/**
 * 2049. Count Nodes With the Highest Score
 *
 *
 * There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.
 *
 * Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.
 *
 * Return the number of nodes that have the highest score.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: parents = [-1,2,0,2,0]
 * Output: 3
 * Explanation:
 * - The score of node 0 is: 3 * 1 = 3
 * - The score of node 1 is: 4 = 4
 * - The score of node 2 is: 1 * 1 * 2 = 2
 * - The score of node 3 is: 4 = 4
 * - The score of node 4 is: 4 = 4
 * The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
 *
 * Example 2:
 *
 * example-2
 * Input: parents = [-1,2,0]
 * Output: 2
 * Explanation:
 * - The score of node 0 is: 2 = 2
 * - The score of node 1 is: 2 = 2
 * - The score of node 2 is: 1 * 1 = 1
 * The highest score is 2, and two nodes (node 0 and node 1) have the highest score.
 *
 *
 * Constraints:
 *
 * 1. n == parents.length
 * 2. 2 <= n <= 10^5
 * 3. parents[0] == -1
 * 4. 0 <= parents[i] <= n - 1 for i != 0
 * 5. parents represents a valid binary tree.
 */
public class CountNodesWithTheHighestScore {

    // 遍历中计算分数
    private int[] L, R;
    private long maxPoint;
    private int maxCount, n;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        L = new int[n];
        R = new int[n];
        for (int i = 0; i < n; i++) {
            L[i] = R[i] = -1;
        }
        for (int node = 1; node < n; node++) {
            if (L[parents[node]] == -1) {
                L[parents[node]] = node;
            } else {
                R[parents[node]] = node;
            }
        }
        dp(0);
        return maxCount;
    }

    private int dp(int node) {
        if (node == -1) {
            return 0;
        }
        int l = dp(L[node]), r = dp(R[node]);
        long point = (long) Math.max(l, 1) * Math.max(r, 1) * Math.max(n - l - r - 1, 1);
        if (maxPoint < point) {
            maxPoint = point;
            maxCount = 1;
        } else if (maxPoint == point) {
            maxCount++;
        }
        return l + r + 1;
    }

}
