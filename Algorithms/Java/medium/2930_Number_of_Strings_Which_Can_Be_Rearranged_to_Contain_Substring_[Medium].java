/**
 * 2930. Number of Strings Which Can Be Rearranged to Contain Substring
 *
 *
 * You are given an integer n.
 *
 * A string s is called good if it contains only lowercase English characters and it is possible to rearrange the characters of s such that the new string contains "leet" as a substring.
 *
 * For example:
 *
 * The string "lteer" is good because we can rearrange it to form "leetr" .
 * "letl" is not good because we cannot rearrange it to contain "leet" as a substring.
 * Return the total number of good strings of length n.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 12
 * Explanation: The 12 strings which can be rearranged to have "leet" as a substring are: "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
 *
 * Example 2:
 *
 * Input: n = 10
 * Output: 83943898
 * Explanation: The number of strings with length 10 which can be rearranged to have "leet" as a substring is 526083947580. Hence the answer is 526083947580 % (109 + 7) = 83943898.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 */
public class NumberOfStringsWhichCanBeRearrangedToContainSubstring {

    // 26^n - 不含 leet 的个数
    // 不含 leet 需满足三个条件之一：
    // 1. 不含 l
    // 2. 不含 t
    // 3. 不含 e 或者 只有一个 e
    // --------------------------------
    // 至少满足一个条件：
    // 1. 25^n
    // 2. 25^n
    // 3. 25^n（不含 e） + n x 25^(n-1)（n 个位置任选一个为 e，其余位置不含 e）
    // (3 x 25 + n) x 25^(n-1)
    // --------------------------------
    // 至少满足两个条件：
    // 1. 不含 l t                24^n
    // 1. 不含 l 且 e 不足两个      24^n + n x 24^(n-1)
    // 1. 不含 t 且 e 不足两个      24^n + n x 24^(n-1)
    // (3 x 24 + 2n) x 24^(n-1)
    // --------------------------------
    // 满足三个条件：23^n + n x 23^(n - 1)
    // --------------------------------
    // 容斥原理：不含 leet 的字符串的个数为「至少满足一个条件」减去「至少满足两个条件」加上「满足三个条件」
    // 26^n − (3 x 25 + n) x 25^(n − 1) + (3 x 24 + 2n) x 24^(n-1) − 23^n + n x 23^(n - 1)
    private static final long MOD = (long) 1e9 + 7;

    public int stringCount(int n) {
        return (int) (((pow(26, n)
                - pow(25, n - 1) * (75 + n)
                + pow(24, n - 1) * (72 + n * 2)
                - pow(23, n - 1) * (23 + n)) % MOD + MOD) % MOD); // 保证结果非负
    }

    private long pow(long x, int n) {
        long rst = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                rst = rst * x % MOD;
            }
            x = x * x % MOD;
        }
        return rst;
    }

}
