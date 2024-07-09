/**
 * 2684. Maximum Number of Moves in a Grid
 *
 *
 * You are given a 0-indexed m x n matrix grid consisting of positive integers.
 *
 * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
 *
 * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
 * Return the maximum number of moves that you can perform.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * Output: 3
 * Explanation: We can start at the cell (0, 0) and make the following moves:
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * It can be shown that it is the maximum number of moves that can be made.
 *
 *
 * Example 2:
 *
 *
 * Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
 * Output: 0
 * Explanation: Starting from any cell in the first column we cannot perform any moves.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^6
 */
public class MaximumNumberOfMovesInAGrid {

    // ↗ → ↘
    // (i - 1, j + 1)、(i, j + 1) 和 (i + 1, j + 1)
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int[] row : grid) {
            row[0] *= -1; // 入队
        }
        for (int j = 0; j < n - 1; j++) {
            boolean move = false;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] > 0) {
                    continue;
                }
                // k in [k - 1, k + 1]
                for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
                    if (grid[k][j + 1] > -grid[i][j]) {
                        grid[k][j + 1] *= -1;
                        move = true;
                    }
                }
            }
            if (!move) { // 无法再往右走了
                return j;
            }
        }
        return n - 1;
    }

}
