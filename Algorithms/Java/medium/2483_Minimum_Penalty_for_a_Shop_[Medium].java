/**
 * 2483. Minimum Penalty for a Shop
 *
 *
 * You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':
 *
 * if the ith character is 'Y', it means that customers come at the ith hour
 * whereas 'N' indicates that no customers come at the ith hour.
 * If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:
 *
 * For every hour when the shop is open and no customers come, the penalty increases by 1.
 * For every hour when the shop is closed and customers come, the penalty increases by 1.
 * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
 *
 * Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = "YYNY"
 * Output: 2
 * Explanation:
 * - Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
 * - Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
 * - Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
 * - Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
 * - Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
 * Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.
 *
 *
 * Example 2:
 *
 * Input: customers = "NNNNN"
 * Output: 0
 * Explanation: It is best to close the shop at the 0th hour as no customers arrive.
 *
 *
 * Example 3:
 *
 * Input: customers = "YYYY"
 * Output: 4
 * Explanation: It is best to close the shop at the 4th hour as customers arrive at each hour.
 *
 *
 * Constraints:
 *
 * 1 <= customers.length <= 10^5
 * customers consists only of characters 'Y' and 'N'.
 */
public class MinimumPenaltyForAShop {


    public int bestClosingTime(String customers) {
        // 0 时刻代价
        int cost = 0;
        char[] log = customers.toCharArray();
        for (char c : log) {
            if (c == 'Y') {
                cost++;
            }
        }
        int ans = 0;
        int minCost = cost;
        // 计算 [1, n] 时刻
        for (int i = 0; i < log.length; i++) {
            if (log[i] == 'N') {
                cost++;
            } else {
                cost--;
                if (cost < minCost) {
                    cost = minCost;
                    ans = i + 1;
                }
            }
        }
        return ans;
    }


    // no[i] = [0, i) 中 N 的数量
    // yes[i] = [i, n) 中 Y 的数量
    // ans = min(no[i] + yes[i]), min(i)
    public int bestClosingTime1(String customers) {
        char[] log = customers.toCharArray();
        int n = log.length;
        int[] no = new int[n + 1];
        int[] yes = new int[n + 1];
        for (int i = 0; i < n; i++) {
            no[i + 1] = no[i];
            if (log[i] == 'N') {
                no[i + 1]++;
            }
            int j = n - i - 1;
            yes[j] = yes[j + 1];
            if (log[j] == 'Y') {
                yes[j]++;
            }
        }
        int ans = 0, cost = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++) {
            if (cost > no[i] + yes[i]) {
                cost = no[i] + yes[i];
                ans = i;
            }
        }
        return ans;
    }

}
