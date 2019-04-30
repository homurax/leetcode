/**
 * 409. Longest Palindrome
 *
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {

    public int longestPalindrome(String s) {

        int[] table = new int[123];
        for (char c : s.toCharArray()) table[c]++;
        int ans = 0;
        for (int i : table) {
            if (i > 0) {
                ans += i / 2 * 2;
                if (ans % 2 == 0 && i % 2 == 1) { // 中位数
                    ans++;
                }
            }
        }
        return ans;
    }
}
