/**
 * 1139. Largest 1-Bordered Square
 *
 *
 * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 9
 *
 * Example 2:
 *
 * Input: grid = [[1,1,0,0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. 1 <= grid.length <= 100
 * 2. 1 <= grid[0].length <= 100
 * 3. grid[i][j] is 0 or 1
 */
public class Largest1BorderedSquare {

    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dx = new int[m][n];
        int[][] dy = new int[m][n];
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                flag = true;
                dx[i][j] = j > 0 ? dx[i][j - 1] + 1 : 1;
                dy[i][j] = i > 0 ? dy[i - 1][j] + 1 : 1;
            }
        }
        if (!flag) {
            return 0;
        }
        int max = 1;
        for (int i = m - 1; i > 0; i--) {
            for (int j = n - 1; j > 0; j--) {
                int min = Math.min(dx[i][j], dy[i][j]);
                while (min > max) {
                    if (dx[i - min + 1][j] >= min && dy[i][j - min + 1] >= min) {
                        max = min;
                    }
                    min--;
                }
            }
        }
        return max * max;
    }


    // 预算计算行列和 遍历时用于判断边长
    public int largest1BorderedSquare1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dm = new int[m + 1][n + 1];
        int[][] dn = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dm[i][j] = dm[i][j - 1] + grid[i - 1][j - 1];
                dn[i][j] = dn[i - 1][j] + grid[i - 1][j - 1];
            }
        }
        int max = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int j2 = n; j2 >= j; j2--) {
                    int l = j2 - j + 1;
                    if (l < max) {
                        break;
                    }
                    if ((i + j2 - j) >= m + 1) {
                        continue;
                    }
                    if ((dm[i][j2] - dm[i][j - 1]) != l || (dm[i + j2 - j][j2] - dm[i + j2 - j][j - 1]) != l) {
                        continue;
                    }
                    if ((dn[i + j2 - j][j] - dn[i - 1][j]) != l || (dn[i + j2 - j][j2] - dn[i - 1][j2]) != l) {
                        continue;
                    }
                    max = l;
                }
            }
        }
        return max * max;
    }

}
