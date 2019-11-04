/**
 * 647. Palindromic Substrings
 *
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 * Note:
 *
 * The input string length won't exceed 1000.
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int ans = 0, len = s.length();
        char[] charArr = s.toCharArray();
        for (int i = 0; i < len; i++) {
            // i为中心轴 奇数长度对称
            for (int j = 0; j <= i && i + j < len && charArr[i - j] == charArr[i + j]; j++) {
                ans++;
            }
            // 偶数长度对称
            int low = i - 1, high = i;
            while (low >= 0 && high < len && charArr[low] == charArr[high]) {
                ans++;
                low--;
                high++;
            }
        }
        return ans;
    }
}
