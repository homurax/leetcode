/**
 * 29. Divide Two Integers
 *
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 *
 *
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 *
 *
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 *
 *
 * Constraints:
 *
 * 1. -2^31 <= dividend, divisor <= 2^31 - 1
 * 2. divisor != 0
 */
public class DivideTwoIntegers {

    // 使用 long 和 倍增模拟乘法
    public int divide(int dividend, int divisor) {
        long a = dividend, b = divisor;
        // 记录正负后都当作整数处理
        boolean flag = (a < 0 && b > 0) || (a > 0 && b < 0);
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        long l = 0, r = a;
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            if (mul(mid, b) <= a) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        r = flag ? -r : r;
        if (r > Integer.MAX_VALUE || r < -Integer.MAX_VALUE - 1) {
            return Integer.MAX_VALUE;
        }
        return (int) r;
    }

    private long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            // 奇数累加
            if ((k & 1) == 1) {
                ans += a;
            }
            // 偶数倍增
            k >>= 1;
            a += a;
        }
        return ans;
    }

}
