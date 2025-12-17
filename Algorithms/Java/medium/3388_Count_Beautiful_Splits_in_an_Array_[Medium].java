/**
 * 3388. Count Beautiful Splits in an Array
 *
 *
 * You are given an array nums.
 *
 * A split of an array nums is beautiful if:
 *
 * The array nums is split into three subarrays: nums1, nums2, and nums3, such that nums can be formed by concatenating nums1, nums2, and nums3 in that order.
 * The subarray nums1 is a prefix of nums2 OR nums2 is a prefix of nums3.
 * Return the number of ways you can make this split.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The beautiful splits are:
 *
 * A split with nums1 = [1], nums2 = [1,2], nums3 = [1].
 * A split with nums1 = [1], nums2 = [1], nums3 = [2,1].
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 *
 * Output: 0
 *
 * Explanation:
 *
 * There are 0 beautiful splits.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 50
 */
public class CountBeautifulSplitsInAnArray {

    // LCP
    // lcp[i][j] = s[i:] 和 s[j:] 的最长公共前缀
    public int beautifulSplits(int[] nums) {
        int n = nums.length;
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                if (nums[i] == nums[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i <= j - i && lcp[0][i] >= i || lcp[i][j] >= j - i) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
