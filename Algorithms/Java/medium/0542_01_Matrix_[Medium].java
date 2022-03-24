/**
 * 542. 01 Matrix
 *
 * x…… n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 *
 * Constraints:
 *
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n <= 10^4
 * 4. 1 <= m * n <= 10^4
 * 5. mat[i][j] is either 0 or 1.
 * 6. There is at least one 0 in mat.
 */
public class Matrix01 {

    // dp
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    ans[i][j] = m * n;
                }
            }
        }
        // 向左向上
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    ans[i][j] = Math.min(ans[i][j], ans[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    ans[i][j] = Math.min(ans[i][j], ans[i][j - 1] + 1);
                }
            }
        }
        // 向右向下
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 < m) {
                    ans[i][j] = Math.min(ans[i][j], ans[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    ans[i][j] = Math.min(ans[i][j], ans[i][j + 1] + 1);
                }
            }
        }
        return ans;
    }

    public int[][] updateMatrix1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] visited = new boolean[m * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i * n + j] = true;
                }
            }
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] ans = new int[m][n];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int i = poll[0], j = poll[1];
            for (int[] dir : dirs) {
                int i1 = i + dir[0], j1 = j + dir[1];
                if (0 <= i1 && i1 < m && 0 <= j1 && j1 < n && !visited[i1 * n + j1]) {
                    ans[i1][j1] = ans[i][j] + 1;
                    queue.offer(new int[]{i1, j1});
                    visited[i1 * n + j1] = true;
                }
            }
        }
        return ans;
    }

}
