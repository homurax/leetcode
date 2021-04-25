/**
 * 316. Remove Duplicate Letters
 *
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^4
 * 2. s consists of lowercase English letters.
 */
public class RemoveDuplicateLetters {

    // StringBuilder 模拟单调栈 考虑栈顶元素移除能否让字典序更小
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        char[] letters = s.toCharArray();
        for (char c : letters) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : letters) {
            if (!visited[c - 'a']) {
                char pre;
                while (sb.length() > 0 && (pre = sb.charAt(sb.length() - 1)) > c) {
                    // 已经用完 不可移除
                    if (count[pre - 'a'] == 0) {
                        break;
                    }
                    // 为了字典序更小 移除
                    visited[pre - 'a'] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }
                visited[c - 'a'] = true;
                sb.append(c);
            }
            count[c - 'a'] -= 1;
        }
        return sb.toString();
    }

}
