/**
 * 2571. Minimum Operations to Reduce an Integer to 0
 *
 *
 * You are given a positive integer n, you can do the following operation any number of times:
 *
 * Add or subtract a power of 2 from n.
 * Return the minimum number of operations to make n equal to 0.
 *
 * A number x is power of 2 if x == 2i where i >= 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 39
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 20 = 1 to n, so now n = 40.
 * - Subtract 23 = 8 from n, so now n = 32.
 * - Subtract 25 = 32 from n, so now n = 0.
 * It can be shown that 3 is the minimum number of operations we need to make n equal to 0.
 *
 *
 * Example 2:
 *
 * Input: n = 54
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 21 = 2 to n, so now n = 56.
 * - Add 23 = 8 to n, so now n = 64.
 * - Subtract 26 = 64 from n, so now n = 0.
 * So the minimum number of operations is 3.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 */
public class MinimumOperationsToReduceAnIntegerTo0 {

    // 低比特位加减可以影响高比特位
    // 如果低比特位存在连续 1 则加 lowbit 可以消除多一个
    // 如果低比特位只有单个 1 则减 lowbit
    public int minOperations(int n) {
        int ans = 1;
        while ((n & (n - 1)) > 0) {
            int lowbit = n & -n;
            if ((n & (lowbit << 1)) > 0) {
                n += lowbit;
            } else {
                n -= lowbit;
            }
            ans++;
        }
        return ans;
    }

}
