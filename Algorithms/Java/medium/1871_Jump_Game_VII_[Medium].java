/**
 * 1871. Jump Game VII
 *
 *
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 *
 *
 * Example 2:
 *
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 2 <= s.length <= 10^5
 * 2. s[i] is either '0' or '1'.
 * 3. s[0] == '0'
 * 4. 1 <= minJump <= maxJump < s.length
 */
public class JumpGameVII {

    // 从 [minJump, n) 开始 dp, [0, minJump) 预处理为可以到达
    // 使用前缀和 preSum 代表区间内存在跳点数量
    // 对于 s[i] == '0' && preSum[i - maxJump:i - minJump] > 0, 则为可以到达 i
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] preSum = new int[n];
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < minJump; i++) {
            preSum[i] = 1;
        }
        for (int i = minJump; i < n; i++) {
            if (s.charAt(i) == '0') {
                int l = i - maxJump, r = i - minJump;
                int cnt = preSum[r] - (l <= 0 ? 0 : preSum[l - 1]);
                dp[i] = cnt > 0 ? 1 : 0;
            }
            preSum[i] = preSum[i - 1] + dp[i];
        }
        return dp[n - 1] == 1;
    }

}
