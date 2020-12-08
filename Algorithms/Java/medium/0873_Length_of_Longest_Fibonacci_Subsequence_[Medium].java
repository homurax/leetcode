/**
 * 873. Length of Longest Fibonacci Subsequence
 *
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 *
 * n >= 3
 * X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 * Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.
 *
 * (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation:
 * The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 *
 * Example 2:
 *
 * Input: [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation:
 * The longest subsequence that is fibonacci-like:
 * [1,11,12], [3,11,14] or [7,11,18].
 *
 *
 * Note:
 *
 * 1. 3 <= A.length <= 1000
 * 2. 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 * 3. (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 */
public class LengthOfLongestFibonacciSubsequence {

    // max <= 10^9 最多 43 项 可以用 Set
    public int lenLongestFibSubseq1(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int x : A) {
            set.add(x);
        }
        int ans = 0;
        for (int i = 0; i < A.length; i++)
            for (int j = i + 1; j < A.length; j++) {
                int x = A[j], y = A[i] + A[j];
                int count = 2;
                while (set.contains(y)) {
                    int temp = y;
                    y += x;
                    x = temp;
                    ans = Math.max(ans, ++count);
                }
            }
        return ans >= 3 ? ans : 0;
    }

    // map[(i, j)] = map[i * n + j] = count, (count >= 2, (i, j) 时序列长度)
    // 如果有 (i, j) -> (j, k), 则 map[(j, k)] = map[(i, j)] + 1
    public int lenLongestFibSubseq2(int[] A) {
        int n = A.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(A[i], i);
        }
        int ans = 0;
        Map<Integer, Integer> longest = new HashMap<>();
        // A[i] + A[j] = A[k], (i < j < k)
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < k; j++) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // hash(i, j) = i * n + j
                    int count = longest.getOrDefault(i * n + j, 2) + 1;
                    longest.put(j * n + k, count);
                    ans = Math.max(ans, count);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int lenLongestFibSubseq(int[] A) {
        int ans = 0;
        int[][] dp = new int[A.length][A.length];
        // i - j - k
        for (int k = 2; k < A.length; k++) {
            int i = 0, j = k - 1;
            int c = A[k];
            while (i < j) {
                int sum = A[i] + A[j];
                if (sum > c) {
                    j--;
                } else if (sum < c) {
                    i++;
                } else {
                    ans = Math.max(ans, dp[j][k] = (dp[i][j] == 0 ? 2 : dp[i][j]) + 1);
                    i++;
                }
            }
        }
        return ans;
    }

}
