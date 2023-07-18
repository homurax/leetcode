/**
 * 1706. Where Will the Ball Fall
 *
 *
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * Output: [1,-1,-1,-1,-1]
 * Explanation: This example is shown in the photo.
 * Ball b0 is dropped at column 0 and falls out of the box at column 1.
 * Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
 * Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
 *
 *
 * Example 2:
 *
 * Input: grid = [[-1]]
 * Output: [-1]
 * Explanation: The ball gets stuck against the left wall.
 *
 *
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * Output: [0,1,2,3,4,-1]
 *
 *
 * Constraints:
 *
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 100
 * 4. grid[i][j] is 1 or -1.
 */
public class WhereWillTheBallFall {


    private int[][] grid;
    private int m, n;

    public int[] findBall(int[][] _grid) {
        grid = _grid;
        m = grid.length;
        n = grid[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = dfs(0, i);
        }
        return ans;
    }

    private int getVal(int c) {
        int i = 0, j = c;
        while (i < m) {
            int next = j + grid[i][j];
            if (next < 0 || next >= n) {
                return -1;
            }
            if (grid[i][j] != grid[i][next]) {
                return -1;
            }
            i++;
            j = next;
        }
        return j;
    }

    private int dfs(int i, int j) {
        if (i == m) {
            return j;
        }
        if (grid[i][j] == 1) {
            // 向右但是右侧是边界或者右侧是向左形成V
            if (j == n - 1 || grid[i][j + 1] == -1) {
                return -1;
            }
            return dfs(i + 1, j + 1);
        } else {
            // 向左但是左侧是边界或者左侧是向右形成V
            if (j == 0 || grid[i][j - 1] == 1) {
                return -1;
            }
            return dfs(i + 1, j - 1);
        }
    }

}
