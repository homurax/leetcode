/**
 * 1589. Maximum Sum Obtained of Any Permutation
 *
 *
 * We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi]. The ith request asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]. Both starti and endi are 0-indexed.
 *
 * Return the maximum total sum of all requests among all permutations of nums.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
 * Output: 19
 * Explanation: One permutation of nums is [2,1,3,4,5] with the following result:
 * requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
 * requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
 * Total sum: 8 + 3 = 11.
 * A permutation with a higher total sum is [3,5,4,2,1] with the following result:
 * requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
 * requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
 * Total sum: 11 + 8 = 19, which is the best that you can do.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5,6], requests = [[0,1]]
 * Output: 11
 * Explanation: A permutation with the max total sum is [6,5,4,3,2,1] with request sums [11].
 *
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
 * Output: 47
 * Explanation: A permutation with the max total sum is [4,10,5,3,2,1] with request sums [19,18,10].
 *
 *
 * Constraints:
 *
 * 1. n == nums.length
 * 2. 1 <= n <= 10^5
 * 3. 0 <= nums[i] <= 10^5
 * 4. 1 <= requests.length <= 10^5
 * 5. requests[i].length == 2
 * 6. 0 <= starti <= endi < n
 */
public class MaximumSumObtainedOfAnyPermutation {

    /**
     * 贪心
     * 数组 nums 中的越大的数字被查询的次数越多，总和越大
     *
     * 通过差分数组记录查询次数
     * 定义：
     *  counts[i] = a[i], (i = 0)
     *  counts[i] = a[i] - a[i - 1], (i != 0)
     * 维护：
     *  counts[start]++;
     *  counts[end + 1]--, (end + 1 < length);
     *  对差分数组计算前缀和，可得到数组 nums 的每个下标位置被查询的次数
     */
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int length = nums.length;
        int[] counts = new int[length];
        for (int[] request : requests) {
            int start = request[0], end = request[1];
            counts[start]++;
            if (end + 1 < length) {
                counts[end + 1]--;
            }
        }
        for (int i = 1; i < length; i++) {
            counts[i] += counts[i - 1];
        }
        Arrays.sort(nums);
        Arrays.sort(counts);
        long sum = 0;
        for (int i = length - 1; i >= 0 && counts[i] > 0; i--) {
            sum += (long) nums[i] * counts[i];
        }
        return (int) (sum % 1000000007);
    }

}
