/**
 * 1959. Minimum Total Space Wasted With K Resizing Operations
 *
 *
 * You are currently designing a dynamic array. You are given a 0-indexed integer array nums, where nums[i] is the number of elements that will be in the array at time i. In addition, you are given an integer k, the maximum number of times you can resize the array (to any size).
 *
 * The size of the array at time t, sizet, must be at least nums[t] because there needs to be enough space in the array to hold all the elements. The space wasted at time t is defined as sizet - nums[t], and the total space wasted is the sum of the space wasted across every time t where 0 <= t < nums.length.
 *
 * Return the minimum total space wasted if you can resize the array at most k times.
 *
 * Note: The array can have any size at the start and does not count towards the number of resizing operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,20], k = 0
 * Output: 10
 * Explanation: size = [20,20].
 * We can set the initial size to be 20.
 * The total wasted space is (20 - 10) + (20 - 20) = 10.
 *
 * Example 2:
 *
 * Input: nums = [10,20,30], k = 1
 * Output: 10
 * Explanation: size = [20,20,30].
 * We can set the initial size to be 20 and resize to 30 at time 2.
 * The total wasted space is (20 - 10) + (20 - 20) + (30 - 30) = 10.
 *
 * Example 3:
 *
 * Input: nums = [10,20,15,30,20], k = 2
 * Output: 15
 * Explanation: size = [10,20,20,30,30].
 * We can set the initial size to 10, resize to 20 at time 1, and resize to 30 at time 3.
 * The total wasted space is (10 - 10) + (20 - 20) + (20 - 15) + (30 - 30) + (30 - 20) = 15.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 200
 * 2. 1 <= nums[i] <= 10^6
 * 3. 0 <= k <= nums.length - 1
 */
public class MinimumTotalSpaceWastedWithKResizingOperations {

    // 动态规划
    // nums 划分为 k + 1 段连续子数组
    // dp[i][j] -> nums[0:i] 划分为 j 段浪费的最小空间
    // g[i][j] -> nums[i:j] 这一段的最大值乘以这一段的长度再减去这一段的元素和
    // dp[i][j] = min(dp[i0][j - 1] + g[i0][i]), i0 in [0, i]
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                sum += nums[j];
                g[i][j] = max * (j - i + 1) - sum;
            }
        }
        int[][] dp = new int[n][k + 2];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k + 1; j++) {
                for (int i0 = 0; i0 <= i; i0++) {
                    dp[i][j] = Math.min(dp[i][j], (i0 == 0 ? 0 : dp[i0 - 1][j - 1]) + g[i0][i]);
                }
            }
        }
        return dp[n - 1][k + 1];
    }

}
