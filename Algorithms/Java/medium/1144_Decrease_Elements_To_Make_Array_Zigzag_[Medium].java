/**
 * 1144. Decrease Elements To Make Array Zigzag
 *
 *
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
 *
 * An array A is a zigzag array if either:
 *
 * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * Return the minimum number of moves to transform the given array nums into a zigzag array.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: We can decrease 2 to 0 or 3 to 1.
 *
 * Example 2:
 *
 * Input: nums = [9,6,1,6,2]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 1000
 */
public class DecreaseElementsToMakeArrayZigzag {

    // 把偶数index点砍成波谷 或者 把奇数index点砍成波谷
    public int movesToMakeZigzag(int[] nums) {
        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int r1 = 0, r2 = 0;
            if ((i & 1) == 0) {
                if (i > 0 && nums[i - 1] <= nums[i]) {
                    r1 = nums[i] - nums[i - 1] + 1;
                }
                if (i + 1 < nums.length && nums[i] >= nums[i + 1]) {
                    r2 = nums[i] - nums[i + 1] + 1;
                }
                ans1 += Math.max(r1, r2);
            } else {
                if (nums[i - 1] <= nums[i]) {
                    r1 = nums[i] - nums[i - 1] + 1;
                }
                if (i + 1 < nums.length && nums[i] >= nums[i + 1]) {
                    r2 = nums[i] - nums[i + 1] + 1;
                }
                ans2 += Math.max(r1, r2);
            }
        }
        return Math.min(ans1, ans2);
    }

}
