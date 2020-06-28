/**
 * 1351. Count Negative Numbers in a Sorted Matrix
 *
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 *
 * Return the number of negative numbers in grid.
 *
 * Example 1:
 *
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 *
 * Example 2:
 *
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 *
 * Example 3:
 *
 * Input: grid = [[1,-1],[-1,-1]]
 * Output: 3
 *
 * Example 4:
 *
 * Input: grid = [[-1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 */
public class CountNegativeNumbersInASortedMatrix {

    public int countNegatives(int[][] grid) {

        int ans = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            int[] arr = grid[i];
            if (arr[0] < 0) {
                ans += (rows - i) * cols;
                break;
            } else {
                for (int j = 0; j < cols; j++) {
                    if (arr[j] < 0) {
                        ans += cols - j;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public int countNegatives2(int[][] grid) {

        int ans = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int j = 0;
        for (int i = cols - 1; i >= 0; i--) {
            while (j < rows && grid[j][i] >= 0) {
                j++;
            }
            if (j == rows) {
                break;
            }
            ans += rows - j;
        }
        return ans;
    }
}
