/**
 * 1020. Number of Enclaves
 *
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 *
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 *
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 *
 * Example 2:
 *
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 */
public class NumberOfEnclaves {


    public int numEnclaves(int[][] A) {
        int len = A.length;
        for (int i = 0; i < A[0].length; i++) {
            zero(A, 0, i);
            zero(A, len - 1, i);
        }
        for (int i = 0; i < len; i++) {
            zero(A, i, 0);
            zero(A, i, A[i].length - 1);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < A[i].length; j++) {
                ans += A[i][j];
            }
        }
        return ans;
    }

    private void zero(int[][] A, int x, int y) {
        if (x < 0 || x >= A.length || y < 0 || y >= A[x].length || A[x][y] == 0)
            return;
        A[x][y] = 0;
        zero(A, x, y + 1);
        zero(A, x, y - 1);
        zero(A, x + 1, y);
        zero(A, x - 1, y);
    }

}
