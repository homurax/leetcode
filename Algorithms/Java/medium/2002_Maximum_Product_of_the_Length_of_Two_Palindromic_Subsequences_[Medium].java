/**
 * 2002. Maximum Product of the Length of Two Palindromic Subsequences
 *
 *
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.
 *
 * Return the maximum possible product of the lengths of the two palindromic subsequences.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: s = "leetcodecom"
 * Output: 9
 * Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
 * The product of their lengths is: 3 * 3 = 9.
 *
 * Example 2:
 *
 * Input: s = "bb"
 * Output: 1
 * Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
 * The product of their lengths is: 1 * 1 = 1.
 *
 * Example 3:
 *
 * Input: s = "accbcaxxcxx"
 * Output: 25
 * Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
 * The product of their lengths is: 5 * 5 = 25.
 *
 *
 * Constraints:
 *
 * 1. 2 <= s.length <= 12
 * 2. s consists of lowercase English letters only.
 */
public class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {

    public int maxProduct(String s) {
        int n = s.length(), limit = 1 << n;
        int[] count = new int[limit];
        char[] letters = s.toCharArray();
        for (int mask = 0; mask < limit; mask++) {
            if (isPalindromic(letters, mask)) {
                count[mask] = Integer.bitCount(mask);
            }
        }
        int max = 1;
        for (int mask = 1; mask < limit; mask++) {
            // 枚举到 mask / 2 即可
            int split = mask >> 1;
            for (int subset = (mask - 1) & mask; subset > split; subset = (subset - 1) & mask) {
                max = Math.max(max, count[subset] * count[mask ^ subset]);
            }
        }
        return max;
    }

    private boolean isPalindromic(char[] letters, int mask) {
        int l = 0, r = letters.length - 1;
        while (l < r) {
            while (l < r && (mask >> l & 1) == 0) {
                l++;
            }
            while (l < r && (mask >> r & 1) == 0) {
                r--;
            }
            if (letters[l] != letters[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
