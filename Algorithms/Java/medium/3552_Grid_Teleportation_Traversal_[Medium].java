/**
 * 3552. Grid Teleportation Traversal
 *
 *
 * You are given a 2D character grid matrix of size m x n, represented as an array of strings, where matrix[i][j] represents the cell at the intersection of the ith row and jth column. Each cell is one of the following:
 *
 * '.' representing an empty cell.
 * '#' representing an obstacle.
 * An uppercase letter ('A'-'Z') representing a teleportation portal.
 * You start at the top-left cell (0, 0), and your goal is to reach the bottom-right cell (m - 1, n - 1). You can move from the current cell to any adjacent cell (up, down, left, right) as long as the destination cell is within the grid bounds and is not an obstacle.
 *
 * If you step on a cell containing a portal letter and you haven't used that portal letter before, you may instantly teleport to any other cell in the grid with the same letter. This teleportation does not count as a move, but each portal letter can be used at most once during your journey.
 *
 * Return the minimum number of moves required to reach the bottom-right cell. If it is not possible to reach the destination, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = ["A..",".A.","..."]
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * Before the first move, teleport from (0, 0) to (1, 1).
 * In the first move, move from (1, 1) to (1, 2).
 * In the second move, move from (1, 2) to (2, 2).
 *
 *
 * Example 2:
 *
 * Input: matrix = [".#...",".#.#.",".#.#.","...#."]
 *
 * Output: 13
 *
 * Explanation:
 *
 *
 *
 *
 *
 * Constraints:
 *
 * 1 <= m == matrix.length <= 10^3
 * 1 <= n == matrix[i].length <= 10^3
 * matrix[i][j] is either '#', '.', or an uppercase English letter.
 * matrix[0][0] is not an obstacle.
 */
public class GridTeleportationTraversal {

    // 相同字母的边权为 0
    // 非字母格子 BFS 遍历
    // 字母格子 BFS + 所有相同字母
    private static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int minMoves(String[] matrix) {
        int m = matrix.length;
        int n = matrix[0].length();
        if (matrix[m - 1].charAt(n - 1) == '#') {
            return -1;
        }
        // 记录所有的传送门
        List<int[]>[] pos = new ArrayList[26];
        Arrays.setAll(pos, i -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i].charAt(j);
                if (Character.isUpperCase(c)) {
                    pos[c - 'A'].add(new int[]{i, j});
                }
            }
        }

        int[][] dis = new int[m][n];
        for (int[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dis[0][0] = 0;

        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] p = q.pollFirst();
            int x = p[0], y = p[1];
            int d = dis[x][y];
            if (x == m - 1 && y == n - 1) {
                return d;
            }
            char c = matrix[x].charAt(y);
            if (c != '.') { // 能访问到一定不是 #, 不是 . 就是传送门
                for (int[] portal : pos[c - 'A']) {
                    int px = portal[0], py = portal[1];
                    if (d < dis[px][py]) { // 开销更小的新位置
                        dis[px][py] = d;
                        q.addFirst(new int[]{px, py});
                    }
                }
                pos[c - 'A'].clear(); // 避免重复使用
            }
            // BFS
            for (int[] dir : DIRS) {
                int nx = x + dir[0], ny = y + dir[1];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && matrix[nx].charAt(ny) != '#' && d + 1 < dis[nx][ny]) {
                    dis[nx][ny] = d + 1;
                    q.addLast(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }

}
