/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 *
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeToBuyAndSellStockWithCooldown {


    /**
     * dp[i][0], (第 i 日持有股票)
     * dp[i][1], (第 i 日不持股票 i+1 为冷冻期)
     * dp[i][2], (第 i 日不持股票 i+1 不处于冷冻期)
     *
     * dp[i][0] = max(dp[i-1][0], dp[i-1][2] - prices[i])
     * dp[i][1] = dp[i-1][0] + prices[i]
     * dp[i][2] = max(dp[i-1][1], dp[i-1][2])
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    // 只与前一日状态有关 可以优化空间
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] dp = {-prices[0], 0, 0};
        for (int i = 1; i < prices.length; ++i) {
            int dp0 = Math.max(dp[0], dp[2] - prices[i]);
            int dp1 = dp[0] + prices[i];
            int dp2 = Math.max(dp[1], dp[2]);
            dp[0] = dp0;
            dp[1] = dp1;
            dp[2] = dp2;
        }
        return Math.max(dp[1], dp[2]);
    }
}
