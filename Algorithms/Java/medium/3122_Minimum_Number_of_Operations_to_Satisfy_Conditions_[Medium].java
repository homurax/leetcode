/**
 * 3122. Minimum Number of Operations to Satisfy Conditions
 *
 *
 * You are given a 2D matrix grid of size m x n. In one operation, you can change the value of any cell to any non-negative number. You need to perform some operations such that each cell grid[i][j] is:
 *
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
 * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
 * Return the minimum number of operations needed.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,2],[1,0,2]]
 *
 * Output: 0
 *
 * Explanation:
 *
 *
 *
 * All the cells in the matrix already satisfy the properties.
 *
 *
 * Example 2:
 *
 * Input: grid = [[1,1,1],[0,0,0]]
 *
 * Output: 3
 *
 * Explanation:
 *
 *
 *
 * The matrix becomes [[1,0,1],[1,0,1]] which satisfies the properties, by doing these 3 operations:
 *
 * Change grid[1][0] to 1.
 * Change grid[0][1] to 0.
 * Change grid[1][2] to 1.
 *
 *
 * Example 3:
 *
 * Input: grid = [[1],[2],[3]]
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * There is a single column. We can change the value to 1 in each cell using 2 operations.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 9
 */
public class MinimumNumberOfOperationsToSatisfyConditions {

    // cnt[i][j] = 第 i 列 j 出现次数
    // dfs(i, j) = i+1 列选择 j
    //           = max(dfs(i - 1, k) + cnt[i][k]), k != j
    // 边界 dfs(-1, j) = 0
    public int minimumOperations(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cnt = new int[n][10];
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                cnt[j][row[j]]++;
            }
        }
        int[][] memo = new int[n][11];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return m * n - dfs(n - 1, 10, cnt, memo);
    }

    private int dfs(int i, int j, int[][] cnt, int[][] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        for (int k = 0; k < 10; ++k) {
            if (k != j) {
                res = Math.max(res, dfs(i - 1, k, cnt, memo) + cnt[i][k]);
            }
        }
        return memo[i][j] = res;
    }

    public int minimumOperations1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int f0 = 0, f1 = 0, pre = -1;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[10];
            for (int[] row : grid) {
                cnt[row[i]]++;
            }
            int mx = -1, mx2 = 0, x = -1;
            for (int v = 0; v < 10; v++) {
                int res = (v != pre ? f0 : f1) + cnt[v];
                if (res > mx) {
                    mx2 = mx;
                    mx = res;
                    x = v;
                } else if (res > mx2) {
                    mx2 = res;
                }
            }
            f0 = mx;
            f1 = mx2;
            pre = x;
        }
        return m * n - f0;
    }


}
