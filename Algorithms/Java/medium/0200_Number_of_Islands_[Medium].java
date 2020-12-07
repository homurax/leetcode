/**
 * 200. Number of Islands
 *
 *
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 300
 * 4. grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {

    // 1. 标记 每次发现 1 就将整个岛置为 0
    public int numIslands1(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                fill(grid, i, j);
                ans++;
            }
        }
        return ans;
    }

    private void fill(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        fill(grid, x, y - 1);
        fill(grid, x, y + 1);
        fill(grid, x - 1, y);
        fill(grid, x + 1, y);
    }


    // 2. 找到下一个访问位置
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int ans = 0;
        int[] next = findNext(grid, visited, 0, 0);
        while (next[0] != -1 && next[1] != -1) {
            ans++;
            visit(grid, visited, next[0], next[1]);
            next = findNext(grid, visited, next[0], next[1]);
        }
        return ans;
    }

    private int[] findNext(char[][] grid, boolean[][] visited, int x, int y) {
        for (int j = y; j < grid[x].length; j++) {
            if (!visited[x][j] && grid[x][j] == '1') {
                return new int[]{x, j};
            }
        }
        for (int i = x + 1; i < grid.length; i++) {
            for (int j = 0; j < grid[x].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private void visit(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || visited[i][j] || grid[i][j] == '0') {
            return;
        }
        visited[i][j] = true;
        visit(grid, visited, i, j - 1);
        visit(grid, visited, i, j + 1);
        visit(grid, visited, i - 1, j);
        visit(grid, visited, i + 1, j);
    }

}
