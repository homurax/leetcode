/**
 * 1391. Check if There is a Valid Path in a Grid
 *
 *
 * Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
 * 1 which means a street connecting the left cell and the right cell.
 * 2 which means a street connecting the upper cell and the lower cell.
 * 3 which means a street connecting the left cell and the lower cell.
 * 4 which means a street connecting the right cell and the lower cell.
 * 5 which means a street connecting the left cell and the upper cell.
 * 6 which means a street connecting the right cell and the upper cell.
 *
 *
 * You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
 *
 * Notice that you are not allowed to change any street.
 *
 * Return true if there is a valid path in the grid or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,4,3],[6,5,2]]
 * Output: true
 * Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
 *
 * Example 2:
 *
 *
 * Input: grid = [[1,2,1],[1,2,1]]
 * Output: false
 * Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
 *
 * Example 3:
 *
 * Input: grid = [[1,1,2]]
 * Output: false
 * Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
 *
 * Example 4:
 *
 * Input: grid = [[1,1,1,1,1,1,3]]
 * Output: true
 * Example 5:
 *
 * Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 300
 * 4. 1 <= grid[i][j] <= 6
 */
public class CheckIfThereIsAValidPathInAGrid {

    public boolean hasValidPath1(int[][] grid) {
        return travel(grid, new boolean[grid.length][grid[0].length], 0, 0);
    }

    // - | ┐ ┌ ┘ └
    // 1 2 3 4 5 6
    // 向上走只可选 -> 2, 3, 4
    // 向下走只可选 -> 2, 5, 6
    // 向左走只可选 -> 1, 4, 6
    // 向右走只可选 -> 1, 3, 5
    // 1 考虑 -> 左 右
    // 2 考虑 -> 上 下
    // 3 考虑 -> 左 下
    // 4 考虑 -> 右 下
    // 5 考虑 -> 左 上
    // 6 考虑 -> 右 上
    private boolean travel(int[][] grid, boolean[][] visited, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }
        if (visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        boolean flag1, flag2;
        if (grid[i][j] == 1) {
            flag1 = checkLeft(grid, visited, i, j);
            flag2 = checkRight(grid, visited, i, j);
        }
        else if (grid[i][j] == 2) {
            flag1 = checkUp(grid, visited, i, j);
            flag2 = checkDown(grid, visited, i, j);
        }
        else if (grid[i][j] == 3) {
            flag1 = checkLeft(grid, visited, i, j);
            flag2 = checkDown(grid, visited, i, j);
        }
        else if (grid[i][j] == 4) {
            flag1 = checkRight(grid, visited, i, j);
            flag2 = checkDown(grid, visited, i, j);
        }
        else if (grid[i][j] == 5) {
            flag1 = checkLeft(grid, visited, i, j);
            flag2 = checkUp(grid, visited, i, j);
        }
        else {
            flag1 = checkRight(grid, visited, i, j);
            flag2 = checkUp(grid, visited, i, j);
        }
        visited[i][j] = false;
        return flag1 || flag2;
    }

    private boolean checkUp(int[][] grid, boolean[][] visited, int i, int j) {
        if (i - 1 >= 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
            return travel(grid, visited, i - 1, j);
        }
        return false;
    }

    private boolean checkDown(int[][] grid, boolean[][] visited, int i, int j) {
        if (i + 1 < grid.length && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
            return travel(grid, visited, i + 1, j);
        }
        return false;
    }

    private boolean checkLeft(int[][] grid, boolean[][] visited, int i, int j) {
        if (j - 1 >= 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
            return travel(grid, visited, i, j - 1);
        }
        return false;
    }

    private boolean checkRight(int[][] grid, boolean[][] visited, int i, int j) {
        if (j + 1 < grid[0].length && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
            return travel(grid, visited, i, j + 1);
        }
        return false;
    }


    /**
     * 静态连通性问题
     *
     * 1. 通过 UF 算法 最后判断两点是否在一个分组
     *
     * 2. 基于方向构图
     *
     *      0
     *      ^
     *      |
     * 3 <-- --> 1
     *      |
     *      v
     *      2
     *
     *
     * 如果某一个单元格有第 i 个方向，那么它在第 i 个方向相邻的单元格必须有第 (i+2)%4 个方向。
     */


    private int M, N;

    public boolean hasValidPath(int[][] grid) {
        this.M = grid.length;
        this.N = grid[0].length;
        if (grid[0][0] == 4) {
            for (int[] a : new int[][]{{1, 0, 1}, {0, 1, 2}}) {
                boolean[][] visited = new boolean[M][N];
                visited[0][0] = true;
                if (travel(a[0], a[1], a[2], grid, visited)) {
                    return true;
                }
            }
            return false;
        }
        return travel(0, 0, 0, grid, new boolean[M][N]);
    }

    public boolean travel(int i, int j, int last, int[][] grid, boolean[][] visited) {
        while (i >= 0 && i < M && j >= 0 && j < N && !visited[i][j]) {
            visited[i][j] = true;
            int cell = grid[i][j];
            if (cell == 1 && (last % 2 == 0)) {
                if (last == 0 || last == 2) {
                    j++;
                    last = 2;
                } else {
                    j--;
                }
            } else if (cell == 2 && (last == 0 || last % 2 == 1)) {
                if (last == 0 || last == 1) {
                    i++;
                    last = 1;
                } else {
                    i--;
                }
            } else if (cell == 3 && (last == 0 || last == 2 || last == 3)) {
                if (last == 0 || last == 2) {
                    i++;
                    last = 1;
                } else {
                    j--;
                    last = 4;
                }
            } else if (cell == 4 && (last == 4 || last == 3)) {
                if (last == 3) {
                    j++;
                    last = 2;
                } else {
                    i++;
                    last = 1;
                }
            } else if (cell == 5 && (last == 0 || last == 2 || last == 1)) {
                if (last == 0 || last == 2) {
                    i--;
                    last = 3;
                } else {
                    j--;
                    last = 4;
                }
            } else if (cell == 6 && (last == 0 || last == 1 || last == 4)) {
                if (last == 0 || last == 1) {
                    j++;
                    last = 2;
                } else {
                    i--;
                    last = 3;
                }
            } else {
                visited[i][j] = false;
                break;
            }
        }
        return visited[M - 1][N - 1];
    }
}
