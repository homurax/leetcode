/**
 * 796. Rotate String
 *
 * We are given two strings, A and B.
 *
 * A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.
 *
 * Example 1:
 * Input: A = 'abcde', B = 'cdeab'
 * Output: true
 *
 * Example 2:
 * Input: A = 'abcde', B = 'abced'
 * Output: false
 * Note:
 *
 * A and B will have length at most 100.
 */
public class RotateString {

    /**
     * 通过加上偏移量进行比较
     */
    public boolean rotateString(String A, String B) {

        if (A.length() != B.length()) return false;
        int length = A.length();
        if (length == 0) return true;

        mark:
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int offset = (i + j) % length;
                if (A.charAt(offset) != B.charAt(j)) {
                    continue mark;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * A+A中包含所有可能的情况
     */
    public boolean rotateString2(String A, String B) {

        return A.length() == B.length() && (A+A).contains(B);
    }

}
