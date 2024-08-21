/**
 * 2981. Find Longest Special Substring That Occurs Thrice I
 *
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
 *
 * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaaa"
 * Output: 2
 * Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
 * It can be shown that the maximum length achievable is 2.
 *
 *
 * Example 2:
 *
 * Input: s = "abcdef"
 * Output: -1
 * Explanation: There exists no special substring which occurs at least thrice. Hence return -1.
 *
 *
 * Example 3:
 *
 * Input: s = "abcaba"
 * Output: 1
 * Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
 * It can be shown that the maximum length achievable is 1.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 50
 * s consists of only lowercase English letters.
 */
public class FindLongestSpecialSubstringThatOccursThriceI {

    // 考虑维护相同字母连续字串的长度列表，按照降序排列
    // 1. len[0] 中可以取 3 个 (len[0] - 2) 长度的字串
    // 2. len[0] == len[1] 可以取 3 个 (len[0] - 1) 长度的字串
    // 3. len[0] > len[1] 可以取 3 个 (len[1]) 长度的字串
    // 4. len[0] len[1] len[2] 中可以取 3 个 (len[2]) 长度的字串
    // 合并情况 2、3 max(len[0] - 2, min(len[0] - 1, len[1]), len[2])
    // 长度列表末尾添加 2 个 0，不用考虑长度不足 3 的情况
    public int maximumLength(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        List<Integer>[] groups = new ArrayList[26];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int i = 0, cnt = 0; i < length; i++) {
            cnt++;
            if (i == length - 1 || chars[i] != chars[i + 1]) {
                groups[chars[i] - 'a'].add(cnt);
                cnt = 0;
            }
        }
        int ans = 0;
        for (List<Integer> list : groups) {
            if (list.isEmpty()) {
                continue;
            }
            list.sort(Collections.reverseOrder());
            list.add(0);
            list.add(0);
            ans = Math.max(ans, Math.max(list.get(0) - 2, Math.max(Math.min(list.get(0) - 1, list.get(1)), list.get(2))));
        }
        return ans > 0 ? ans : -1;
    }

}
