/**
 * 53. Maximum Subarray
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int num : nums) {
            sum = sum > 0 ? sum + num : num;
            ans = Math.max(ans, sum);
        }
        return ans;
    }


    public int maxSubArray2(int[] nums) {

        return divide(nums, 0, nums.length - 1);
    }

    private int divide(int[] nums, int start, int end) {

        if (start == end)
            return nums[start];

        int mid = (start + end) >>> 1;
        int sum = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            leftMax = Math.max(leftMax, sum);
        }

        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            rightMax = Math.max(rightMax, sum);
        }

        int max = Math.max(divide(nums, start, mid), divide(nums, mid + 1, end));

        return Math.max(max, leftMax + rightMax);
    }
}
