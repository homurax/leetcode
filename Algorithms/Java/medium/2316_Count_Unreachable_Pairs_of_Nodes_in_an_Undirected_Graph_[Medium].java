/**
 * 2316. Count Unreachable Pairs of Nodes in an Undirected Graph
 *
 *
 * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * Return the number of pairs of different nodes that are unreachable from each other.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[0,2],[1,2]]
 * Output: 0
 * Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
 *
 *
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * Output: 14
 * Explanation: There are 14 pairs of nodes that are unreachable from each other:
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
 * Therefore, we return 14.
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 10^5
 * 2. 0 <= edges.length <= 2 * 10^5
 * 3. edges[i].length == 2
 * 4. 0 <= ai, bi < n
 * 5. ai != bi
 * 6. There are no repeated edges.
 */
public class CountUnreachablePairsOfNodesInAnUndirectedGraph {

    private List<Integer>[] graph;
    private boolean[] visited;
    private int size;

    // 并查集获取的所有的连通块大小后再计算
    // 建图后边遍历边计算累加
    public long countPairs(int n, int[][] edges) {
        visited = new boolean[n];
        graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        long ans = 0;
        for (int i = 0, total = 0; i < n; i++) {
            if (!visited[i]) {
                size = 0;
                dfs(i);
                ans += (long) total * size;
                total += size;
            }
        }
        return ans;
    }

    private void dfs(int i) {
        visited[i] = true;
        size++;
        for (int j : graph[i]) {
            if (!visited[j]) {
                dfs(j);
            }
        }
    }

}
