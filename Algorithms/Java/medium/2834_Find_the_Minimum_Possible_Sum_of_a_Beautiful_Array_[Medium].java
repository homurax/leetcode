/**
 * 2834. Find the Minimum Possible Sum of a Beautiful Array
 *
 *
 * You are given positive integers n and target.
 *
 * An array nums is beautiful if it meets the following conditions:
 *
 * nums.length == n.
 * nums consists of pairwise distinct positive integers.
 * There doesn't exist two distinct indices, i and j, in the range [0, n - 1], such that nums[i] + nums[j] == target.
 * Return the minimum possible sum that a beautiful array could have modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, target = 3
 * Output: 4
 * Explanation: We can see that nums = [1,3] is beautiful.
 * - The array nums has length n = 2.
 * - The array nums consists of pairwise distinct positive integers.
 * - There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
 * It can be proven that 4 is the minimum possible sum that a beautiful array could have.
 *
 *
 * Example 2:
 *
 * Input: n = 3, target = 3
 * Output: 8
 * Explanation: We can see that nums = [1,3,4] is beautiful.
 * - The array nums has length n = 3.
 * - The array nums consists of pairwise distinct positive integers.
 * - There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
 * It can be proven that 8 is the minimum possible sum that a beautiful array could have.
 *
 *
 * Example 3:
 *
 * Input: n = 1, target = 1
 * Output: 1
 * Explanation: We can see, that nums = [1] is beautiful.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^9
 * 1 <= target <= 10^9
 */
public class FindTheMinimumPossibleSumOfABeautifulArray {


    // target 可以拆分为 (1, target - 1), (2, target - 2), ..., (target / 2, target - target / 2)
    // m = min(k / 2, n), 数组第一段为 [1, m], 数组第二段为 [target, target + n - m - 1]
    // sum[1, m] = (1 + m) * m / 2
    // sum[target, target + n - m - 1] = (2 * target + n - m - 1) * (n - m) / 2
    public int minimumPossibleSum(int n, int target) {
        long m = Math.min(target / 2, n);
        // target * 2 放在 n - m - 1 之后防止 n 或 target 过大导致超限
        return (int) ((m * (m + 1) + (n - m - 1 + target * 2) * (n - m)) / 2 % 1_000_000_007);
    }

}
