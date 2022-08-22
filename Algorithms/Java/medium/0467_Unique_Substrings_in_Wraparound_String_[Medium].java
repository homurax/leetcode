/**
 * 467. Unique Substrings in Wraparound String
 *
 *
 * We define the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this:
 *
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Given a string p, return the number of unique non-empty substrings of p are present in s.
 *
 *
 *
 * Example 1:
 *
 * Input: p = "a"
 * Output: 1
 * Explanation: Only the substring "a" of p is in s.
 * Example 2:
 *
 * Input: p = "cac"
 * Output: 2
 * Explanation: There are two substrings ("a", "c") of p in s.
 * Example 3:
 *
 * Input: p = "zab"
 * Output: 6
 * Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab") of p in s.
 *
 *
 * Constraints:
 *
 * 1. 1 <= p.length <= 10^5
 * 2. p consists of lowercase English letters.
 */
public class UniqueSubstringsInWraparoundString {

    // cnt[i] = 以字符 p[i] 结尾在 s 中的子串的最长长度
    public int findSubstringInWraproundString(String p) {
        int len = 0;
        int[] cnt = new int[26];
        int n = p.length();
        char[] letters = p.toCharArray();
        for (int i = 0; i < n; i++) {
            if (i > 0 && (letters[i] - letters[i - 1] + 26) % 26 == 1) { // ab za
                len++;
            } else {
                len = 1;
            }
            cnt[letters[i] - 'a'] = Math.max(cnt[letters[i] - 'a'], len);
        }
        int sum = 0;
        for (int i : cnt) {
            sum += i;
        }
        return sum;
    }

}
