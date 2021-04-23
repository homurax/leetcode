/**
 * 787. Cheapest Flights Within K Stops
 *
 *
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 *
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * 2. The size of flights will be in range [0, n * (n - 1) / 2].
 * 3. The format of each flight will be (src, dst, price).
 * 4. The price of each flight will be in the range [1, 10000].
 * 5. k is in the range of [0, n - 1].
 * 3. There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {

    /**
     * DFS / 回溯
     */
    private int[][] graph;
    private boolean[] visited;
    private int ans = Integer.MAX_VALUE;

    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K) {
        K = Math.min(K, n - 2);
        this.graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        this.visited = new boolean[n];
        dfs(src, dst, K + 1, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int src, int dst, int cities, int cost) {
        if (src == dst) {
            ans = cost;
            return;
        }
        if (cities == 0) {
            return;
        }
        for (int i = 0; i < graph[src].length; i++) {
            if (graph[src][i] > 0) {
                if (visited[i]) {
                    continue;
                }
                if (graph[src][i] + cost > ans) {
                    continue;
                }
                visited[i] = true;
                dfs(i, dst, cities - 1, cost + graph[src][i]);
                visited[i] = false;
            }
        }
    }

    // Dijkstra
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 起点, 费用, 站数限制
        pq.offer(new int[]{src, 0, K + 1});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int station = node[0], price = node[1], cities = node[2];
            if (station == dst) {
                return price;
            }
            if (cities > 0) {
                for (int i = 0; i < n; i++) {
                    if (graph[station][i] > 0) {
                        // 存在指向的顶点 i、从起点 station 到 i 的总路径长度、还有多少站可以中转
                        pq.offer(new int[]{i, price + graph[station][i], cities - 1});
                    }
                }
            }
        }
        return -1;
    }

    //Bellman-Ford
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        // 从顶点 src 到其它顶点 i 经过了 j 次松弛操作（也就是经过了 j 个顶点）以后得到的最短路径
        int[][] dp = new int[n][K + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i <= K; i++) {
            dp[src][i] = 0;
        }

        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[flight[1]][0] = flight[2];
            }
        }
        for (int i = 1; i <= K; i++) {
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                // 每一次松弛操作的结果是互相独立的，因此只有在上一轮（第 i - 1 轮）得到单源最短路径的顶点，才需要执行松弛操作
                if (dp[from][i - 1] != Integer.MAX_VALUE) {
                    dp[to][i] = Math.min(dp[from][i - 1] + flight[2], dp[to][i]);
                }
            }
        }
        return dp[dst][K] == Integer.MAX_VALUE ? -1 : dp[dst][K];
    }


}
