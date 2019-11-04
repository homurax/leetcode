/**
 * 136. Single Number
 *
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 *
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {

    /**
     * a ^ 0 = a
     * a ^ a = 0
     * a ^ b ^ a ^ c ^ c = b ^ 0 = b
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums)
            result ^= i;
        return result;
    }

    public int singleNumber2(int[] nums) {
        for (int i = 1; i < nums.length; i += 2) {
            nums[0] ^= nums[i] ^ nums[i + 1];
        }
        return nums[0];
    }

}
