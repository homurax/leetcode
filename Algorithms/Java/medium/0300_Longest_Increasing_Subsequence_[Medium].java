/**
 * 300. Longest Increasing Subsequence
 *
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 2500
 * 2. -10^4 <= nums[i] <= 10^4
 *
 *
 * Follow up:
 *
 * 1. Could you come up with the O(n^2) solution?
 * 2. Could you improve it to O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {

    private int ans = 0;

    public int lengthOfLIS(int[] nums) {
        dfs(nums, -10001, 0, 0);
        return ans;
    }

    // TLE
    private void dfs(int[] nums, int prev, int currIdx, int currLen) {
        if (currIdx == nums.length || currLen + nums.length - currIdx <= ans) {
            ans = Math.max(ans, currLen);
            return;
        }
        if (nums[currIdx] > prev) {
            dfs(nums, nums[currIdx], currIdx + 1, currLen + 1);
        }
        dfs(nums, prev, currIdx + 1, currLen);
    }

    // dp
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int right = 1; right < nums.length; right++) {
            dp[right] = 1;
            for (int left = 0; left < right; left++) {
                if (nums[right] > nums[left]) {
                    dp[right] = Math.max(dp[right], dp[left] + 1);
                }
            }
            ans = Math.max(ans, dp[right]);
        }
        return ans;
    }

    // 贪心 + 二分
    public int lengthOfLIS3(int[] nums) {
        int ans = 1, n = nums.length;
        // d[i] 表示长度为 i 的最长上升子序列的末尾元素的最小值
        int[] d = new int[n + 1];
        d[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[ans]) {
                d[++ans] = nums[i];
            } else {
                int left = 1, right = ans, pos = 0;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return ans;
    }


    public int lengthOfLIS4(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, ans, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == ans) {
                ans++;
            }
        }
        return ans;
    }

}
