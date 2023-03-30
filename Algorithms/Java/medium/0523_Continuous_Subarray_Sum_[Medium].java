/**
 * 523. Continuous Subarray Sum
 *
 *
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 *
 * A good subarray is a subarray where:
 *
 * its length is at least two, and
 * the sum of the elements of the subarray is a multiple of k.
 * Note that:
 *
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 *
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 *
 *
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 * 3. 0 <= sum(nums[i]) <= 2^31 - 1
 * 4. 1 <= k <= 2^31 - 1
 */
public class ContinuousSubarraySum {

    // sum[i] 为前缀和数组
    // [i, j] -> sum[j] - sum[i - 1]
    // sum[j] - sum[i - 1] = n * k
    // sum[j]、sum[i - 1] 对 k 同余
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }

}
