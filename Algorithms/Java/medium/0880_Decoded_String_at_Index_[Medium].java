/**
 * 880. Decoded String at Index
 *
 *
 * You are given an encoded string s. To decode the string to a tape, the encoded string is read one character at a time and the following steps are taken:
 *
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
 * Given an integer k, return the kth letter (1-indexed) in the decoded string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leet2code3", k = 10
 * Output: "o"
 * Explanation: The decoded string is "leetleetcodeleetleetcodeleetleetcode".
 * The 10th letter in the string is "o".
 *
 *
 * Example 2:
 *
 * Input: s = "ha22", k = 5
 * Output: "h"
 * Explanation: The decoded string is "hahahaha".
 * The 5th letter is "h".
 *
 *
 * Example 3:
 *
 * Input: s = "a2345678999999999999999", k = 1
 * Output: "a"
 * Explanation: The decoded string is "a" repeated 8301530446056247680 times.
 * The 1st letter is "a".
 *
 *
 * Constraints:
 *
 * 1. 2 <= s.length <= 100
 * 2. s consists of lowercase English letters and digits 2 through 9.
 * 3. s starts with a letter.
 * 4. 1 <= k <= 10^9
 * 5. It is guaranteed that k is less than or equal to the length of the decoded string.
 * 6. The decoded string is guaranteed to have less than 263 letters.
 */
public class DecodedStringAtIndex {

    public String decodeAtIndex(String s, int k) {
        int n = s.length();
        long l = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (Character.isLetter(c)) {
                l++;
            } else {
                l *= c - '0';
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            char c = chars[i];
            k %= l;
            if (k == 0 && Character.isLetter(c)) {
                return Character.toString(c);
            }
            if (Character.isLetter(c)) {
                l--;
            } else {
                l /= c - '0';
            }
        }
        return null;
    }

}
