/**
 * 1162. As Far from Land as Possible
 *
 *
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
 *
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
 *
 *
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */
public class AsFarFromLandAsPossible {


    // 多源最短路径
    // 从「多个源点」到达「一个/多个汇点」的最短路径
    // 建立虚拟「超级源点」的方式，将「多源 BFS」转换回「单源 BFS」
    // 将源点和汇点反转
    // 从每个「陆地」区域出发，多个「陆地」区域每次同时向往扩散一圈，每个「海洋」区域被首次覆盖时所对应的圈数，就是「海洋」区域距离最近的「陆地」区域的距离
    // 存在一个「虚拟源点」，其与所有「真实源点」（陆地）存在等权的边，那么任意「海洋」区域与「最近的陆地」区域的最短路等价于与「虚拟源点」的最短路径
    public int maxDistance(int[][] _grid) {
        int n = grid.length;
        Deque<int[]> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    deque.add(new int[]{i, j});
                    map.put(i * n + j, 0);
                }
            }
        }
        int ans = -1;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!deque.isEmpty()) {
            int[] poll = deque.poll();
            int dx = poll[0], dy = poll[1];
            int step = map.get(dx * n + dy);
            for (int[] dir : dirs) {
                int nx = dx + dir[0], ny = dy + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (grid[nx][ny] != 0) {
                    continue;
                }
                grid[nx][ny] = step + 1;
                deque.add(new int[]{nx, ny});
                map.put(nx * n + ny, step + 1);
                ans = Math.max(ans, step + 1);
            }
        }
        return ans;
    }



    // 单源最短路径
    // 对每个海洋做 BFS 得到最近陆地距离，在所有的距离中取最大值
    // Time Limit Exceeded
    private int[][] grid;
    private int n;
    private static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maxDistance1(int[][] _grid) {
        grid = _grid;
        n = grid.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }
        return ans;
    }

    private int bfs(int x, int y) {
        Map<Integer, Integer> visited = new HashMap<>();
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{x, y});
        visited.put(x * n + y, 0);
        while (!deque.isEmpty()) {
            int[] poll = deque.pollFirst();
            int dx = poll[0], dy = poll[1];
            int step = visited.get(dx * n + dy);
            if (grid[dx][dy] == 1) {
                return step;
            }
            for (int[] dir : dirs) {
                int nx = dx + dir[0], ny = dy + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                int next = nx * n + ny;
                if (visited.containsKey(next)) {
                    continue;
                }
                deque.addLast(new int[]{nx, ny});
                visited.put(next, step + 1);
            }
        }
        return -1;
    }

}
