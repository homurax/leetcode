/**
 * 2131. Longest Palindrome by Concatenating Two Letter Words
 *
 *
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 *
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 *
 *
 * Example 2:
 *
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 *
 *
 * Example 3:
 *
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 *
 *
 * Constraints:
 *
 * 1. 1 <= words.length <= 10^5
 * 2. words[i].length == 2
 * 3. words[i] consists of lowercase English letters.
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {

    // 奇数次只可以出现一次
    public int longestPalindrome(String[] words) {
        int[][] cnt = new int[26][26];
        for (String word : words) {
            cnt[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }
        int ans = 0;
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            int count = cnt[i][i];
            ans += count - count % 2;
            odd |= count & 1;
            for (int j = i + 1; j < 26; j++) {
                ans += Math.min(cnt[i][j], cnt[j][i]) * 2;
            }
        }
        return (ans + odd) * 2;
    }

}
