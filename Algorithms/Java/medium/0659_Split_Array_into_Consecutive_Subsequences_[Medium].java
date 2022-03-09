/**
 * 659. Split Array into Consecutive Subsequences
 *
 *
 * You are given an integer array nums that is sorted in non-decreasing order.
 *
 * Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:
 *
 * Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
 * All subsequences have a length of 3 or more.
 * Return true if you can split nums according to the above conditions, or false otherwise.
 *
 * A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,5]
 * Output: true
 * Explanation: nums can be split into the following subsequences:
 * [1,2,3,3,4,5] --> 1, 2, 3
 * [1,2,3,3,4,5] --> 3, 4, 5
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,3,4,4,5,5]
 * Output: true
 * Explanation: nums can be split into the following subsequences:
 * [1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
 * [1,2,3,3,4,4,5,5] --> 3, 4, 5
 *
 * Example 3:
 *
 * Input: nums = [1,2,3,4,4,5]
 * Output: false
 * Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^4
 * 2. -1000 <= nums[i] <= 1000
 * 3. nums is sorted in non-decreasing order.
 */
public class SplitArrayIntoConsecutiveSubsequences {

    // 只要求子串长度不小于 3
    // 不要求子串数量
    // 贪心
    public boolean isPossible(int[] nums) {
        int[] counts = new int[2004];
        int[] ends = new int[2004];
        for (int num : nums) {
            counts[num + 1001]++;
        }
        for (int num : nums) {
            int cur = num + 1001;
            if (counts[cur] <= 0) {
                continue;
            }
            if (ends[cur - 1] > 0) { // 存在 cur - 1 结尾的子串 拼上 cur
                ends[cur - 1]--;
                ends[cur]++;
                counts[cur]--;
            } else if (counts[cur + 1] > 0 && counts[cur + 2] > 0) { // cur, cur + 1, cur + 2
                counts[cur]--;
                counts[cur + 1]--;
                counts[cur + 2]--;
                ends[cur + 2]++;
            } else {
                return false;
            }
        }
        return true;
    }

}
