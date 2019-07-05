/**
 * 994. Rotting Oranges
 *
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 *
 * Example 1:
 *
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 *
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 *
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Note:
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 */
public class RottingOranges {

    /**
     * 考虑边界情况
     * 迭代模拟腐烂传染即可
     */
    // 上 左 下 右
    private int[] dr = new int[]{-1, 0, 1, 0};
    private int[] dc = new int[]{0, -1, 0, 1};
    public int orangesRotting(int[][] grid) {

        int R = grid.length;
        int C = grid[0].length;
        int count = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // record
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] > 0) {
                    count++;
                    if (grid[r][c] == 2) {
                        int position = r * C + c;
                        deque.add(position);
                    }
                }
            }
        }

        // check
        if (count == 0) return 0;
        if (deque.isEmpty()) return -1;
        if (count == deque.size()) return 0;

        int minute = 0;
        while (!deque.isEmpty()) {

            int size = deque.size();
            boolean effective = false;
            for (int i = 0; i < size; i++) {

                int position = deque.remove();
                int r = position / C, c = position % C;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R
                            && 0 <= nc && nc < C
                            && grid[nr][nc] == 1) {

                        effective = true;
                        grid[nr][nc] = 2;
                        int newPosition = nr * C + nc;
                        deque.add(newPosition);
                    }
                }
            }

            if (effective) minute++;
        }

        // check
        for (int[] row : grid) {
            for (int v : row) {
                if (v == 1)  return -1;
            }
        }

        return minute;
    }



    public int orangesRotting2(int[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int R = grid.length;
        int C = grid[0].length;
        int freshCount = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        int mark = 0;
        int oldFresh = freshCount;

        while (freshCount > 0) {

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {

                    if (grid[r][c] == mark + 2) { // 上一轮被传染的
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < R
                                    && 0 <= nc && nc < C
                                    && grid[nr][nc] == 1) {

                                grid[nr][nc] = mark + 3;
                                freshCount--;
                            }
                        }
                    }

                }
            }

            if (freshCount == oldFresh)
                return -1;

            oldFresh = freshCount;
            mark++;
        }

        return mark;
    }
}
