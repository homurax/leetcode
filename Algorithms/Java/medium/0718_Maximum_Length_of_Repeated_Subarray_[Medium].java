/**
 * 718. Maximum Length of Repeated Subarray
 *
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 *
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 *
 *
 * Note:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class MaximumLengthOfRepeatedSubarray {

    // 1. 动态规划
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int ans = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    // 2. 滑动窗口
    public int findLength2(int[] A, int[] B) {
        int ans = 0;
        // A不动 B向右拖动
        for (int i = 0; i < A.length; i++) {
            int len = Math.min(B.length, A.length - i);
            int maxLength = maxLength(A, B, i, 0, len);
            ans = Math.max(ans, maxLength);
        }
        // A不动 B向左拖动
        for (int i = 0; i < B.length; i++) {
            int len = Math.min(A.length, B.length - i);
            int maxLength = maxLength(A, B, 0, i, len);
            ans = Math.max(ans, maxLength);
        }
        return ans;
    }

    private int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int result = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            result = Math.max(result, k);
        }
        return result;
    }

}
