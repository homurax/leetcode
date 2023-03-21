/**
 * 464. Can I Win
 *
 *
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 *
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false. Assume both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 * Output: false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 *
 *
 * Example 2:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 0
 * Output: true
 *
 *
 * Example 3:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 1. 1 <= maxChoosableInteger <= 20
 * 2. 0 <= desiredTotal <= 300
 */
public class CanIWin {


    private int n, t;
    private int[] f = new int[1 << 20];

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        this.n = maxChoosableInteger;
        this.t = desiredTotal;
        if (n * (n + 1) / 2 < t) {
            return false;
        }
        if (t == 0) {
            return true;
        }
        return dfs(0, 0) == 1;
    }

    // 状态优化
    // 当前选手能否获胜
    private int dfs(int state, int total) {
        if (f[state] != 0) {
            return f[state];
        }
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1) {
                continue;
            }
            if (total + i + 1 >= t) {
                return f[state] = 1;
            }
            if (dfs(state | (1 << i), total + i + 1) == -1) {
                return f[state] = 1;
            }
        }
        return f[state] = -1;
    }



    // TLE
    private int[][] dp = new int[1 << 20][2];

    private int dfs(int state, int total, int k) {
        // 选择了所有数仍然和小于 desiredTotal
        if (state == ((1 << n) - 1) && total < t) {
            return -1;
        }
        // 通过回合数确定先手输赢
        int rst = k % 2 == 0 ? 1 : -1;
        for (int i = 0; i < n; i++) {
            // 当前数已经选择
            if (((state >> i) & 1) == 1) {
                continue;
            }
            // 当前回合已经满足
            if (total + i + 1 >= t) {
                return dp[state][k % 2] = rst;
            }
            // 之后可以满足
            if (dfs(state | (1 << i), total + i + 1, k + 1) == rst) {
                return dp[state][k % 2] = rst;
            }
        }
        // 无法满足
        return dp[state][k % 2] = -rst;
    }


}
