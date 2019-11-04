/**
 * 680. Valid Palindrome II
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {

        if (s.length() == 1) { return true; }

        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return validPalindrome(s, low + 1, high) || validPalindrome(s, low, high - 1);
            }
            low++;
            high--;
        }
        return true;
    }
    private boolean validPalindrome(String str, int low, int high) {
        while (low < high) {
            if (str.charAt(low++) != str.charAt(high--)) {
                return false;
            }
        }
        return true;
    }



    public boolean validPalindrome2(String s) {

        if (s.length() == 1) { return true; }
        return validPalindrome(s, 0, s.length() - 1, 0);
    }
    private boolean validPalindrome(String str, int low, int high, int step) {

        if (step > 1) { return false; }

        while (low < high) {
            if (str.charAt(low) == str.charAt(high)) {
                low++;
                high--;
            } else {
                return validPalindrome(str, low + 1, high, step + 1) || validPalindrome(str, low, high - 1, step + 1);
            }
        }
        return true;
    }

}
