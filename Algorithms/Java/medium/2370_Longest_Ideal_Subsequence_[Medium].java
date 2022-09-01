/**
 * 2370. Longest Ideal Subsequence
 *
 *
 * You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
 *
 * t is a subsequence of the string s.
 * The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
 * Return the length of the longest ideal string.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "acfgbd", k = 2
 * Output: 4
 * Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
 * Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
 *
 *
 * Example 2:
 *
 * Input: s = "abcd", k = 3
 * Output: 4
 * Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. 0 <= k <= 25
 * 3. s consists of lowercase English letters.
 */
public class LongestIdealSubsequence {

    // dp[i][c] -> s 的前 i 个字母中的以 c 结尾的理想字符串的最长长度
    // dp[i][c] 由 dp[i - 1][ [c - k, c + k] ] 转移
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[] dp = new int[26];
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = Math.max(c - k, 0); j <= Math.min(c + k, 25); j++) {
                dp[c] = Math.max(dp[c], dp[j]);
            }
            dp[c]++;
        }
        int max = 0;
        for (int cnt : dp) {
            if (cnt > max) {
                max = cnt;
            }
        }
        return max;
    }


    // TLE
    public int longestIdealString1(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        char[] letters = s.toCharArray();
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (Math.abs(letters[i] - letters[j]) <= k) {
                    int temp = dp[j] == 0 ? 2 : dp[j] + 1;
                    if (temp > dp[i]) {
                        dp[i] = temp;
                        ans = Math.max(ans, dp[i]);
                    }
                }
            }
        }
        return ans;
    }

}
