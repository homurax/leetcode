/**
 * 58. Length of Last Word
 *
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 * Example:
 *
 * Input: "Hello World"
 * Output: 5
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = s.length() - 1;
        while (i >= 0 && Character.isSpaceChar(s.charAt(i))) {
            i--;
        }
        int count = 0;
        while (i >= 0 && Character.isLetter(s.charAt(i))) {
            count++;
            i--;
        }
        return count;
    }
}
