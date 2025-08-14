/**
 * 2708. Maximum Strength of a Group
 *
 *
 * You are given a 0-indexed integer array nums representing the score of students in an exam. The teacher would like to form one non-empty group of students with maximal strength, where the strength of a group of students of indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​].
 *
 * Return the maximum strength of a group the teacher can create.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,-1,-5,2,5,-9]
 * Output: 1350
 * Explanation: One way to form a group of maximal strength is to group the students at indices [0,2,3,4,5]. Their strength is 3 * (-5) * 2 * 5 * (-9) = 1350, which we can show is optimal.
 *
 *
 * Example 2:
 *
 * Input: nums = [-4,-5,-4]
 * Output: 20
 * Explanation: Group the students at indices [0, 1] . Then, we’ll have a resulting strength of 20. We cannot achieve greater strength.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 13
 * -9 <= nums[i] <= 9
 */
public class MaximumStrengthOfAGroup {

    // 对于 nums[i]
    // nums[i] 自身成为最大值或者最小值
    // nums[i] > 0, nums[i] * mx 更新最大值, nums[i] * mn 更新最小值
    // nums[i] < 0, nums[i] * mn 更新最大值, nums[i] * mx 更新最小值
    public long maxStrength(int[] nums) {
        long mn = nums[0];
        long mx = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long x = nums[i];
            long tmp = mn;
            mn = Math.min(Math.min(mn, x), Math.min(mn * x, mx * x));
            mx = Math.max(Math.max(mx, x), Math.max(tmp * x, mx * x));
        }
        return mx;
    }

}
