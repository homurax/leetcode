/**
 * 5. Longest Palindromic Substring
 *
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 *
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {

    // 中心扩散
    public String longestPalindrome1(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        int idx = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int l = i - 1, r = i + 1, len = 1;
            while (l >= 0 && s.charAt(l) == s.charAt(i)) {
                l--;
                len++;
            }
            while (r < n && s.charAt(r) == s.charAt(i)) {
                r++;
                len++;
            }
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                len += 2;
            }
            if (len > maxLen) {
                maxLen = len;
                idx = l;
            }
        }
        return s.substring(idx + 1, idx + maxLen + 1);
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        boolean[][] dp = new boolean[n][n];
        int left = 0, right = 0, len = 0;
        for (int r = 1; r < n; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > len) {
                        len = r - l + 1;
                        left = l;
                        right = r;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }

}
