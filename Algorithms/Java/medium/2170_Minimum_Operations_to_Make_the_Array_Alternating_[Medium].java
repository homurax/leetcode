/**
 * 2170. Minimum Operations to Make the Array Alternating
 *
 *
 * You are given a 0-indexed array nums consisting of n positive integers.
 *
 * The array nums is called alternating if:
 *
 * nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * In one operation, you can choose an index i and change nums[i] into any positive integer.
 *
 * Return the minimum number of operations required to make the array alternating.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,3,2,4,3]
 * Output: 3
 * Explanation:
 * One way to make the array alternating is by converting it to [3,1,3,1,3,1].
 * The number of operations required in this case is 3.
 * It can be proven that it is not possible to make the array alternating in less than 3 operations.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,2,2,2]
 * Output: 2
 * Explanation:
 * One way to make the array alternating is by converting it to [1,2,1,2,1].
 * The number of operations required in this case is 2.
 * Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class MinimumOperationsToMakeTheArrayAlternating {

    // 考虑奇偶位分组后每组的最大的两个数
    // 如过不同则直接选择, 否则交替选择一个组的最大值与另一组的次大值, 取最大组合
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int[] even = new int[100010], odd = new int[100010];
        int even1 = 0, even2 = 0, odd1 = 0, odd2 = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even[nums[i]]++;
                int cur = even[nums[i]];
                if (even1 == 0 || cur > even[even1]) {
                    even2 = even1;
                    even1 = nums[i];
                } else if (nums[i] != even1 && (even2 == 0 || cur > even[even2])) {
                    even2 = nums[i];
                }
            } else {
                odd[nums[i]]++;
                int cur = odd[nums[i]];
                if (odd1 == 0 || cur > odd[odd1]) {
                    odd2 = odd1;
                    odd1 = nums[i];
                } else if (nums[i] != odd1 && (odd2 == 0 || cur > odd[odd2])) {
                    odd2 = nums[i];
                }
            }
        }
        if (even1 != odd1) {
            return n - (even[even1] + odd[odd1]);
        }
        return n - Math.max(even[even1] + odd[odd2], even[even2] + odd[odd1]);
    }

}
