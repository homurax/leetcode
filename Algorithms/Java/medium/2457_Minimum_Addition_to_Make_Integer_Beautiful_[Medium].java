/**
 * 2457. Minimum Addition to Make Integer Beautiful
 *
 *
 * You are given two positive integers n and target.
 *
 * An integer is considered beautiful if the sum of its digits is less than or equal to target.
 *
 * Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it is always possible to make n beautiful.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 16, target = 6
 * Output: 4
 * Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can not make n beautiful with adding non-negative integer less than 4.
 *
 * Example 2:
 *
 * Input: n = 467, target = 6
 * Output: 33
 * Explanation: Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5. It can be shown that we can not make n beautiful with adding non-negative integer less than 33.
 *
 * Example 3:
 *
 * Input: n = 1, target = 1
 * Output: 0
 * Explanation: Initially n is 1 and its digit sum is 1, which is already smaller than or equal to target.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^12
 * 1 <= target <= 150
 * The input will be generated such that it is always possible to make n beautiful.
 */
public class MinimumAdditionToMakeIntegerBeautiful {

    // 数字和变小只能是发生进位 从最小的进位开始考虑
    public long makeIntegerBeautiful(long n, int target) {
        for (long v = 1; ; v *= 10) {
            // v - n % v 为最小进位所需增加的
            long m = n + (v - n % v) % v;
            int sum = 0;
            for (long x = m; x > 0; x /= 10) {
                sum += (int) (x % 10);
            }
            if (sum <= target) {
                return m - n;
            }
        }
    }

}
