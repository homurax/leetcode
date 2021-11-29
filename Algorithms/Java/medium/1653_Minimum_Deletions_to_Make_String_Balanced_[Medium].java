/**
 * 1653. Minimum Deletions to Make String Balanced
 *
 *
 * You are given a string s consisting only of characters 'a' and 'b'.
 *
 * You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 *
 * Return the minimum number of deletions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * Example 2:
 *
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. s[i] is 'a' or 'b'.
 */
public class MinimumDeletionsToMakeStringBalanced {

    /**
     * char = 'a' & count_b > 0 的时候就是矛盾点
     * 不管删除 a 还是 b，总要删除一个，count_b-- 就代表了进行一次抵消
     */
    public int minimumDeletions(String s) {
        int ans = 0, count_b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                 count_b++;
            } else {
                if (count_b > 0) {
                    count_b--;
                    ans++;
                }
            }
        }
        return ans;
    }

}
