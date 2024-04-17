/**
 * 3101. Count Alternating Subarrays
 *
 *
 * You are given a binary array nums.
 *
 * We call a subarray alternating if no two adjacent elements in the subarray have the same value.
 *
 * Return the number of alternating subarrays in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,1,1]
 *
 * Output: 5
 *
 * Explanation:
 *
 * The following subarrays are alternating: [0], [1], [1], [1], and [0,1].
 *
 *
 * Example 2:
 *
 * Input: nums = [1,0,1,0]
 *
 * Output: 10
 *
 * Explanation:
 *
 * Every subarray of the array is alternating. There are 10 possible subarrays that we can choose.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */
public class CountAlternatingSubarrays {

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt = (i > 0 && nums[i] == nums[i - 1]) ? 1 : cnt + 1;
            ans += cnt;
        }
        return ans;
    }

}
