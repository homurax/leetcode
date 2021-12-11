/**
 * 1695. Maximum Erasure Value
 *
 *
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 *
 * Example 2:
 *
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^4
 */
public class MaximumErasureValue {

    public int maximumUniqueSubarray(int[] nums) {
        int left = 0, max = 0, score = 0;
        boolean[] visited = new boolean[10001];
        for (int num : nums) {
            if (!visited[num]) {
                visited[num] = true;
                score += num;
                max = Math.max(max, score);
            } else {
                while (nums[left] != num) {
                    visited[nums[left]] = false;
                    score -= nums[left];
                    left++;
                }
                left++;
            }
        }
        return max;
    }

}
