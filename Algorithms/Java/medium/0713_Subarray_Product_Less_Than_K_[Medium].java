/**
 * 713. Subarray Product Less Than K
 *
 *
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 *
 * 1. 0 < nums.length <= 50000.
 * 2. 0 < nums[i] < 1000.
 * 3. 0 <= k < 10^6.
 */
public class SubarrayProductLessThanK {

    /*
    // 可能超时并且缺少重复结果判断
    private int ans = 0;
    private void dfs(int cur, int index, int[] nums, int limit) {
        if (index == nums.length) {
            return;
        }
        cur *= nums[index];
        if (cur >= limit) {
            return;
        }
        ans++;
        dfs(cur, index + 1, nums, limit);
    }*/


    // 滑窗
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int temp = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            temp *= nums[right];
            while (temp >= k) { // right 不用回退, left 前进
                temp /= nums[left++];
            }
            ans += right - left + 1;
        }
        return ans;
    }



}
