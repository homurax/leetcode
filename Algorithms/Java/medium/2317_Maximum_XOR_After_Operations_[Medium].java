/**
 * 2317. Maximum XOR After Operations
 *
 *
 * You are given a 0-indexed integer array nums. In one operation, select any non-negative integer x and an index i, then update nums[i] to be equal to nums[i] AND (nums[i] XOR x).
 *
 * Note that AND is the bitwise AND operation and XOR is the bitwise XOR operation.
 *
 * Return the maximum possible bitwise XOR of all elements of nums after applying the operation any number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,4,6]
 * Output: 7
 * Explanation: Apply the operation with x = 4 and i = 3, num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2.
 * Now, nums = [3, 2, 4, 2] and the bitwise XOR of all the elements = 3 XOR 2 XOR 4 XOR 2 = 7.
 * It can be shown that 7 is the maximum possible bitwise XOR.
 * Note that other operations may be used to achieve a bitwise XOR of 7.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,9,2]
 * Output: 11
 * Explanation: Apply the operation zero times.
 * The bitwise XOR of all the elements = 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11.
 * It can be shown that 11 is the maximum possible bitwise XOR.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^8
 */
public class MaximumXORAfterOperations {


    /**
     * 1. nums[i] 可以 xor 任意非负整数 -> nums[i] 可以被修改未任意非负整数
     * 2. nums[i] 与 任意非负整数 -> nums[i] 每一位可以从 1 变为 0 && 不可以从 0 变为 1 -> 可以控制 nums[i] 上那些位为 1
     * 3. nums xor 后的结果, 位数为 1 的越多则越大，结合 1 && 2，可以控制某一个位上 1 的总数
     *
     *  nums 在那些位上有 1
     */
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans |= num;
        }
        return ans;
    }

}
