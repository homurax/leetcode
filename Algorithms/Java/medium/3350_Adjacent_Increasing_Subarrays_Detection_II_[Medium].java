/**
 * 3350. Adjacent Increasing Subarrays Detection II
 *
 *
 * Given an array nums of n integers, your task is to find the maximum value of k for which there exist two adjacent
 * subarrays
 *  of length k each, such that both subarrays are strictly increasing. Specifically, check if there are two subarrays of length k starting at indices a and b (a < b), where:
 *
 * Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
 * The subarrays must be adjacent, meaning b = a + k.
 * Return the maximum possible value of k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,7,8,9,2,3,4,3,1]
 *
 * Output: 3
 *
 * Explanation:
 *
 * The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
 * The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
 * These two subarrays are adjacent, and 3 is the maximum possible value of k for which two such adjacent strictly increasing subarrays exist.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,4,4,4,5,6,7]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The subarray starting at index 0 is [1, 2], which is strictly increasing.
 * The subarray starting at index 2 is [3, 4], which is also strictly increasing.
 * These two subarrays are adjacent, and 2 is the maximum possible value of k for which two such adjacent strictly increasing subarrays exist.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class AdjacentIncreasingSubarraysDetectionII {

    // max(一段的一半, 两段中长度较小的)
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int ans = 0;
        int cnt = 0, preCnt = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                ans = Math.max(ans, Math.max(cnt / 2, Math.min(cnt, preCnt)));
                preCnt = cnt;
                cnt = 0;
            }
        }
        return ans;
    }

}