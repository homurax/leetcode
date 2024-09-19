/**
 * 3290. Maximum Multiplication Score
 *
 *
 * You are given an integer array a of size 4 and another integer array b of size at least 4.
 *
 * You need to choose 4 indices i0, i1, i2, and i3 from the array b such that i0 < i1 < i2 < i3. Your score will be equal to the value a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3].
 *
 * Return the maximum score you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]
 *
 * Output: 26
 *
 * Explanation:
 * We can choose the indices 0, 1, 2, and 5. The score will be 3 * 2 + 2 * (-6) + 5 * 4 + 6 * 2 = 26.
 *
 *
 * Example 2:
 *
 * Input: a = [-1,4,5,-2], b = [-5,-1,-3,-2,-4]
 *
 * Output: -1
 *
 * Explanation:
 * We can choose the indices 0, 1, 3, and 4. The score will be (-1) * (-5) + 4 * (-1) + 5 * (-2) + (-2) * (-4) = -1.
 *
 *
 *
 * Constraints:
 *
 * a.length == 4
 * 4 <= b.length <= 10^5
 * -105 <= a[i], b[i] <= 10^5
 */
public class MaximumMultiplicationScore {

    // dfs(i, j) 代表 b[0] ~ b[i] 选择 j+1 个数字，计算 a[0] ~ a[j] 的乘积合，j 最大为 3
    // 不选 i -> dfs(i - 1, j)
    // 选择 i -> dfs(i - 1, j - 1) + a[j] * b[i]
    // dfs(i, j) = max(dfs(i - 1, j), dfs(i - 1, j - 1) + a[j] * b[i])
    // 边界 dfs(i, −1) = 0, dfs(−1, j ≥ 0) = −∞
    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long[][] memo = new long[n][4];
        for (long[] row : memo) {
            Arrays.fill(row, Long.MIN_VALUE);
        }
        return dfs(n - 1, 3, a, b, memo);
    }

    private long dfs(int i, int j, int[] a, int[] b, long[][] memo) {
        if (j < 0) {
            return 0;
        }
        if (i < 0) { // j >= 0
            return Long.MIN_VALUE / 2; // 防溢出
        }
        if (memo[i][j] == Long.MIN_VALUE) {
            memo[i][j] = Math.max(dfs(i - 1, j, a, b, memo), dfs(i - 1, j - 1, a, b, memo) + (long) a[j] * b[i]);
        }
        return memo[i][j];
    }

    // f[i + 1][j + 1] 的定义和 dfs(i, j) 相同
    // 方便边界处理
    public long maxScore1(int[] a, int[] b) {
        int n = b.length;
        long[][] f = new long[n + 1][5];
        Arrays.fill(f[0], 1, 5, Long.MIN_VALUE / 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                f[i + 1][j + 1] = Math.max(f[i][j + 1], f[i][j] + (long) a[j] * b[i]);
            }
        }
        return f[n][4];
    }

    // f[i + 1] 和 f[i] 保存到同一个数组中
    public long maxScore2(int[] a, int[] b) {
        long[] f = new long[5];
        Arrays.fill(f, 1, 5, Long.MIN_VALUE / 2);
        for (int v : b) {
            for (int j = 3; j >= 0; j--) {
                f[j + 1] = Math.max(f[j + 1], f[j] + (long) a[j] * v);
            }
        }
        return f[4];
    }

}
