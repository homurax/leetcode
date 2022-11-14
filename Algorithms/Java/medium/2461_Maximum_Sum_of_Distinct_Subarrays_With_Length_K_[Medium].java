/**
 * 2461. Maximum Sum of Distinct Subarrays With Length K
 *
 *
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 *
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 *
 *
 * Example 2:
 *
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class MaximumSumOfDistinctSubarraysWithLengthK {

    public long maximumSubarraySum(int[] nums, int k) {
        long ans = 0;
        long sum = 0;
        int size = 0;
        int[] count = new int[100001];
        for (int i = 0; i < k - 1; i++) {
            if (++count[nums[i]] == 1) {
                size++;
            }
            sum += nums[i];
        }
        for (int i = k - 1; i < nums.length; i++) {
            if (++count[nums[i]] == 1) {
                size++;
            }
            sum += nums[i];
            if (size == k && sum > ans) {
                ans = sum;
            }
            int left = nums[i + 1 - k];
            if (--count[left] == 0) {
                size--;
            }
            sum -= left;
        }
        return ans;
    }

}
