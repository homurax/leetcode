/**
 * 1775. Equal Sum Arrays With Minimum Number of Operations
 *
 *
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values in the arrays are between 1 and 6, inclusive.
 *
 * In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.
 *
 * Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2. Return -1​​​​​ if it is not possible to make the sum of the two arrays equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
 * - Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
 * - Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
 *
 * Example 2:
 *
 * Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * Output: -1
 * Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
 *
 * Example 3:
 *
 * Input: nums1 = [6,6], nums2 = [1]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
 * - Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
 * - Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums1.length, nums2.length <= 10^5
 * 2. 1 <= nums1[i], nums2[i] <= 6
 */
public class EqualSumArraysWithMinimumNumberOfOperations {

    // 贪心
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            sum1 += num;
        }
        for (int num : nums2) {
            sum2 += num;
        }
        if (sum1 == sum2) {
            return 0;
        }
        if (sum1 > sum2) {
            return minOperations(nums2, nums1);
        }
        int diff = sum2 - sum1;
        int[] freq = new int[6];
        for (int num : nums1) {
            freq[6 - num]++;
        }
        for (int num : nums2) {
            freq[num - 1]++;
        }
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; i--) {
            while (freq[i] > 0 && diff > 0) {
                freq[i]--;
                ans++;
                diff -= i;
            }
        }
        return diff > 0 ? -1 : ans;
    }

}
