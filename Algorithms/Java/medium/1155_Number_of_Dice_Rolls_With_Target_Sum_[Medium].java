/**
 * 1155. Number of Dice Rolls With Target Sum
 *
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 *
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
 *
 *
 *
 * Example 1:
 *
 * Input: d = 1, f = 6, target = 3
 * Output: 1
 * Explanation:
 * You throw one die with 6 faces.  There is only one way to get a sum of 3.
 *
 * Example 2:
 *
 * Input: d = 2, f = 6, target = 7
 * Output: 6
 * Explanation:
 * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 *
 * Example 3:
 *
 * Input: d = 2, f = 5, target = 10
 * Output: 1
 * Explanation:
 * You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
 * Example 4:
 *
 * Input: d = 1, f = 2, target = 3
 * Output: 0
 * Explanation:
 * You throw one die with 2 faces.  There is no way to get a sum of 3.
 *
 * Example 5:
 *
 * Input: d = 30, f = 30, target = 500
 * Output: 222616187
 * Explanation:
 * The answer must be returned modulo 10^9 + 7.
 *
 *
 * Constraints:
 *
 * 1. 1 <= d, f <= 30
 * 2. 1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {

    /**
     * bracktrack 的复杂度过高 O(f^d)
     *
     * 定义 dp[i][j] -> i 个骰子和为 j 的方法数
     * ->  dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] + ... + dp[i-1][j-f]
     *
     * i 只依赖 i - 1 空间可以压缩
     */
    public int numRollsToTarget(int d, int f, int target) {
        if (d > target || d * f < target) {
            return 0;
        }
        int mod = 0x3B9ACA07;
        int[][] dp = new int[2][1001];
        for (int i = 1; i <= Math.min(f, target); i++) {
            dp[0][i] = 1;
        }
        int cur = 0;
        for (int i = 2; i <= d; i++) {
            int pre = cur;
            cur = 1 - cur;
            Arrays.fill(dp[cur], 0);
            for (int j = i; j <= Math.min(target, i * f); j++) {
                for (int k = 1; j - k >= 0 && k <= f; k++) {
                    dp[cur][j] = (dp[cur][j] + dp[pre][j - k]) % mod;
                }
            }
        }
        return dp[cur][target];
    }

}
