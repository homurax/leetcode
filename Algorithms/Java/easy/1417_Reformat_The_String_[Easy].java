/**
 * 1417. Reformat The String
 *
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 *
 * You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.
 *
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 *
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by digits.
 *
 * Example 3:
 *
 * Input: s = "1229857369"
 * Output: ""
 * Explanation: "1229857369" has only digits so we cannot separate them by characters.
 *
 * Example 4:
 *
 * Input: s = "covid2019"
 * Output: "c2o0v1i9d"
 *
 * Example 5:
 *
 * Input: s = "ab123"
 * Output: "1a2b3"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters and/or digits.
 */
public class ReformatTheString {

    // swap
    public String reformat(String s) {
        int digit = 0, letter = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                digit++;
            } else {
                letter++;
            }
        }
        if (Math.abs(digit - letter) > 1) {
            return "";
        }
        if (digit > letter) {
            digit = 0;
            letter = 1;
        } else {
            digit = 1;
            letter = 0;
        }
        char[] temp = new char[s.length()];
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                temp[digit] = aChar;
                digit += 2;
            } else {
                temp[letter] = aChar;
                letter += 2;
            }
        }
        return new String(temp);
    }


    public String reformat2(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }

        char[] digits = new char[length];
        char[] letters = new char[length];
        char[] chars = s.toCharArray();
        int digit = 0, letter = 0;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                digits[digit++] = c;
            } else {
                letters[letter++] = c;
            }
        }
        if (Math.abs(digit - letter) > 1) {
            return "";
        }

        char[] temp1 = digit > letter ? digits : letters;
        char[] temp2 = digit > letter ? letters : digits;
        digit = letter = 0;
        for (int i = 0; i < length; i++) {
            chars[i] = ((i & 1) == 0) ? temp1[digit++] : temp2[letter++];
        }
        return new String(chars);
    }
}
