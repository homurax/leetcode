/**
 * 2311. Longest Binary Subsequence Less Than or Equal to K
 *
 *
 * You are given a binary string s and a positive integer k.
 *
 * Return the length of the longest subsequence of s that makes up a binary number less than or equal to k.
 *
 * Note:
 *
 * The subsequence can contain leading zeroes.
 * The empty string is considered to be equal to 0.
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 *
 * Example 1:
 *
 * Input: s = "1001010", k = 5
 * Output: 5
 * Explanation: The longest subsequence of s that makes up a binary number less than or equal to 5 is "00010", as this number is equal to 2 in decimal.
 * Note that "00100" and "00101" are also possible, which are equal to 4 and 5 in decimal, respectively.
 * The length of this subsequence is 5, so 5 is returned.
 *
 * Example 2:
 *
 * Input: s = "00101001", k = 1
 * Output: 6
 * Explanation: "000001" is the longest subsequence of s that makes up a binary number less than or equal to 1, as this number is equal to 1 in decimal.
 * The length of this subsequence is 6, so 6 is returned.
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s[i] is either '0' or '1'.
 * 3. 1 <= k <= 10^9
 */
public class LongestBinarySubsequenceLessThanOrEqualToK {

    // 在靠后的位置找到不超过 k 的子序列
    // 子序列前面尽可能补充前导 0
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int m = 32 - Integer.numberOfLeadingZeros(k);
        if (n < m) {
            return n;
        }
        int ans = Integer.parseInt(s.substring(n - m), 2) <= k ? m : m - 1;
        for (int i = 0; i < n - m; i++) {
            if (s.charAt(i) == '0') {
                ans++;
            }
        }
        return ans;
    }

}
