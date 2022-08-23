/**
 * 918. Maximum Sum Circular Subarray
 *
 *
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 *
 * Example 2:
 *
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 *
 * Example 3:
 *
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 *
 * Constraints:
 *
 * 1. n == nums.length
 * 2. 1 <= n <= 3 * 10^4
 * 3. -3 * 10^4 <= nums[i] <= 3 * 10^4
 */
public class MaximumSumCircularSubarray {

    // dp[i] = 以 nums[i] 结尾的最大子数组和
    // 在中间 常规 Kadane 获得 max(dp[i])
    // 在两端 dp 代表最小子数组和 sum - min(dp[i]) 为最大子数组和
    // 为了避免 nums 全部是负数时 会导致 sum - min 为 0 不判断最后一个元素
    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0;
        int dp = 0;
        int max = nums[0];
        for (int num : nums) {
            sum += num;
            dp = num + Math.max(dp, 0);
            max = Math.max(max, dp);
        }
        int min = 0;
        int n = nums.length;
        dp = nums[0];
        for (int i = 1; i < n - 1; i++) {
            dp = nums[i] + Math.min(dp, 0);
            min = Math.min(min, dp);
        }
        return Math.max(sum - min, max);
    }

}
