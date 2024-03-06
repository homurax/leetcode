/**
 * 481. Magical String
 *
 *
 * A magical string s consists of only '1' and '2' and obeys the following rules:
 *
 * The string s is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string s itself.
 * The first few elements of s is s = "1221121221221121122……". If we group the consecutive 1's and 2's in s, it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......" and the occurrences of 1's or 2's in each group are "1 2 2 1 1 2 1 2 2 1 2 2 ......". You can see that the occurrence sequence is s itself.
 *
 * Given an integer n, return the number of 1's in the first n number in the magical string s.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: 3
 * Explanation: The first 6 elements of magical string s is "122112" and it contains three 1's, so return 3.
 *
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 */
public class MagicalString {

    // 字符串由若干个 1 和若干个 2 交替出现
    // s[i+1] 开始的若干个具体是几个 由 s[i] 决定
    public int magicalString(int n) {
        char[] s = new char[n + 2];
        s[0] = 1;
        s[1] = s[2] = 2;
        char c = 2;
        for (int i = 2, w = 3; w < n; i++) {
            // 1 和 2 之间切换
            c ^= 3;
            s[w++] = c;
            if (s[i] == 2) {
                s[w++] = c;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += 2 - s[i];
        }
        return ans;
    }

}
