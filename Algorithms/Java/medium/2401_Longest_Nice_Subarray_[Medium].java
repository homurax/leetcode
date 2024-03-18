/**
 * 2401. Longest Nice Subarray
 *
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
 *
 * Return the length of the longest nice subarray.
 *
 * A subarray is a contiguous part of an array.
 *
 * Note that subarrays of length 1 are always considered nice.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,8,48,10]
 * Output: 3
 * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
 * - 3 AND 8 = 0.
 * - 3 AND 48 = 0.
 * - 8 AND 48 = 0.
 * It can be proven that no longer nice subarray can be obtained, so we return 3.
 *
 *
 * Example 2:
 *
 * Input: nums = [3,1,5,11,13]
 * Output: 1
 * Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class LongestNiceSubarray {

    // 子数组中每个比特位上最多只有 1 个 1
    // 通过 |= 加入集合，通过 ^= 从集合中去除
    public int longestNiceSubarray(int[] nums) {
        int ans = 0;
        for (int l = 0, r = 0, or = 0; r < nums.length; r++) {
            while ((or & nums[r]) > 0) {
                or ^= nums[l++];
            }
            or |= nums[r];
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

}
