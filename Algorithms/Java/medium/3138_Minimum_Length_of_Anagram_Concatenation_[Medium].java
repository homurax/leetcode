/**
 * 3138. Minimum Length of Anagram Concatenation
 *
 *
 * You are given a string s, which is known to be a concatenation of anagrams of some string t.
 *
 * Return the minimum possible length of the string t.
 *
 * An anagram is formed by rearranging the letters of a string. For example, "aab", "aba", and, "baa" are anagrams of "aab".
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abba"
 *
 * Output: 2
 *
 * Explanation:
 *
 * One possible string t could be "ba".
 *
 *
 * Example 2:
 *
 * Input: s = "cdef"
 *
 * Output: 4
 *
 * Explanation:
 *
 * One possible string t could be "cdef", notice that t can be equal to s.
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consist only of lowercase English letters.
 */
public class MinimumLengthOfAnagramConcatenation {

    // 10^5 之内的数字因子个数最多为 128 个（83160 的因子个数）
    public int minAnagramLength(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        next:
        for (int k = 1; k <= n / 2; k++) {
            if (n % k != 0) {
                continue;
            }
            int[] cnt0 = new int[26];
            for (int i = 0; i < k; i++) {
                cnt0[chars[i] - 'a']++;
            }
            for (int i = k * 2; i <= n; i += k) {
                int[] cnt = new int[26];
                for (int j = i - k; j < i; j++) {
                    cnt[chars[j] - 'a']++;
                }
                if (!Arrays.equals(cnt, cnt0)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }

}
