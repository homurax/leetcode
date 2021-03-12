/**
 * 1536. Minimum Swaps to Arrange a Binary Grid
 *
 *
 * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
 *
 * A grid is said to be valid if all the cells above the main diagonal are zeros.
 *
 * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
 *
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
 * Output: 3
 *
 * Example 2:
 *
 *
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * Output: -1
 * Explanation: All rows are similar, swaps have no effect on the grid.
 *
 * Example 3:
 *
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. n == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= n <= 200
 * 4。 grid[i][j] is 0 or 1
 */
public class MinimumSwapsToArrangeABinaryGrid {


    /**
     * 第 i 行 index [i + 1, n - 1] 应当为 0;    0 <= i <= n - 1
     * 第 i 行 存在多个满足的行时 贪心可选择最近的 越靠后限制越宽松
     */
    public int minSwaps(int[][] grid) {

        int n = grid.length;
        int[] position = new int[n];
        Arrays.fill(position, -1);
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    position[i] = j;
                    break;
                }
            }
        }
        int ans = 0;
        for (int currRow = 0; currRow < n; currRow++) {
            int k = -1;
            for (int switchRow = currRow; switchRow < n; switchRow++) {
                if (position[switchRow] <= currRow) {
                    ans += switchRow - currRow;
                    k = switchRow;
                    break;
                }
            }
            if (k >= 0) { // 更新 position
                for (int j = k; j > currRow; j--) {
                    int temp = position[j];
                    position[j] = position[j - 1];
                    position[j - 1] = temp;
                }
            } else {
                return -1;
            }
        }
        return ans;
    }


}
