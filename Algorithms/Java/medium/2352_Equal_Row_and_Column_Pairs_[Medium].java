/**
 * 2352. Equal Row and Column Pairs
 *
 *
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 *
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 *
 *
 * Example 2:
 *
 *
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 *
 *
 * Constraints:
 *
 * 1. n == grid.length == grid[i].length
 * 2. 1 <= n <= 200
 * 3. 1 <= grid[i][j] <= 10^5
 */
public class EqualRowAndColumnPairs {

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> cnt = new HashMap<>();
        for (int[] row : grid) {
            cnt.merge(Arrays.toString(row), 1, Integer::sum);
        }
        int ans = 0;
        int[] col = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                col[j] = grid[j][i];
            }
            String key = Arrays.toString(col);
            if (cnt.containsKey(key)) {
                ans += cnt.get(key);
            }
        }
        return ans;
    }

}
