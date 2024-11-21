/**
 * 3297. Count Substrings That Can Be Rearranged to Contain a String I
 *
 *
 * You are given two strings word1 and word2.
 *
 * A string x is called valid if x can be rearranged to have word2 as a prefix .
 *
 * Return the total number of valid substrings of word1.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "bcca", word2 = "abc"
 *
 * Output: 1
 *
 * Explanation:
 *
 * The only valid substring is "bcca" which can be rearranged to "abcc" having "abc" as a prefix.
 *
 *
 * Example 2:
 *
 * Input: word1 = "abcabc", word2 = "abc"
 *
 * Output: 10
 *
 * Explanation:
 *
 * All the substrings except substrings of size 1 and size 2 are valid.
 *
 *
 * Example 3:
 *
 * Input: word1 = "abcabc", word2 = "aaabc"
 *
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 1 <= word1.length <= 10^5
 * 1 <= word2.length <= 10^4
 * word1 and word2 consist only of lowercase English letters.
 */
public class CountSubstringsThatCanBeRearrangedToContainAStringI {

    // 确定无差异窗口区间, 固定右端点, 累加排除左端点
    public long validSubstringCount(String word1, String word2) {
        int[] cnt = new int[26];
        char[] src = word1.toCharArray();
        char[] target = word2.toCharArray();
        for (char c : target) {
            cnt[c - 'a']++;
        }
        int diff = 0;
        for (int num : cnt) {
            if (num > 0) {
                diff++;
            }
        }
        int left = 0;
        long ans = 0;
        for (char c : src) {
            if (--cnt[c - 'a'] == 0) {
                diff--;
            }
            while (diff == 0) {
                char out = src[left++];
                if (cnt[out - 'a'] == 0) {
                    // 移除会破坏窗口平衡
                    diff++;
                }
                cnt[out - 'a']++;
            }
            ans += left;
        }
        return ans;
    }

}
