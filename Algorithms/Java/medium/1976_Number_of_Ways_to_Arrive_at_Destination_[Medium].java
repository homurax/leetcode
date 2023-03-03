/**
 * 1976. Number of Ways to Arrive at Destination
 *
 *
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 *
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * Output: 4
 * Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
 * The four ways to get there in 7 minutes are:
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 *
 *
 * Example 2:
 *
 * Input: n = 2, roads = [[1,0,10]]
 * Output: 1
 * Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 200
 * 2. n - 1 <= roads.length <= n * (n - 1) / 2
 * 3. roads[i].length == 3
 * 4. 0 <= ui, vi <= n - 1
 * 5. 1 <= timei <= 10^9
 * 6. ui != vi
 * 7. There is at most one road connecting any two intersections.
 * 8. You can reach any intersection from any other intersection.
 */
public class NumberOfWaysToArriveAtDestination {


    // dist[i] 代表从 0 出发到 i 点最小距离
    // 初始化 dist 后，统计路径和为 dist[n - 1] 的方案数


    int N = 210;
    int[][] g = new int[N][N];
    long[] dist = new long[N];
    boolean[] visited = new boolean[N];
    int[] in = new int[N];
    int n;

    public int countPaths(int n, int[][] roads) {
        this.n = n;
        // 邻接
        for (int[] road : roads) {
            int a = road[0], b = road[1], c = road[2];
            g[a][b] = g[b][a] = c;
        }
        dijkstra();
        // 重新建图
        for (int[] road : roads) {
            int a = road[0], b = road[1], c = road[2];
            g[a][b] = g[b][a] = 0;
            if (dist[a] + c == dist[b]) {
                g[a][b] = c;
                in[b]++;
            } else if (dist[b] + c == dist[a]) {
                g[b][a] = c;
                in[a]++;
            }
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                deque.addLast(i);
            }
        }
        int[] f = new int[n];
        f[0] = 1;
        while (!deque.isEmpty()) {
            int x = deque.pollFirst();
            for (int i = 0; i < n; i++) {
                if (g[x][i] == 0) {
                    continue;
                }
                f[i] += f[x];
                f[i] %= 1_000_000_007;
                if (--in[i] == 0) {
                    deque.addLast(i);
                }
            }
        }
        return f[n - 1];
    }

    // 求解从 0 点到其他点的最短路
    private void dijkstra() {
        Arrays.fill(dist, (long) 1e12);
        dist[0] = 0;
        for (int i = 0; i < n; i++) {
            int t = -1;
            // 当前未访问过的中距离最小的
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            visited[t] = true;
            for (int j = 0; j < n; j++) {
                if (g[t][j] == 0) {
                    continue;
                }
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
    }

}
