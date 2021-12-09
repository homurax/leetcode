/**
 * 1680. Concatenation of Consecutive Binary Numbers
 *
 *
 * Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: "1" in binary corresponds to the decimal value 1.
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: 27
 * Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
 * After concatenating them, we have "11011", which corresponds to the decimal value 27.
 *
 * Example 3:
 *
 * Input: n = 12
 * Output: 505379714
 * Explanation: The concatenation results in "1101110010111011110001001101010111100".
 * The decimal value of that is 118505380540.
 * After modulo 10^9 + 7, the result is 505379714.
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 10^5
 */
public class ConcatenationOfConsecutiveBinaryNumbers {

    /**
     * 站在十进制角度处理
     *
     * 对于十进制 i, 现有二进制部分需要左移 len_2(i) 位，再加上 i
     * 设现有二进制部分对应十进制数为 x，即 x * 2 ^ len_2(i) + i
     * f(i) = f(i - 1) * 2 ^ len_2(i) + i
     * f(1) = 1
     *
     * len_2(i) 计算
     * len_2(i) = 32 - Integer.numberOfLeadingZeros(i);
     *
     * 递推，len_2(i - 1) 与 len_2(i) 相差 1
     * i & (i−1) = 0，则 i 和 i-1 的二进制表示中没有某一位均为 1，那么 i 就是 2 的整数次幂
     *
     */
    public int concatenatedBinary(int n) {
        int MOD = 1_000_000_007;
        long ans = 0;
        int shift = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                shift++;
            }
            // ans = ((ans << shift) % MOD + i) % MOD;
            ans = ((ans << shift) | i) % MOD;
        }
        return (int) ans;
    }


}
