/**
 * 877. Stone Game
 *
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 *
 * Example 1:
 *
 * Input: [5,3,4,5]
 * Output: true
 *
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 *
 *
 * Note:
 *
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 */
public class StoneGame {

    /**
     * 偶数堆 奇数总数
     * 两堆时 先手必胜
     * 四堆时 必定可以获取1/3堆或2/4堆 两种中至少一组更大
     * 扩展到N堆同理 先手必胜
     */
    public boolean stoneGame(int[] piles) {

        int len = piles.length;
        int[][] dp = new int[len + 2][len + 2];
        for (int size = 1; size <= len; size++)
            for (int i = 0; i + size <= len; i++) {
                int j = i + size - 1;
                int parity = (j + i + len) % 2;
                if (parity == 1)
                    dp[i + 1][j + 1] = Math.max(piles[i] + dp[i + 2][j + 1], piles[j] + dp[i + 1][j]);
                else
                    dp[i + 1][j + 1] = Math.min(-piles[i] + dp[i + 2][j + 1], -piles[j] + dp[i + 1][j]);
            }

        return dp[1][len] > 0;
    }
}
