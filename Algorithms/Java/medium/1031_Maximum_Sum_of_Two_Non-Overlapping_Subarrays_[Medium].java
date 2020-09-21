/**
 * 1031. Maximum Sum of Two Non-Overlapping Subarrays
 *
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 *
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 *
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *
 *
 * Example 1:
 *
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 * Example 2:
 *
 * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 * Example 3:
 *
 * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 *
 *
 * Note:
 *
 * L >= 1
 * M >= 1
 * L + M <= A.length <= 1000
 * 0 <= A[i] <= 1000
 */
public class MaximumSumOfTwoNonOverlappingSubarrays {

    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[][] dp = new int[A.length + 1][4];
        int preSum = 0;
        for (int i = 0; i < L; i++) {
            preSum += A[i];
        }
        int maxSum = preSum;
        dp[L - 1][0] = maxSum;
        for (int i = L; i < A.length; i++) {
            preSum -= A[i - L];
            preSum += A[i];
            maxSum = Math.max(maxSum, preSum);
            dp[i][0] = maxSum;
        }

        preSum = 0;
        for (int i = 0; i < M; i++) {
            preSum += A[i];
        }
        maxSum = preSum;
        dp[M - 1][1] = maxSum;
        for (int i = M; i < A.length; i++) {
            preSum -= A[i - M];
            preSum += A[i];
            maxSum = Math.max(maxSum, preSum);
            dp[i][1] = maxSum;
        }

        preSum = 0;
        for (int i = A.length - 1; i >= A.length - L; i--) {
            preSum += A[i];
        }
        maxSum = preSum;
        dp[A.length - L][2] = maxSum;
        for (int i = A.length - L - 1; i >= 0; i--) {
            preSum -= A[i + L];
            preSum += A[i];
            maxSum = Math.max(maxSum, preSum);
            dp[i][2] = maxSum;
        }

        preSum = 0;
        for (int i = A.length - 1; i >= A.length - M; i--) {
            preSum += A[i];
        }
        maxSum = preSum;
        dp[A.length - M][3] = maxSum;
        for (int i = A.length - M - 1; i >= 0; i--) {
            preSum -= A[i + M];
            preSum += A[i];
            maxSum = Math.max(maxSum, preSum);
            dp[i][3] = maxSum;
        }

        int ans = 0;
        // L M
        for (int i = L; i <= A.length - M; i++) {
            ans = Math.max(ans, dp[i - 1][0] + dp[i][3]);
        }
        // M L
        for (int i = M; i <= A.length - L; i++) {
            ans = Math.max(ans, dp[i - 1][1] + dp[i][2]);
        }
        return ans;
    }


    public int maxSumTwoNoOverlap2(int[] A, int L, int M) {
        int[] preSum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        return Math.max(maxSum(preSum, L, M), maxSum(preSum, M, L));
    }

    private int maxSum(int[] preSum, int L, int M) {
        int ans = 0, maxL = 0;
        for (int i = L + M; i < preSum.length; i++) {
            maxL = Math.max(maxL, preSum[i - M] - preSum[i - M - L]);
            ans = Math.max(ans, maxL + preSum[i] - preSum[i - M]);
        }
        return ans;
    }

}
