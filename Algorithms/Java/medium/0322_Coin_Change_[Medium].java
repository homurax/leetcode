/**
 * 322. Coin Change
 *
 *
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Example 4:
 *
 * Input: coins = [1], amount = 1
 * Output: 1
 *
 * Example 5:
 *
 * Input: coins = [1], amount = 2
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {

    /**
     * 直接贪心优先复用最大值是错误的 需要剪枝
     *      coins = [1, 4, 5], amount = 28
     *      8: 5 * 5 + 1 * 3
     *      6: 5 * 4 + 4 * 2
     */
    /*public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        for (int index = coins.length - 1; index >= 0; index--) {
            if (amount >= coins[index]) {
                int ans = test(coins, amount, index, 0);
                if (ans > 0) {
                    return ans;
                }
            }
        }
        return -1;
    }

    private static int test(int[] coins, int target, int index, int count) {
        if (index < 0) {
            return -1;
        }
        if (target == 0) {
            return count;
        }
        if (target - coins[index] >= 0) {
            return test(coins, target - coins[index], index, ++count);
        } else {
            return test(coins, target, --index, count);
        }
    }*/


    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, amount + 1);
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, amount + 1);
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
