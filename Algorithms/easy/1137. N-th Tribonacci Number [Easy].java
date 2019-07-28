/**
 * 1137. N-th Tribonacci Number
 *
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 *
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 * Constraints:
 *
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class NthTribonacciNumber {

    /**
     * 状态转移
     * dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
     */
    public int tribonacci(int n) {

        int a = 0, b = 1, c = 1;
        if (n < 2) return n;
        for (int i = 3; i <= n; i++) {
            int ans = a + b + c;
            a = b;
            b = c;
            c = ans;
        }
        return c;
    }
}
