/**
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 *
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 *
 * Example 2:
 *
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 *
 * Example 3:
 *
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters.
 */
public class FindTheLongestSubstringContainingVowelsInEvenCounts {


    public int findTheLongestSubstring(String s) {
        int[] pos = new int[32];
        Arrays.fill(pos, 1, 32, -1);
        // status 的低五位代表 a、e、i、o、u 的奇偶性 取值范围[0, 31]
        int len = s.length(), max = 0, status = 0;
        char[] letters = s.toCharArray();
        for (int i = 0; i < len; i++) {
            char ch = letters[i];
            if (ch == 'a') {
                status ^= 1;
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                max = Math.max(max, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return max;
    }

}
