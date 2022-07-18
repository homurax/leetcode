/**
 * 673. Number of Longest Increasing Subsequence
 *
 *
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 * Notice that the sequence has to be strictly increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 *
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 2000
 * 2. -10^6 <= nums[i] <= 10^6
 */
public class NumberOfLongestIncreasingSubsequence {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        int[] count = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++) {
            f[i] = count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (f[i] < f[j] + 1) { // 覆盖
                        f[i] = f[j] + 1;
                        count[i] = count[j];
                    } else if (f[i] == f[j] + 1) { // 累加
                        count[i] += count[j];
                    }
                }
            }
            max = Math.max(max, f[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == max) {
                ans += count[i];
            }
        }
        return ans;
    }

}
