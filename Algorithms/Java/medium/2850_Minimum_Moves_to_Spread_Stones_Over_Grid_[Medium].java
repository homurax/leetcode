/**
 * 2850. Minimum Moves to Spread Stones Over Grid
 *
 *
 * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell. The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
 *
 * In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
 *
 * Return the minimum number of moves required to place one stone in each cell.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
 * Output: 3
 * Explanation: One possible sequence of moves to place one stone in each cell is:
 * 1- Move one stone from cell (2,1) to cell (2,2).
 * 2- Move one stone from cell (2,2) to cell (1,2).
 * 3- Move one stone from cell (1,2) to cell (0,2).
 * In total, it takes 3 moves to place one stone in each cell of the grid.
 * It can be shown that 3 is the minimum number of moves required to place one stone in each cell.
 *
 *
 * Example 2:
 *
 *
 * Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
 * Output: 4
 * Explanation: One possible sequence of moves to place one stone in each cell is:
 * 1- Move one stone from cell (0,1) to cell (0,2).
 * 2- Move one stone from cell (0,1) to cell (1,1).
 * 3- Move one stone from cell (2,2) to cell (1,2).
 * 4- Move one stone from cell (2,2) to cell (2,1).
 * In total, it takes 4 moves to place one stone in each cell of the grid.
 * It can be shown that 4 is the minimum number of moves required to place one stone in each cell.
 *
 *
 * Constraints:
 *
 * grid.length == grid[i].length == 3
 * 0 <= grid[i][j] <= 9
 * Sum of grid is equal to 9.
 */
public class MinimumMovesToSpreadStonesOverGrid {

    // 对 from 全排列, 计算与 to 的曼哈顿距离之和, 取最小的
    public int minimumMoves(int[][] grid) {
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 1; k < grid[i][j]; k++) {
                        from.add(new int[]{i, j});
                    }
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }
        List<List<int[]>> fromPermute = new ArrayList<>();
        permute(from, 0, fromPermute);
        int ans = Integer.MAX_VALUE;
        for (List<int[]> from2 : fromPermute) {
            int sum = 0;
            for (int i = 0; i < from2.size(); i++) {
                int[] f = from2.get(i);
                int[] t = to.get(i);
                sum += Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

    private void permute(List<int[]> arr, int start, List<List<int[]>> result) {
        if (start == arr.size()) {
            result.add(new ArrayList<>(arr));
        }
        for (int i = start; i < arr.size(); i++) {
            Collections.swap(arr, start, i);
            permute(arr, start + 1, result);
            Collections.swap(arr, start, i);
        }
    }


}
