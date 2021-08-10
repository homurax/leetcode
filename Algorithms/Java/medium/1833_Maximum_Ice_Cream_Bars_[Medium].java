/**
 * 1833. Maximum Ice Cream Bars
 *
 *
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
 *
 * At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible.
 *
 * Return the maximum number of ice cream bars the boy can buy with coins coins.
 *
 * Note: The boy can buy the ice cream bars in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [1,3,2,4,1], coins = 7
 * Output: 4
 * Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
 *
 * Example 2:
 *
 * Input: costs = [10,6,8,7,7,8], coins = 5
 * Output: 0
 * Explanation: The boy cannot afford any of the ice cream bars.
 *
 * Example 3:
 *
 * Input: costs = [1,6,3,1,2,5], coins = 20
 * Output: 6
 * Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
 *
 *
 * Constraints:
 *
 * 1. costs.length == n
 * 2. 1 <= n <= 10^5
 * 3. 1 <= costs[i] <= 10^5
 * 4. 1 <= coins <= 10^8
 */
public class MaximumIceCreamBars {

    public int maxIceCream(int[] costs, int coins) {
        int count = 0;
        Arrays.sort(costs);
        for (int cost : costs) {
            if (cost > coins) {
                return count;
            } else {
                coins -= cost;
                count++;
            }
        }
        return count;
    }

    public int maxIceCream2(int[] costs, int coins) {
        int[] frequency = new int[100001];
        for (int cost : costs) {
            frequency[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            if (coins >= i) {
                int num = Math.min(frequency[i], coins / i);
                count += num;
                coins -= i * num;
            } else {
                return count;
            }
        }
        return count;
    }

}
