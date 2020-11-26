/**
 * 1262. Greatest Sum Divisible by Three
 *
 *
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 *
 * Example 2:
 *
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 *
 * Example 3:
 *
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 4 * 10^4
 * 2. 1 <= nums[i] <= 10^4
 */
public class GreatestSumDivisibleByThree {

    /**
     * 定义:
     *      dp[i][j] -> nums[0:i] 之和模 3 余 j 的最大和
     *      dp[i][1] -> nums[0:i] 模 3 余 1 的最大和
     *      ans = dp[n][0]
     *
     * 状态转移:
     *      nums[i] % 3 == 0
     *          dp[i][0] = max(dp[i - 1][0], dp[i - 1][0] + nums[i])
     * 			dp[i][1] = max(dp[i - 1][1], dp[i - 1][1] + nums[i])
     * 			dp[i][2] = max(dp[i - 1][2], dp[i - 1][2] + nums[i])
     *
     *      nums[i] % 3 == 1
     *          dp[i][0] = max(dp[i - 1][0], dp[i - 1][2] + nums[i])
     * 			dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + nums[i])
     * 			dp[i][2] = max(dp[i - 1][2], dp[i - 1][1] + nums[i])
     *
     *      nums[i] % 3 == 2
     *          dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + nums[i])
     * 			dp[i][1] = max(dp[i - 1][1], dp[i - 1][2] + nums[i])
     * 			dp[i][2] = max(dp[i - 1][2], dp[i - 1][0] + nums[i])
     *
     */
    public int maxSumDivThree1(int[] nums) {
        int[][] dp = new int[nums.length + 1][3];
        dp[0][1] = dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            if (num % 3 == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + num);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + num);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + num);
            } else if (num % 3 == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + num);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + num);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + num);
            } else if (num % 3 == 2) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + num);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + num);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + num);
            }
        }
        return dp[nums.length][0];
    }


    /**
     * 减去最小的数来让剩余和可被 3 整除
     * 考虑减去两种余数情况都可以满足条件:
     *      减去一个 模 3 余为 1 的数   i
     *      减去两个 模 3 余为 2 的数   j, k
     *
     *     max(sum - i, sum - j - k)
     *  -> min(i, j + k)
     */
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int remainder = sum % 3;
        if (remainder == 0) {
            return sum;
        }
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num % 3 == remainder && num < min) {
                min = num;
            }
        }
        int min1 = min, min2 = min;
        int remainder2 = 3 - remainder;
        for (int num : nums) {
            if (num % 3 == remainder2) {
                if (num <= min1) {
                    min2 = min1;
                    min1 = num;
                } else if (num < min2) {
                    min2 = num;
                }
            }
        }
        return (min1 + min2 < min) ? sum - min1 - min2 : sum - min;
    }

}
