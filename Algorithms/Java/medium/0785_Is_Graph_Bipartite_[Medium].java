/**
 * 785. Is Graph Bipartite
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B, such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can divide the vertices into two groups: {0, 2} and {1, 3}.
 *
 * Example 2:
 *
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 *
 * Constraints:
 *
 * 1. 1 <= graph.length <= 100
 * 2. 0 <= graph[i].length < 100
 * 3. 0 <= graph[i][j] <= graph.length - 1
 * 4. graph[i][j] != i
 * 5. All the values of graph[i] are unique.
 * 6. The graph is guaranteed to be undirected.
 */
public class IsGraphBipartite {

    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] coloring;
    private boolean valid = true;

    // 对于每一条边的两个点 都应该位于不同的集合
    // 选定一个点 给这个点以及和它直接相连的点染不同的颜色
    // 如果可以全部染色 则为二分图 如果不能 则不是
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        coloring = new int[graph.length];
        for (int i = 0; i < n && valid; i++) {
            if (coloring[i] == UNCOLORED) {
                dfs(graph, i, RED);
            }
        }
        return valid;
    }

    private void dfs(int[][] graph, int index, int color) {
        coloring[index] = color;
        int nextColor = color == RED ? GREEN : RED;
        for (int i : graph[index]) {
            if (coloring[i] == UNCOLORED) {
                dfs(graph, i, nextColor);
            }
            else if (coloring[i] != nextColor) {
                valid = false;
                return;
            }
        }
    }

    // BFS
    public boolean isBipartite1(int[][] graph) {
        int n = graph.length;
        int[] coloring = new int[n];
        for (int i = 0; i < n; i++) {
            if (coloring[i] == UNCOLORED) {
                Queue<Integer> colored = new LinkedList<>();
                colored.offer(i);
                coloring[i] = RED;
                while (!colored.isEmpty()) {
                    int node = colored.poll();
                    int nextColor = coloring[node] == RED ? GREEN : RED;
                    for (int neighbor : graph[node]) {
                        if (coloring[neighbor] == UNCOLORED) {
                            colored.offer(neighbor);
                            coloring[neighbor] = nextColor;
                        } else if (coloring[neighbor] != nextColor) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
