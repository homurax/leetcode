/**
 * 3514. Number of Unique XOR Triplets II
 *
 *
 * You are given an integer array nums.
 *
 * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
 *
 * Return the number of unique XOR triplet values from all possible triplets (i, j, k).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The possible XOR triplet values are:
 *
 * (0, 0, 0) → 1 XOR 1 XOR 1 = 1
 * (0, 0, 1) → 1 XOR 1 XOR 3 = 3
 * (0, 1, 1) → 1 XOR 3 XOR 3 = 1
 * (1, 1, 1) → 3 XOR 3 XOR 3 = 3
 * The unique XOR values are {1, 3}. Thus, the output is 2.
 *
 *
 * Example 2:
 *
 * Input: nums = [6,7,8,9]
 *
 * Output: 4
 *
 * Explanation:
 *
 * The possible XOR triplet values are {6, 7, 8, 9}. Thus, the output is 4.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1500
 * 1 <= nums[i] <= 1500
 */
public class NumberOfUniqueXORTripletsII {

    // 先算两个的xor 再算三个的
    public int uniqueXorTriplets(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int limit = 1 << (32 - Integer.numberOfLeadingZeros(max));
        boolean[] f1 = new boolean[limit];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                f1[nums[i] ^ nums[j]] = true;
            }
        }
        boolean[] f2 = new boolean[limit];
        for (int xy = 0; xy < limit; xy++) {
            if (!f1[xy]) {
                continue;
            }
            for (int z : nums) {
                f2[xy ^ z] = true;
            }
        }

        int ans = 0;
        for (boolean flag : f2) {
            if (flag) {
                ans++;
            }
        }
        return ans;
    }

}
