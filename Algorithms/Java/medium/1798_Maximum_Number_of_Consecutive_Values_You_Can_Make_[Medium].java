/**
 * 1798. Maximum Number of Consecutive Values You Can Make
 *
 * You are given an integer array coins of length n which represents the n coins that you own. The value of the ith coin is coins[i]. You can make some value x if you can choose some of your n coins such that their values sum up to x.
 *
 * Return the maximum number of consecutive integer values that you can make with your coins starting from and including 0.
 *
 * Note that you may have multiple coins of the same value.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,3]
 * Output: 2
 * Explanation: You can make the following values:
 * - 0: take []
 * - 1: take [1]
 * You can make 2 consecutive integer values starting from 0.
 *
 * Example 2:
 *
 * Input: coins = [1,1,1,4]
 * Output: 8
 * Explanation: You can make the following values:
 * - 0: take []
 * - 1: take [1]
 * - 2: take [1,1]
 * - 3: take [1,1,1]
 * - 4: take [4]
 * - 5: take [4,1]
 * - 6: take [4,1,1]
 * - 7: take [4,1,1,1]
 * You can make 8 consecutive integer values starting from 0.
 *
 * Example 3:
 *
 * Input: nums = [1,4,10,3,1]
 * Output: 20
 *
 *
 * Constraints:
 *
 * 1. coins.length == n
 * 2. 1 <= n <= 4 * 10^4
 * 3. 1 <= coins[i] <= 4 * 10^4
 */
public class MaximumNumberOfConsecutiveValuesYouCanMake {

    /**
     * 贪心
     *
     * 假设当前 coins 可以构成 [0, x] 范围连续整数
     * 此时增加一个 y，则一定可以构成 [0, x]、[y, y + x] 两段连续整数
     * 若希望两段可以合并为 [0, y + x]，则需要 x + 1 >= y
     */
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int x = 0;
        for (int y : coins) {
            if (y > x + 1) {
                break;
            }
            x += y;
        }
        return x + 1;
    }

}
