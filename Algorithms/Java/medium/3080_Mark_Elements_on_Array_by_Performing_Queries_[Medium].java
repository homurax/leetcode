/**
 * 3080. Mark Elements on Array by Performing Queries
 *
 *
 * You are given a 0-indexed array nums of size n consisting of positive integers.
 *
 * You are also given a 2D array queries of size m where queries[i] = [indexi, ki].
 *
 * Initially all elements of the array are unmarked.
 *
 * You need to apply m queries on the array in order, where on the ith query you do the following:
 *
 * Mark the element at index indexi if it is not already marked.
 * Then mark ki unmarked elements in the array with the smallest values. If multiple such elements exist, mark the ones with the smallest indices. And if less than ki unmarked elements exist, then mark all of them.
 * Return an array answer of size m where answer[i] is the sum of unmarked elements in the array after the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]
 *
 * Output: [8,3,0]
 *
 * Explanation:
 *
 * We do the following queries on the array:
 *
 * Mark the element at index 1, and 2 of the smallest unmarked elements with the smallest indices if they exist, the marked elements now are nums = [1,2,2,1,2,3,1]. The sum of unmarked elements is 2 + 2 + 3 + 1 = 8.
 * Mark the element at index 3, since it is already marked we skip it. Then we mark 3 of the smallest unmarked elements with the smallest indices, the marked elements now are nums = [1,2,2,1,2,3,1]. The sum of unmarked elements is 3.
 * Mark the element at index 4, since it is already marked we skip it. Then we mark 2 of the smallest unmarked elements with the smallest indices if they exist, the marked elements now are nums = [1,2,2,1,2,3,1]. The sum of unmarked elements is 0.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,4,2,3], queries = [[0,1]]
 *
 * Output: [7]
 *
 * Explanation: We do one query which is mark the element at index 0 and mark the smallest element among unmarked elements. The marked elements will be nums = [1,4,2,3], and the sum of unmarked elements is 4 + 3 = 7.
 *
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == queries.length
 * 1 <= m <= n <= 10^5
 * 1 <= nums[i] <= 10^5
 * queries[i].length == 2
 * 0 <= indexi, ki <= n - 1
 */
public class MarkElementsOnArrayByPerformingQueries {

    // 优先标记较小元素 注意使用稳定排序
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        long sum = 0;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            ids[i] = i;
        }
        Arrays.sort(ids, Comparator.comparingInt(i -> nums[i]));
        long[] ans = new long[m];
        for (int qi = 0, j = 0; qi < m; qi++) {
            int[] query = queries[qi];
            int idx = query[0];
            int k = query[1];
            // 如果之前已经标记过就是 0，没有影响
            sum -= nums[idx];
            nums[idx] = 0;
            for (; j < n && k > 0; j++) {
                int i = ids[j];
                if (nums[i] > 0) {
                    sum -= nums[i];
                    nums[i] = 0;
                    k--;
                }
            }
            ans[qi] = sum;
        }
        return ans;
    }

}
