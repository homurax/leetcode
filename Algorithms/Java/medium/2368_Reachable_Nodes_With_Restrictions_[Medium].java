/**
 * 2368. Reachable Nodes With Restrictions
 *
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted nodes.
 *
 * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
 *
 * Note that node 0 will not be a restricted node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * Output: 4
 * Explanation: The diagram above shows the tree.
 * We have that [0,1,2,3] are the only nodes that can be reached from node 0 without visiting a restricted node.
 *
 *
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * Output: 3
 * Explanation: The diagram above shows the tree.
 * We have that [0,5,6] are the only nodes that can be reached from node 0 without visiting a restricted node.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * All the values of restricted are unique.
 */
public class ReachableNodesWithRestrictions {


    private List<Integer>[] graph;
    private int ans;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        // Set<Integer> ban = Arrays.stream(restricted).boxed().collect(Collectors.toSet());
        Set<Integer> ban = new HashSet<>();
        for (int i : restricted) {
            ban.add(i);
        }
        graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (!ban.contains(a) && !ban.contains(b)) {
                graph[a].add(b);
                graph[b].add(a);
            }
        }
        dfs(-1, 0);
        return ans;
    }

    private void dfs(int preNode, int node) {
        ans++;
        for (int nextNode : graph[node]) {
            if (nextNode != preNode) {
                dfs(node, nextNode);
            }
        }
    }


    public int reachableNodes1(int n, int[][] edges, int[] restricted) {
        Set<Integer> ban = new HashSet<>();
        for (int i : restricted) {
            ban.add(i);
        }
        List<Integer>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (!ban.contains(a) && !ban.contains(b)) {
                graph[a].add(b);
                graph[b].add(a);
            }
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        int ans = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int node = deque.removeFirst();
                if (visited[node]) {
                    continue;
                }
                visited[node] = true;
                ans++;
                for (int nextNode : graph[node]) {
                    deque.offerLast(nextNode);
                }
            }
        }
        return ans;
    }

}
