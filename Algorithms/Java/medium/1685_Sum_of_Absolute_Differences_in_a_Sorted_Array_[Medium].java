/**
 * 1685. Sum of Absolute Differences in a Sorted Array
 *
 *
 * You are given an integer array nums sorted in non-decreasing order.
 *
 * Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
 *
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,5]
 * Output: [4,3,5]
 * Explanation: Assuming the arrays are 0-indexed, then
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
 *
 * Example 2:
 *
 * Input: nums = [1,4,6,8,10]
 * Output: [24,15,13,15,21]
 *
 *
 * Constraints:
 *
 * 1. 2 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= nums[i + 1] <= 10^4
 */
public class SumOfAbsoluteDifferencesInASortedArray {

    // 非递减 nums[i] <= nums[i + 1]
    // 利用前缀和 preSum[i] = sum(nums[0:i-1]) 前 i 个
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = nums[i] * i - preSum[i];
            int right = (preSum[n] - preSum[i + 1]) - nums[i] * (n - 1 - i);
            ans[i] = left + right;
        }
        return ans;
    }

    // 动态维护 leftSum leftSum 不需要前缀和数组
    public int[] getSumAbsoluteDifferences2(int[] nums) {
        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }
        int n = nums.length;
        int leftSum = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (nums[i] * i - leftSum) + (rightSum - nums[i] * (n - i));
            leftSum += nums[i];
            rightSum -= nums[i];
        }
        return ans;
    }

}
