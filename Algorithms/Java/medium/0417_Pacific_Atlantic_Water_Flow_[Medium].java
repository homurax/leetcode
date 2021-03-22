/**
 * 417. Pacific Atlantic Water Flow
 *
 *
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note:
 *
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 *
 *
 * Example:
 *
 * Given the following 5x5 matrix:
 *
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Return:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int[][] matrix, mark;
    private int m, n;

    private boolean inContinent(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private void dfs(int x, int y, int flag) {
        mark[x][y] |= flag;
        for (int i = 0; i < 4; i++) {
            int x1 = x + directions[i][0];
            int y1 = y + directions[i][1];
            if (inContinent(x1, y1) && matrix[x][y] <= matrix[x1][y1] && (mark[x1][y1] & flag) == 0) {
                dfs(x1, y1, flag);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        this.matrix = matrix;
        m = matrix.length;
        if (m == 0) {
            return ans;
        }
        n = matrix[0].length;
        if (n == 0) {
            return ans;
        }
        this.mark = new int[m][n];
        for (int i = 0; i < n; i++) {
            dfs(0, i, 0b01);
            dfs(m - 1, i, 0b10);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, 0, 0b01);
            dfs(i, n - 1, 0b10);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mark[i][j] == 0b11) {
                    List<Integer> node = new ArrayList<>();
                    node.add(i);
                    node.add(j);
                    ans.add(node);
                }
            }
        }
        return ans;
    }

}
