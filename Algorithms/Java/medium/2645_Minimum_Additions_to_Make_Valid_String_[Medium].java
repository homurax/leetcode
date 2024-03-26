/**
 * 2645. Minimum Additions to Make Valid String
 *
 *
 * Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times, return the minimum number of letters that must be inserted so that word becomes valid.
 *
 * A string is called valid if it can be formed by concatenating the string "abc" several times.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "b"
 * Output: 2
 * Explanation: Insert the letter "a" right before "b", and the letter "c" right next to "a" to obtain the valid string "abc".
 * Example 2:
 *
 * Input: word = "aaa"
 * Output: 6
 * Explanation: Insert letters "b" and "c" next to each "a" to obtain the valid string "abcabcabc".
 * Example 3:
 *
 * Input: word = "abc"
 * Output: 0
 * Explanation: word is already valid. No modifications are needed.
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 50
 * word consists of letters "a", "b" and "c" only.
 */
public class MinimumAdditionsToMakeValidString {

    // 考虑每一组 x = word[i - 1], y = word[i]
    // 如果 x < y, 则可以在同一组 abc 中
    // 如果 x >= y, 则一定不在同一组 abc 中
    public int addMinimum(String word) {
        int cnt = 1;
        char[] chars = word.toCharArray();
        int n = chars.length;
        for (int i = 1; i < n; i++) {
            if (chars[i - 1] >= chars[i]) {
                cnt++;
            }
        }
        return 3 * cnt - n;
    }

}
