/**
 * 1328. Break a Palindrome
 *
 *
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.
 *
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 *
 * Example 2:
 *
 * Input: palindrome = "a"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 */
public class BreakAPalindrome {

    public static String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len == 1) {
            return "";
        }
        char[] letters = palindrome.toCharArray();
        int mid = len / 2;
        for (int index = 0; index < mid; index++) {
            if (letters[index] != 'a') {
                letters[index] = 'a';
                return new String(letters);
            }
        }
        letters[len - 1] = 'b';
        return new String(letters);
    }


}
