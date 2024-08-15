/**
 * 3153. Sum of Digit Differences of All Pairs
 *
 *
 * You are given an array nums consisting of positive integers where all integers have the same number of digits.
 *
 * The digit difference between two integers is the count of different digits that are in the same position in the two integers.
 *
 * Return the sum of the digit differences between all pairs of integers in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [13,23,12]
 *
 * Output: 4
 *
 * Explanation:
 * We have the following:
 * - The digit difference between 13 and 23 is 1.
 * - The digit difference between 13 and 12 is 1.
 * - The digit difference between 23 and 12 is 2.
 * So the total sum of digit differences between all pairs of integers is 1 + 1 + 2 = 4.
 *
 *
 * Example 2:
 *
 * Input: nums = [10,10,10,10]
 *
 * Output: 0
 *
 * Explanation:
 * All the integers in the array are the same. So the total sum of digit differences between all pairs of integers will be 0.
 *
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] < 10^9
 * All integers in nums have the same number of digits.
 */
public class SumOfDigitDifferencesOfAllPairs {

    // 对 个、十、百 等按照每一位考虑
    // 对于 num = nums[i], diff = i - cnt[num]
    public long sumDigitDifferences(int[] nums) {
        int[][] cnt = new int[Integer.toString(nums[0]).length()][10];
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; num > 0; num /= 10, j++) {
                int d = num % 10;
                ans += i - cnt[j][d]++;
            }
        }
        return ans;
    }

}
