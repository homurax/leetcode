/**
 * 3286. Find a Safe Walk Through a Grid
 *
 *
 * You are given an m x n binary matrix grid and an integer health.
 *
 * You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
 *
 * You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.
 *
 * Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
 *
 * Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
 *
 * Output: true
 *
 * Explanation:
 *
 * The final cell can be reached safely by walking along the gray cells below.
 *
 *
 * Example 2:
 *
 * Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3
 *
 * Output: false
 *
 * Explanation:
 *
 * A minimum of 4 health points is needed to reach the final cell safely.
 *
 *
 * Example 3:
 *
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
 *
 * Output: true
 *
 * Explanation:
 *
 * The final cell can be reached safely by walking along the gray cells below.
 *
 *
 *
 * Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.
 *
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 2 <= m * n
 * 1 <= health <= m + n
 * grid[i][j] is either 0 or 1.
 */
public class FindASafeWalkThroughAGrid {

    // Dijkstra
    // 最短路径 < health 即可
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.getFirst().size();
        Integer[][] a = new Integer[m][];
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            a[i] = grid.get(i).toArray(Integer[]::new);
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[0][0] = a[0][0];
        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] p = q.pollFirst();
            int i = p[0];
            int j = p[1];
            for (int[] d : DIRS) {
                int x = i + d[0];
                int y = j + d[1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    int cost = a[x][y];
                    if (dis[i][j] + cost < dis[x][y]) {
                        dis[x][y] = dis[i][j] + cost;
                        if (cost == 0) {
                            q.addFirst(new int[]{x, y});
                        } else {
                            q.addLast(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return dis[m - 1][n - 1] < health;
    }

}
