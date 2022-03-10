/**
 * 2012. Sum of Beauty in the Array
 *
 *
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 *
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 2.
 *
 * Example 2:
 *
 * Input: nums = [2,4,6,4]
 * Output: 1
 * Explanation: For each index i in the range 1 <= i <= 2:
 * - The beauty of nums[1] equals 1.
 * - The beauty of nums[2] equals 0.
 *
 * Example 3:
 *
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 0.
 *
 *
 * Constraints:
 *
 * 1. 3 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class SumOfBeautyInTheArray {

    public int sumOfBeauties(int[] nums) {
        int beauty = 0;
        int n = nums.length;
        int preMax = nums[0];
        int[] sufMin = new int[n];
        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 1; i--) {
            sufMin[i] = Math.min(nums[i], sufMin[i + 1]);
        }
        for (int i = 1; i <= n - 2; i++) {
            if (preMax < nums[i] && nums[i] < sufMin[i + 1]) {
                beauty += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                beauty++;
            }
            if (nums[i] > preMax) {
                preMax = nums[i];
            }
        }
        return beauty;
    }

}
