/**
 * 395. Longest Substring with At Least K Repeating Characters
 *
 *
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 *
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^5
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    // 如果 count(char) < k 则包含 char 的字串都不可选
    public int longestSubstring(String s, int k) {
        if (k == 1) {
            return s.length();
        }
        return dfs(s, 0, s.length() - 1, k);
    }

    private int dfs(String s, int left, int right, int k) {
        if (right + 1 < k) {
            return 0;
        }
        int[] cnt = new int[26];
        for (int i = left; i <= right; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        for (int i = left; i <= right; i++) {
            if (cnt[s.charAt(i) - 'a'] >= k) {
                continue;
            }
            int next = i + 1;
            while (next < right && cnt[s.charAt(next) - 'a'] < k) {
                next++;
            }
            return Math.max(dfs(s, left, i - 1, k), dfs(s, next, right, k));
        }
        return right - left + 1;
    }

}
