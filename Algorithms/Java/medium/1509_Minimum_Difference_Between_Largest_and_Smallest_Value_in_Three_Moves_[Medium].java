/**
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 *
 *
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 *
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 *
 * Example 2:
 *
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 *
 * Example 3:
 *
 * Input: nums = [6,6,0,1,1,4,6]
 * Output: 2
 *
 * Example 4:
 *
 * Input: nums = [1,5,6,14,15]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < 4; i++) {
            min = Math.min(min, nums[nums.length - (4 - i)] - nums[i]);
        }
        return min;
    }


    // 不排序 维护最小最大的四个数
    public int minDifference2(int[] nums) {
        int n = nums.length;
        if (n <= 4) {
            return 0;
        }
        int[] max = new int[4];
        int[] min = new int[4];
        Arrays.fill(max, Integer.MIN_VALUE);
        Arrays.fill(min, Integer.MAX_VALUE);
        for (int num : nums) {
            int pos = 0;
            while (pos < 4 && max[pos] > num) {
                pos++;
            }
            if (pos < 4) {
                System.arraycopy(max, pos, max, pos + 1, 3 - pos);
                max[pos] = num;
            }
            pos = 0;
            while (pos < 4 && min[pos] < num) {
                pos++;
            }
            if (pos < 4) {
                System.arraycopy(min, pos, min, pos + 1, 3 - pos);
                min[pos] = num;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, max[i] - min[3 - i]);
        }
        return ans;
    }

}
