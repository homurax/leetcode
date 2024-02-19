/**
 * 2182. Construct String With Repeat Limit
 *
 *
 * You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.
 *
 * Return the lexicographically largest repeatLimitedString possible.
 *
 * A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cczazcc", repeatLimit = 3
 * Output: "zzcccac"
 * Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
 * The letter 'a' appears at most 1 time in a row.
 * The letter 'c' appears at most 3 times in a row.
 * The letter 'z' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
 * Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
 *
 *
 * Example 2:
 *
 * Input: s = "aababab", repeatLimit = 2
 * Output: "bbabaa"
 * Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
 * The letter 'a' appears at most 2 times in a row.
 * The letter 'b' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
 * Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.
 *
 *
 * Constraints:
 *
 * 1 <= repeatLimit <= s.length <= 10^5
 * s consists of lowercase English letters.
 */
public class ConstructStringWithRepeatLimit {

    // 贪心 优先拼接较大的字符
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int cnt = 0, w = 0;
        char [] chars = new char[s.length()];
        for (int i = 25, j = 24; i >= 0 && j >= 0;) {
            // 当前较大字符已经用完
            if (count[i] == 0) {
                cnt = 0;
                i--;
            } else if (cnt < repeatLimit) {
                // 当前较大字符未超限 仍可拼接
                chars[w++] = (char) ('a' + i);
                cnt++;
                count[i]--;
            } else if (j >= i || count[j] == 0) {
                // 当前较大字符未用完但是超过限制，且次大字符需要更新，查找次大字符
                j--;
            } else {
                // 当前较大字符未用完但是超过限制，持有合法次大字符
                // 下次拼接的仍是较大字符，即更换了新字符，所以重置计数
                chars[w++] = (char) ('a' + j);
                cnt = 0;
                count[j]--;
            }
        }
        return new String(chars, 0, w);
    }

}
