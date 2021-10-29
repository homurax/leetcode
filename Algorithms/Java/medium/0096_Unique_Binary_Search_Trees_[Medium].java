/**
 * 96. Unique Binary Search Trees
 *
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 19
 */
public class UniqueBinarySearchTrees {


    /**
     *              i
     * [1, i - 1]      [i + 1, n]
     * (len = i - 1)   (len = n - i)
     *
     * G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数
     * F(i,n): 以 i 为根、序列长度为 n 的不同二叉搜索树个数
     *
     * G(n) = sum(F(i, n)), i in [1, n]
     * F(i, n) = G(i - 1) * G(n - i)
     * G(n) = sum(G(i - 1) * G(n - i)), i in [1, n]
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= len; i++) {
                dp[len] += dp[i - 1] * dp[len - i];
            }
        }
        return dp[n];
    }

}
