/**
 * 1930. Unique Length-3 Palindromic Subsequences
 *
 *
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
 *
 * Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
 *
 * A palindrome is a string that reads the same forwards and backwards.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "aabca"
 * Output: 3
 * Explanation: The 3 palindromic subsequences of length 3 are:
 * - "aba" (subsequence of "aabca")
 * - "aaa" (subsequence of "aabca")
 * - "aca" (subsequence of "aabca")
 *
 * Example 2:
 *
 * Input: s = "adc"
 * Output: 0
 * Explanation: There are no palindromic subsequences of length 3 in "adc".
 *
 * Example 3:
 *
 * Input: s = "bbcbaba"
 * Output: 4
 * Explanation: The 4 palindromic subsequences of length 3 are:
 * - "bbb" (subsequence of "bbcbaba")
 * - "bcb" (subsequence of "bbcbaba")
 * - "bab" (subsequence of "bbcbaba")
 * - "aba" (subsequence of "bbcbaba")
 *
 *
 * Constraints:
 *
 * 1. 3 <= s.length <= 10^5
 * 2. s consists of only lowercase English letters.
 */
public class UniqueLength3PalindromicSubsequences {

    // 以 s[i] 为中间字符的回文种数为前缀 s[0, i-1] 与后缀 s[i+1, n-1] 的公共字符种数
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] pre = new int[n];
        int[] suf = new int[n];
        char[] letters = s.toCharArray();
        for (int i = 0; i < n; i++) {
            pre[i] = (i == 0 ? 0 : pre[i - 1]) | (1 << (letters[i] - 'a'));
        }
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = (i == n - 1 ? 0 : suf[i + 1]) | (1 << (letters[i] - 'a'));
        }
        // 去重
        int[] set = new int[26];
        for (int i = 1; i < n - 1; i++) {
            set[letters[i] - 'a'] |= (pre[i - 1] & suf[i + 1]);
        }
        int ans = 0;
        for (int status : set) {
            ans += Integer.bitCount(status);
        }
        return ans;
    }


    // 确定两头，中间不同字符数就是可能组合数
    public int countPalindromicSubsequence1(String s) {
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(c);
        }
        int ans = 0;
        for (Character c : new HashSet<>(list)) {
            int l = list.indexOf(c);
            int r = list.lastIndexOf(c);
            if (r - l > 1) {
                ans += new HashSet<>(list.subList(l + 1, r)).size();
            }
        }
        return ans;
    }

}
