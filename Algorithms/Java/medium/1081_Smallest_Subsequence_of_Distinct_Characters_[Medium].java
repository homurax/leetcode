/**
 * 1081. Smallest Subsequence of Distinct Characters
 *
 *
 * Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s consists of lowercase English letters.
 *
 *
 * Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
 */
public class SmallestSubsequenceOfDistinctCharacters {

    public String smallestSubsequence(String s) {
        char[] letters = s.toCharArray();
        int[] count = new int[26];
        for (char c : letters) {
            count[c - 'a']++;
        }
        boolean[] visited = new boolean[26];
        char[] rst = new char[letters.length];
        int index = 0;
        for (char curr : letters) {
            if (!visited[curr - 'a']) {
                char prev;
                while (index > 0 && (prev = rst[index - 1]) > curr) {
                    if (count[prev - 'a'] == 0) {
                        break;
                    }
                    visited[prev - 'a'] = false;
                    index--;
                }
                visited[curr - 'a'] = true;
                rst[index++] = curr;
            }
            count[curr - 'a']--;
        }
        return new String(rst, 0, index);
    }

}
