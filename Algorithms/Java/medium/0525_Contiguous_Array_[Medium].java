/**
 * 525. Contiguous Array
 *
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 *
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArray {

    // count 相等的 index 之间零一数量相同
    public int findMaxLength(int[] nums) {
        int[] mark = new int[nums.length * 2 + 1];
        Arrays.fill(mark, -2);
        mark[nums.length] = -1;
        int ans = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 0 ? -1 : 1;
            if (mark[count + nums.length] >= -1) {
                ans = Math.max(ans, i - mark[count + nums.length]);
            } else {
                mark[count + nums.length] = i;
            }
        }
        return ans;
    }

}
