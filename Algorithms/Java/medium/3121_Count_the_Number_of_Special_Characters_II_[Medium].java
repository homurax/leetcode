/**
 * 3121. Count the Number of Special Characters II
 *
 *
 * You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.
 *
 * Return the number of special letters in word.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aaAbcBC"
 *
 * Output: 3
 *
 * Explanation:
 *
 * The special characters are 'a', 'b', and 'c'.
 *
 *
 * Example 2:
 *
 * Input: word = "abc"
 *
 * Output: 0
 *
 * Explanation:
 *
 * There are no special characters in word.
 *
 *
 * Example 3:
 *
 * Input: word = "AbBCab"
 *
 * Output: 0
 *
 * Explanation:
 *
 * There are no special characters in word.
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 2 * 10^5
 * word consists of only lowercase and uppercase English letters.
 */
public class CountTheNumberOfSpecialCharactersII {

    // 状态机
    //                       ↑ → ↓      ↑ → ↓
    // -1 <- 大写 - 0 - 小写 -> 1 - 大写 -> 2 -
    // ↑ ------------- 小写 -----------------|
    // 位运算
    //  a = 0110 0001
    //  A = 0100 0001
    // 31 = 0001 1111
    // 32 = 0010 0000
    public int numberOfSpecialChars(String word) {
        int lower = 0, upper = 0, invalid = 0;
        for (char c : word.toCharArray()) {
            int bit = 1 << (c & 31);
            if ((c & 32) > 0) { // 小写字母
                lower |= bit;
                if ((upper & bit) > 0) {
                    invalid |= bit;
                }
            } else {
                upper |= bit;
            }
        }
        return Integer.bitCount(lower & upper & ~invalid);
    }

}
