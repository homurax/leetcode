/**
 * 1358. Number of Substrings Containing All Three Characters
 *
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 *
 * Example 2:
 *
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
 *
 * Example 3:
 *
 * Input: s = "abc"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 5 x 10^4
 * s only consists of a, b or c characters.
 */
public class NumberOfSubstringsContainingAllThreeCharacters {

    /*
    对于从 i 开始的所有字串 合法性随下标具有单调性
        [i, j] 合法 [i, j + 1] 必然合法
        [i, j] 非法 [i, j - 1] 必然非法
    对于每一个 i 找到 j 即可
        ans = sum{ i | len(s) - j + 1}
    对于从 0 开始的每一个 i 对应的 j 称为 pos_i 必然为单调不降的
     */
    public int numberOfSubstrings(String s) {
        int ans = 0;
        int left = 0, right = -1, len = s.length();
        int[] count = new int[3];
        char[] letters = s.toCharArray();
        while (left < len) {
            while (right < len && !(count[0] >= 1 && count[1] >= 1 && count[2] >= 1)) {
                if (++right == len) {
                    break;
                }
                count[letters[right] - 'a']++;
            }
            ans += len - right;
            count[letters[left++] - 'a']--;
        }
        return ans;
    }


    public int numberOfSubstrings2(String s) {
        char[] letters = s.toCharArray();
        int len = letters.length;
        int[] pos = new int[3];
        Arrays.fill(pos, -1);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            pos[letters[i] - 'a'] = i;
            int cur = i;
            for (int j = 0; j < 3; j++) {
                cur = Math.min(cur, pos[j]);
            }
            if (cur == -1) {
                continue;
            }
            ans += cur + 1;
        }
        return ans;
    }
}
