/**
 * 2492. Minimum Score of a Path Between Two Cities
 *
 *
 * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
 *
 * The score of a path between two cities is defined as the minimum distance of a road in this path.
 *
 * Return the minimum possible score of a path between cities 1 and n.
 *
 * Note:
 *
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
 * The test cases are generated such that there is at least one path between 1 and n.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 * Output: 5
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
 *
 *
 * It can be shown that no other path has less score.
 * Example 2:
 *
 *
 * Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 * Output: 2
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^5
 * 1 <= roads.length <= 10^5
 * roads[i].length == 3
 * 1 <= ai, bi <= n
 * ai != bi
 * 1 <= distancei <= 10^4
 * There are no repeated edges.
 * There is at least one path between 1 and n.
 */
public class MinimumScoreOfAPathBetweenTwoCities {

    // 双向道路即为可以折返
    // 1 和 n 必定联通 最短路径即为该连通块中的最短路径


    private List<int[]>[] graph;
    private boolean[] visited;
    private int ans;

    public int minScore(int n, int[][] roads) {
        ans = Integer.MAX_VALUE;
        visited = new boolean[n];
        graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] road : roads) {
            int a = road[0] - 1, b = road[1] - 1, d = road[2];
            graph[a].add(new int[]{b, d});
            graph[b].add(new int[]{a, d});
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        visited[i] = true;
        for (int[] edge : graph[i]) {
            ans = Math.min(ans, edge[1]);
            if (!visited[edge[0]]) {
                dfs(edge[0]);
            }
        }
    }

}
