/**
 * 2527. Find Xor-Beauty of Array
 *
 *
 * You are given a 0-indexed integer array nums.
 *
 * The effective value of three indices i, j, and k is defined as ((nums[i] | nums[j]) & nums[k]).
 *
 * The xor-beauty of the array is the XORing of the effective values of all the possible triplets of indices (i, j, k) where 0 <= i, j, k < n.
 *
 * Return the xor-beauty of nums.
 *
 * Note that:
 *
 * val1 | val2 is bitwise OR of val1 and val2.
 * val1 & val2 is bitwise AND of val1 and val2.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4]
 * Output: 5
 * Explanation:
 * The triplets and their corresponding effective values are listed below:
 * - (0,0,0) with effective value ((1 | 1) & 1) = 1
 * - (0,0,1) with effective value ((1 | 1) & 4) = 0
 * - (0,1,0) with effective value ((1 | 4) & 1) = 1
 * - (0,1,1) with effective value ((1 | 4) & 4) = 4
 * - (1,0,0) with effective value ((4 | 1) & 1) = 1
 * - (1,0,1) with effective value ((4 | 1) & 4) = 4
 * - (1,1,0) with effective value ((4 | 4) & 1) = 0
 * - (1,1,1) with effective value ((4 | 4) & 4) = 4
 * Xor-beauty of array will be bitwise XOR of all beauties = 1 ^ 0 ^ 1 ^ 4 ^ 1 ^ 4 ^ 0 ^ 4 = 5.
 *
 *
 * Example 2:
 *
 * Input: nums = [15,45,20,2,34,35,5,44,32,30]
 * Output: 34
 * Explanation: The xor-beauty of the given array is 34.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class FindXorBeautyOfArray {

    // 暴力解化简后可以直接得到结论为 nums 异或
    // 位运算每个 bit 位互不相干，可以拆分成每个 bit 位来考虑
    // (a | b) & c = 1 要求 c 必须是 1，a 和 b 不能都是 0
    // 假设有 y 个 1，n - y 个 x
    // (n^2 - x^2)y = (2ny - y^2)y
    // 异或只看奇偶性 -> y^3 的奇偶性
    public int xorBeauty(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans ^= i;
        }
        return ans;
    }

}
