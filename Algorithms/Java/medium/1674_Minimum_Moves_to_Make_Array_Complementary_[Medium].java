/**
 * 1674. Minimum Moves to Make Array Complementary
 *
 *
 * You are given an integer array nums of even length n and an integer limit. In one move, you can replace any integer from nums with another integer between 1 and limit, inclusive.
 *
 * The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number. For example, the array [1,2,3,4] is complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
 *
 * Return the minimum number of moves required to make nums complementary.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined elements are changed).
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change any number to 3 since 3 > limit.
 *
 *
 * Example 3:
 *
 * Input: nums = [1,2,1,2], limit = 2
 * Output: 0
 * Explanation: nums is already complementary.
 *
 *
 * Constraints:
 *
 * 1. n == nums.length
 * 2. 2 <= n <= 10^5
 * 3. 1 <= nums[i] <= limit <= 10^5
 * 4. n is even.
 */
public class MinimumMovesToMakeArrayComplementary {

    // 设 operand[x] 为 nums[i] + nums[n - 1 - i] = x 时所需的操作数，则 ans = min(operand[i])
    // 考虑 nums[i] 取值范围及替换值范围，x 取值范围为 [2, 2 * limit]
    //
    // 设 a = nums[i], b = nums[n - 1 - i]，操作数 operand[x] 可以是：
    // operand[x] = 0，x = a + b，即 a、b 都不修改
    // operand[x] = 1，x in [1 + min(A, B), limit + max(A, B)]，即只修改 a 或 b
    // operand[x] = 2，x in [2, 2 * limit]，即 a 和 b 都修改
    //
    // 遍历每一组 nums[i] + nums[n - 1 - i]
    // [2, 2 * limit] 范围内所需操作数 + 2
    // [1 + min(A, B), limit + max(A, B)] 范围内所需操作数 - 1（变为 1）
    // [a + b] 位置 - 1（变为 0）
    //
    // 区间修改则使用差分数组
    public int minMoves(int[] nums, int limit) {
        // 差分数组需要 r + 1
        int[] diff = new int[limit * 2 + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n - 1 - i];
            int l = 2, r = 2 * limit;
            diff[l] += 2;
            diff[r + 1] -= 2;
            diff[1 + Math.min(a, b)] -= 1;
            diff[limit + Math.max(a, b) + 1] -= -1;
            diff[a + b] -= 1;
            diff[a + b + 1] -= -1;
        }
        // 遍历 diff 即变相还原出 operand
        int ans = n, sum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            sum += diff[i];
            if (ans > sum) {
                ans = sum;
            }
        }
        return ans;
    }

}
