/**
 * 2380. Time Needed to Rearrange a Binary String
 *
 *
 * You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10". This process repeats until no occurrences of "01" exist.
 *
 * Return the number of seconds needed to complete this process.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0110101"
 * Output: 4
 * Explanation:
 * After one second, s becomes "1011010".
 * After another second, s becomes "1101100".
 * After the third second, s becomes "1110100".
 * After the fourth second, s becomes "1111000".
 * No occurrence of "01" exists any longer, and the process needed 4 seconds to complete,
 * so we return 4.
 *
 *
 * Example 2:
 *
 * Input: s = "11100"
 * Output: 0
 * Explanation:
 * No occurrence of "01" exists in s, and the processes needed 0 seconds to complete,
 * so we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 */
public class TimeNeededToRearrangeABinaryString {

    // 01 -> 10 可以理解为 1 在向左移动
    // f(i) = 前 i 个字符移动所需时间, ans = f(n - 1)
    // pre0(i) = 前 i 个字符中 0 的个数, 只有 1 左侧有 0 才可以移动
    // s[i] == 0, 不需要移动 f(i) = f(i - 1)
    // s[i] == 1, f(i) = max(f(i - 1) + 1, pre0(i))
    // f(i - 1) + 1 -> s[i - 1] 与 s[i] 两辆车不可能同时到达各自的最终位置, 因为是 01 -> 10, 不会出现 11 同时左移
    // 且间隔也不会超过 1s, 只要 s[i] 左侧有 0, 下一秒就会移动过去
    public int secondsToRemoveOccurrences(String s) {
        int f = 0, pre0 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                pre0++;
            } else if (pre0 > 0) {
                f = Math.max(f + 1, pre0);
            }
        }
        return f;
    }

}
