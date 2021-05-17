/**
 * 1567. Maximum Length of Subarray With Positive Product
 *
 *
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 *
 * Example 2:
 *
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 *
 * Example 3:
 *
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 *
 * Example 4:
 *
 * Input: nums = [-1,2]
 * Output: 1
 *
 * Example 5:
 *
 * Input: nums = [1,2,3,5,-6,4,0,10]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^9 <= nums[i] <= 10^9
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {

    private int ans = 0;
    public int getMaxLen1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, 0, true);
            if (ans > nums.length - i - 1) {
                break;
            }
        }
        return ans;
    }
    // TLE
    private void dfs(int[] nums, int idx, int len, boolean positive) {
        if (idx == nums.length || nums[idx] == 0) {
            return;
        }
        len++;
        if (nums[idx] > 0) {
            if (positive) {
                ans = Math.max(len, ans);
            }
        } else {
            if (!positive) {
                ans = Math.max(len, ans);
                positive = true;
            } else {
                positive = false;
            }
        }
        dfs(nums, idx + 1, len, positive);
    }


    // DFS 出现 TLE 下意识考虑滑窗
    public int getMaxLen(int[] nums) {
        int positive = nums[0] > 0 ? 1 : 0;
        int negative = nums[0] < 0 ? 1 : 0;
        int max = positive;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                positive++;
                if (negative > 0) {
                    negative++;
                }
            } else if (nums[i] < 0) {
                int tempPositive = negative > 0 ? negative + 1 : 0;
                negative = positive + 1;
                positive = tempPositive;
            } else {
                positive = 0;
                negative = 0;
            }
            max = Math.max(max, positive);
        }
        return max;
    }

}
