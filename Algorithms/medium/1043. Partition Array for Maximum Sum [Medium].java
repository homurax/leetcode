/**
 * 1043. Partition Array for Maximum Sum
 *
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 *
 *
 * Example 1:
 *
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 *
 *
 * Note:
 *
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 */
public class PartitionArrayForMaximumSum {


    /**
     * 动态规划
     * 最后一个区间的最大值为max 长度为len 区间范围为[i - len + 1, i]
     */
    public int maxSumAfterPartitioning(int[] A, int K) {

        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int max = A[i];
            for (int len = 1; len <= K && i - len + 1 >= 0; len++) {
                max = Math.max(max, A[i - len + 1]);
                if (i >= len) {
                    ans[i] = Math.max(ans[i], ans[i - len] + max * len);
                } else {
                    ans[i] = Math.max(ans[i], max * len);
                }
            }
        }
        return ans[ans.length - 1];
    }


}
