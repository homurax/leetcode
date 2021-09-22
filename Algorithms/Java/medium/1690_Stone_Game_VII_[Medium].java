/**
 * 1690. Stone Game VII
 *
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones arranged in a row. On each player's turn, they can remove either the leftmost stone or the rightmost stone from the row and receive points equal to the sum of the remaining stones' values in the row. The winner is the one with the higher score when there are no stones left to remove.
 *
 * Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference. Alice's goal is to maximize the difference in the score.
 *
 * Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return the difference in Alice and Bob's score if they both play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [5,3,1,4,2]
 * Output: 6
 * Explanation:
 * - Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
 * - Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
 * - Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
 * - Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
 * - Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
 * The score difference is 18 - 12 = 6.
 *
 * Example 2:
 *
 * Input: stones = [7,90,5,1,100,10,10,2]
 * Output: 122
 *
 *
 * Constraints:
 *
 * 1. n == stones.length
 * 2. 2 <= n <= 1000
 * 3. 1 <= stones[i] <= 1000
 */
public class StoneGameVII {

    /**
     * i == j      ->    dp[i][j] = 0   // 移除后没有石头了
     * j - i == 1  ->    dp[i][j] = max(stones[i], stones[j])   // 移除后只剩一个 移除最小的
     * j - i > 1   ->    dp[i][j] = max(sum[i + 1][j] - dp[i + 1][j], sum[i][j - 1] - dp[i][j - 1]) // 比较移除左边和移除右边
     *
     */
    public int stoneGameVII1(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n];
        sum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + stones[i];
        }
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(sum[j] - sum[i + 1] + stones[i + 1] - dp[i + 1][j], sum[j - 1] - sum[i] + stones[i] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int sum = stones[i];
            for (int j = i + 1; j < n; j++) {
                sum += stones[j];
                dp[j] = Math.max(sum - stones[i] - dp[j], sum - stones[j] - dp[j - 1]);
            }
        }
        return dp[n - 1];
    }

}
