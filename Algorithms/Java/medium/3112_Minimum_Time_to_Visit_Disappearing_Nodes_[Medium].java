/**
 * 3112. Minimum Time to Visit Disappearing Nodes
 *
 *
 * There is an undirected graph of n nodes. You are given a 2D array edges, where edges[i] = [ui, vi, lengthi] describes an edge between node ui and node vi with a traversal time of lengthi units.
 *
 * Additionally, you are given an array disappear, where disappear[i] denotes the time when the node i disappears from the graph and you won't be able to visit it.
 *
 * Note that the graph might be disconnected and might contain multiple edges.
 *
 * Return the array answer, with answer[i] denoting the minimum units of time required to reach node i from node 0. If node i is unreachable from node 0 then answer[i] is -1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]
 *
 * Output: [0,-1,4]
 *
 * Explanation:
 *
 *
 *
 * We are starting our journey from node 0, and our goal is to find the minimum time required to reach each node before it disappears.
 *
 * For node 0, we don't need any time as it is our starting point.
 * For node 1, we need at least 2 units of time to traverse edges[0]. Unfortunately, it disappears at that moment, so we won't be able to visit it.
 * For node 2, we need at least 4 units of time to traverse edges[2].
 *
 *
 * Example 2:
 *
 * Input: n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]
 *
 * Output: [0,2,3]
 *
 * Explanation:
 *
 *
 *
 * We are starting our journey from node 0, and our goal is to find the minimum time required to reach each node before it disappears.
 *
 * For node 0, we don't need any time as it is the starting point.
 * For node 1, we need at least 2 units of time to traverse edges[0].
 * For node 2, we need at least 3 units of time to traverse edges[0] and edges[1].
 *
 *
 * Example 3:
 *
 * Input: n = 2, edges = [[0,1,1]], disappear = [1,1]
 *
 * Output: [0,-1]
 *
 * Explanation:
 *
 * Exactly when we reach node 1, it disappears.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5 * 10^4
 * 0 <= edges.length <= 10^5
 * edges[i] == [ui, vi, lengthi]
 * 0 <= ui, vi <= n - 1
 * 1 <= lengthi <= 10^5
 * disappear.length == n
 * 1 <= disappear[i] <= 10^5
 */
public class MinimumTimeToVisitDisappearingNodes {

    // Dijkstra
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        // 邻接表
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], t = edge[2];
            graph[x].add(new int[]{y, t});
            graph[y].add(new int[]{x, t});
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // t, x
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int t = poll[0], x = poll[1];
            if (t > ans[x]) { // 更早前访问过
                continue;
            }
            for (int[] p : graph[x]) {
                int y = p[0], tt = t + p[1];
                if (tt < disappear[y] && (ans[y] < 0 || tt < ans[y])) {
                    ans[y] = tt;
                    pq.offer(new int[]{tt, y});
                }
            }
        }
        return ans;
    }

}
