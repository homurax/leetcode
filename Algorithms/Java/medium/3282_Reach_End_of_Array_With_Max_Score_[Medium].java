/**
 * 3282. Reach End of Array With Max Score
 *
 *
 * You are given an integer array nums of length n.
 *
 * Your goal is to start at index 0 and reach index n - 1. You can only jump to indices greater than your current index.
 *
 * The score for a jump from index i to index j is calculated as (j - i) * nums[i].
 *
 * Return the maximum possible total score by the time you reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1,5]
 *
 * Output: 7
 *
 * Explanation:
 *
 * First, jump to index 1 and then jump to the last index. The final score is 1 * 1 + 2 * 3 = 7.
 *
 *
 * Example 2:
 *
 * Input: nums = [4,3,1,3,2]
 *
 * Output: 16
 *
 * Explanation:
 *
 * Jump directly to the last index. The final score is 4 * 4 = 16.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class ReachEndOfArrayWithMaxScore {

    // (j - i) * nums[i] 等价于计算矩形面积
    // 从 0 跳跃到 i, 相当于矩形的边长从 nums[0] 变为 nums[i], 贪心遇到更高的才跳
    // 不跳就相当于累加 nums[i] * 1
    public long findMaximumScore(List<Integer> nums) {
        int max = 0;
        long ans = 0;
        for (int i = 0; i < nums.size() - 1; i++) {
            max = Math.max(max, nums.get(i));
            ans += max;
        }
        return ans;
    }

}
