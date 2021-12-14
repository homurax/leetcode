/**
 * 934. Shortest Bridge
 *
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 *
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 *
 * Example 2:
 *
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 *
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. n == grid.length == grid[i].length
 * 2. 2 <= n <= 100
 * 3. grid[i][j] is either 0 or 1.
 * 4. There are exactly two islands in grid.
 */
public class ShortestBridge {

    // 先找到一座岛，将它外延伸，直到到达了另一座岛
    private int N;

    public int shortestBridge(int[][] grid) {
        N = grid.length;
        Queue<int[]> queue = getSide(grid);
        int ans = 0;
        int[] x = new int[]{1, -1, 0, 0};
        int[] y = new int[]{0, 0, 1, -1};
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.remove();
                for (int idx = 0; idx < 4; idx++) {
                    int a = curr[0] + x[idx];
                    int b = curr[1] + y[idx];
                    if (inGrid(a, b)) {
                        if (grid[a][b] == 2) {
                            continue;
                        }
                        if (grid[a][b] == 1) {
                            return ans;
                        }
                        queue.add(new int[]{a, b});
                        grid[a][b] = 2;
                    }
                }
            }
        }
        return ans;
    }

    // 找到一个岛的轮廓
    private Queue<int[]> getSide(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    return queue;
                }
            }
        }
        return queue;
    }

    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        if (!inGrid(i, j) || grid[i][j] == 2) {
            return;
        }
        if (grid[i][j] == 0) {
            queue.add(new int[]{i, j});
            return;
        }
        grid[i][j] = 2;
        dfs(grid, i, j - 1, queue);
        dfs(grid, i, j + 1, queue);
        dfs(grid, i - 1, j, queue);
        dfs(grid, i + 1, j, queue);
    }

    private boolean inGrid(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

}
