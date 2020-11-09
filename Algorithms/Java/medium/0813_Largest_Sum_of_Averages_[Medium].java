/**
 * 813. Largest Sum of Averages
 *
 *
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
 *
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 */
public class LargestSumOfAverages {

    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[] sum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        double[] dp = new double[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (sum[n] - sum[i]) / (n - i);
        }

        for (int k = 0; k < K - 1; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    dp[i] = Math.max(dp[i], (sum[j] - sum[i]) / (j - i) + dp[j]);
                }
            }
        }
        return dp[0];
    }






    public double largestSumOfAverages2(int[] A, int K) {
        double[][] memo = new double[A.length][K + 1];
        return recurse(A, A.length - 1, K, memo);
    }

    private double recurse(int[] A, int i, int K, double[][] memo) {
        if (memo[i][K] != 0) {
            return memo[i][K];
        }
        if (K == 1) {
            double sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += A[j];
            }
            return sum / (i + 1);
        }
        double max = 0;
        for (int j = i, sum = 0, num = 1; j >= K - 1; j--, num++) {
            sum += A[j];
            double avg = (double) sum / num;
            max = Math.max(max, recurse(A, j - 1, K - 1, memo) + avg);
        }
        memo[i][K] = max;
        return memo[i][K];
    }

}
