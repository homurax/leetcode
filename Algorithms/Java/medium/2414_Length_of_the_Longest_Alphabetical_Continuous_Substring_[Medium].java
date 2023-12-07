/**
 * 2414. Length of the Longest Alphabetical Continuous Substring
 *
 *
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
 *
 * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
 * Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacaba"
 * Output: 2
 * Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
 * "ab" is the longest continuous substring.
 *
 *
 * Example 2:
 *
 * Input: s = "abcde"
 * Output: 5
 * Explanation: "abcde" is the longest continuous substring.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only English lowercase letters.
 */
public class LengthOfTheLongestAlphabeticalContinuousSubstring {


    public int longestContinuousSubstring(String s) {
        int ans = 1;
        int start = 0;
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1) + 1) {
                ans = Math.max(ans, i - start);
                start = i;
            }
        }
        return Math.max(ans, n - start);
    }


    public int longestContinuousSubstring1(String s) {
        int n = s.length();
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int len = 1;
            while (i + 1 < n && s.charAt(i + 1) - s.charAt(i) == 1) {
                len++;
                i++;
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }

}
