/**
 * 421. Maximum XOR of Two Numbers in an Array
 *
 *
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
 *
 * Follow up: Could you do this in O(n) runtime?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 *
 * Example 2:
 *
 * Input: nums = [0]
 * Output: 0
 *
 * Example 3:
 *
 * Input: nums = [2,4]
 * Output: 6
 *
 * Example 4:
 *
 * Input: nums = [8,10,2]
 * Output: 10
 *
 * Example 5:
 *
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1
 */
public class MaximumXOROfTwoNumbersInAnArray {

    public int findMaximumXOR(int[] nums) {
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int L = Integer.toBinaryString(maxNum).length();
        int maxXor = 0, currXor;
        Set<Integer> prefixes = new HashSet<>();
        for (int i = L - 1; i > -1; i--) {
            // 释放出下一比特位的位置
            maxXor <<= 1;
            // 最右端置为 1 的 maxXor 当作 currXor
            currXor = maxXor | 1;
            prefixes.clear();
            // 从最高位向后每次多一位的前缀集合
            for (int num : nums) {
                prefixes.add(num >> i);
            }
            // a ^ b = c
            // a ^ b ^ b = c ^ b
            // a = c ^ b
            for (int p : prefixes) {
                // 存在这样的 currXor
                if (prefixes.contains(currXor ^ p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }

}
