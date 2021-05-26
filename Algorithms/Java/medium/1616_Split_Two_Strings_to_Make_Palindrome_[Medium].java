/**
 * 1616. Split Two Strings to Make Palindrome
 *
 *
 * You are given two strings a and b of the same length. Choose an index and split both strings at the same index, splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings: bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
 *
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty. For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 *
 * Return true if it is possible to form a palindrome string, otherwise return false.
 *
 * Notice that x + y denotes the concatenation of strings x and y.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "x", b = "y"
 * Output: true
 * Explaination: If either a or b are palindromes the answer is true since you can split in the following way:
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * Then, aprefix + bsuffix = "" + "y" = "y", which is a palindrome.
 *
 * Example 2:
 *
 * Input: a = "abdef", b = "fecab"
 * Output: true
 *
 * Example 3:
 *
 * Input: a = "ulacfd", b = "jizalu"
 * Output: true
 * Explaination: Split them at index 3:
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 *
 * Example 4:
 *
 * Input: a = "xbdef", b = "xecab"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 1 <= a.length, b.length <= 10^5
 * 2. a.length == b.length
 * 3. a and b consist of lowercase English letters
 */
public class SplitTwoStringsToMakePalindrome {

    // 从字符串中心向外扩展找回文串
    // 匹配结束后考虑拼接前和后
    public boolean checkPalindromeFormation(String a, String b) {
        int n = a.length();
        int left = (n >> 1) - 1;
        left = Math.min(check(a, a, n, left), check(b, b, n, left));
        left = Math.min(check(a, b, n, left), check(b, a, n, left));
        return left == -1;
    }

    private int check(String a, String b, int n, int left) {
        int right = n - 1 - left;
        while (left >= 0 && right < n) {
            if (a.charAt(left) != b.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return left;
    }

}
