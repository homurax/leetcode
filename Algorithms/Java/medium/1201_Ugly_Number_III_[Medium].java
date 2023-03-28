/**
 * 1201. Ugly Number III
 *
 *
 * An ugly number is a positive integer that is divisible by a, b, or c.
 *
 * Given four integers n, a, b, and c, return the nth ugly number.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 *
 *
 * Example 2:
 *
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
 *
 *
 * Example 3:
 *
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
 *
 *
 * Constraints:
 *
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * It is guaranteed that the result will be in range [1, 2 * 10^9].
 */
public class UglyNumberIII {

    // 容斥原理
    public int nthUglyNumber(int n, int a, int b, int c) {
        long lcm_ab = lcm(a, b);
        long lcm_bc = lcm(b, c);
        long lcm_ac = lcm(a, c);
        long lcm_abc = lcm(a, lcm(b, c));
        int min = Math.min(a, Math.min(b, c));
        long l = 0, r = (long) min * n + 1;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            long count = mid / a + mid / b + mid / c - mid / lcm_ab - mid / lcm_ac - mid / lcm_bc + mid / lcm_abc;
            if (count < n) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return (int) r;
    }

    // 最小公倍数
    private long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    // 最大公约数
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
