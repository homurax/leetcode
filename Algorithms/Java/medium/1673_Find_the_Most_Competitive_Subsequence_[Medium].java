/**
 * 1673. Find the Most Competitive Subsequence
 *
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 *
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 *
 * We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,6], k = 2
 * Output: [2,6]
 * Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 *
 * Example 2:
 *
 * Input: nums = [2,4,3,3,5,4,9,6], k = 4
 * Output: [2,3,3,4]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 * 3. 1 <= k <= nums.length
 */
public class FindTheMostCompetitiveSubsequence {

    // TLE
    public int[] mostCompetitive1(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        int[] ans = new int[k];
        int start = 0, idx = 0;
        while (k > 0) {
            int min = Integer.MAX_VALUE;
            for (int i = start; i <= nums.length - k; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                    start = i + 1;
                }
            }
            ans[idx++] = min;
            k--;
        }
        return ans;
    }

    // 单调栈
    public int[] mostCompetitive2(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        int[] ans = new int[k];
        ans[0] = Integer.MAX_VALUE;
        int write = 1;
        for (int i = 0; i < nums.length; i++) {
            while (write > 0 && nums[i] < ans[write - 1] && k - write < nums.length - i) {
                write--;
            }
            if (write < k) {
                ans[write++] = nums[i];
            }
        }
        return ans;
    }

    public int[] mostCompetitive(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        int min = Integer.MAX_VALUE, minIdx = 0;
        for (int i = 0; i <= nums.length - k; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIdx = i;
            }
        }
        int[] ans = new int[k];
        ans[0] = nums[minIdx];
        if (k == 1) {
            return ans;
        }

        int write = 1;
        for (int i = minIdx + 1; i < nums.length; i++) {
            while (write > 0 && nums[i] < ans[write - 1] && k - write < nums.length - i) {
                write--;
            }
            if (write < k) {
                ans[write++] = nums[i];
            }
        }
        return ans;
    }

}
