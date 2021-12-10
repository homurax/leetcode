/**
 * 1297. Maximum Number of Occurrences of a Substring
 *
 *
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 *
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 *
 * Example 3:
 *
 * Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * Output: 3
 * Example 4:
 *
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. 1 <= maxLetters <= 26
 * 3. 1 <= minSize <= maxSize <= min(26, s.length)
 * 4. s only contains lowercase English letters.
 */
public class MaximumNumberOfOccurrencesOfASubstring {

    // 可选长字串必然覆盖短子串 考虑短的即可
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int max = 0, diff = 0, n = s.length();
        Map<String, Integer> map = new HashMap<>();
        int[] hash = new int[26];
        char[] letters = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (i >= minSize) {
                if (--hash[letters[i - minSize] - 'a'] == 0) {
                    diff--;
                }
            }
            if (++hash[letters[i] - 'a'] == 1) {
                diff++;
            }
            if (i >= minSize - 1 && diff <= maxLetters) {
                // max = Math.max(max, map.merge(new String(letters, i - minSize + 1, minSize), 1, Integer::sum));
                map.merge(new String(letters, i - minSize + 1, minSize), 1, Integer::sum);
            }
        }
        for (Integer count : map.values()) {
            max = Math.max(max, count);
        }
        return max;
    }

}
