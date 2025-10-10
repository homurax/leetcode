/**
 * 801. Minimum Swaps To Make Sequences Increasing
 *
 *
 * We have two integer sequences A and B of the same non-zero length.
 *
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.
 *
 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 *
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.
 *
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 *
 * Note:
 *
 * 1. A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * 2. A[i], B[i] are integer values in the range [0, 2000].
 */
public class MinimumSwapsToMakeSequencesIncreasing {

    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) { // 各自有序
                // 交换后仍然有序 可以交换可以不交换 取最小
                if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                    dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                    dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
                } else { // 交换后无序
                    // 不交换则上个位置也不能交换
                    dp[i][0] = dp[i - 1][0];
                    // 交换则上个位置也必须交换
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            } else { // 存在无序
                // 不交换则上个位置必须交换
                dp[i][0] = dp[i - 1][1];
                // 交换则上个位置不能交换
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }


    public int minSwap1(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[2][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                    dp[curr][0] = Math.min(dp[prev][0], dp[prev][1]);
                    dp[curr][1] = Math.min(dp[prev][0], dp[prev][1]) + 1;
                } else {
                    dp[curr][0] = dp[prev][0];
                    dp[curr][1] = dp[prev][1] + 1;
                }
            } else {
                dp[curr][0] = dp[prev][1];
                dp[curr][1] = dp[prev][0] + 1;
            }
        }
        return Math.min(dp[1 - (n % 2)][0], dp[1 - (n % 2)][1]);
    }

}
