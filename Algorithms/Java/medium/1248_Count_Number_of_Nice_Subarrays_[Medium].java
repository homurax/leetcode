/**
 * 1248. Count Number of Nice Subarrays
 *
 *
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 *
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 *
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
public class CountNumberOfNiceSubarrays {

    private int ans = 0;

    public int numberOfSubarrays(int[] nums, int k) {
        for (int i = 0; i <= nums.length - k; i++) {
            dfs(nums, i, 0, k);
        }
        return ans;
    }

    // TLE
    private void dfs(int[] nums, int cur, int count, int k) {
        if (cur >= nums.length) {
            return;
        }
        if ((nums[cur] & 1) == 1) {
            if (++count > k) {
                return;
            }
        }
        if (count == k) {
            ans++;
        }
        dfs(nums, cur + 1, count, k);
    }


    /**
     *
     * pre[i] 为 [0, i] 中奇数的个数
     * pre[i] = pre[i - 1] + (nums[i] & 1)
     *
     *    pre[i] - pre[j - 1] == k
     * -> pre[j - 1] == pre[i] - k
     *
     * count[] 记录 pre[i] 出现的次数
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        int[] count = new int[nums.length + 1];
        count[0] = 1;
        int oddCount = 0, ans = 0;
        for (int num : nums) {
            oddCount += num & 1;
            count[oddCount]++;
            if (oddCount >= k) {
                ans += count[oddCount - k];
            }
        }
        return ans;
    }

}
