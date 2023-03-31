/**
 * 2556. Disconnect Path in a Binary Matrix by at Most One Flip
 *
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col) to any of the cells (row + 1, col) or (row, col + 1) that has the value 1. The matrix is disconnected if there is no path from (0, 0) to (m - 1, n - 1).
 *
 * You can flip the value of at most one (possibly none) cell. You cannot flip the cells (0, 0) and (m - 1, n - 1).
 *
 * Return true if it is possible to make the matrix disconnect or false otherwise.
 *
 * Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,1],[1,0,0],[1,1,1]]
 * Output: true
 * Explanation: We can change the cell shown in the diagram above. There is no path from (0, 0) to (2, 2) in the resulting grid.
 *
 *
 * Example 2:
 *
 *
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: false
 * Explanation: It is not possible to change at most one cell such that there is not path from (0, 0) to (2, 2).
 *
 *
 * Constraints:
 *
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 1000
 * 4. 1 <= m * n <= 10^5
 * 5. grid[i][j] is either 0 or 1.
 * 6. grid[0][0] == grid[m - 1][n - 1] == 1
 */
public class DisconnectPathInABinaryMatrixByAtMostOneFlip {

    private int m, n;
    private int[][] g;

    // 优先先右再向下 与 优先向下再向右
    // 如果两条路径存在交集 则反转交集上任意一点即可
    // 走一种路线中全部修改为 0
    // 另一种路线无法抵达即为 true
    public boolean isPossibleToCutPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        g = grid;
        return !dfs(0, 0) || !dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        g[i][j] = 0;
        return j + 1 < n && g[i][j + 1] == 1 && dfs(i, j + 1)
                || i + 1 < m && g[i + 1][j] == 1 && dfs(i + 1, j);
    }

}
