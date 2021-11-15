/**
 * 1443. Minimum Time to Collect All Apples in a Tree
 *
 *
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 *
 * Example 2:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 *
 * Example 3:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 10^5
 * 2. edges.length == n - 1
 * 3. edges[i].length == 2
 * 4. 0 <= ai < bi <= n - 1
 * 5. fromi < toi
 * 6. hasApple.length == n
 */
public class MinimumTimeToCollectAllApplesInATree {

    public int minTime1(int n, int[][] edges, List<Boolean> hasApple) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
        for (int[] edge : edges) {
            if (parents[edge[1]] == -1) {
                parents[edge[1]] = edge[0];
            } else {
                parents[edge[0]] = edge[1];
            }
        }
        int time = 0;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for (int i = 1; i < n; i++) {
            if (hasApple.get(i)) {
                int curr = i;
                while (!visited[curr]) {
                    visited[curr] = true;
                    time += 2;
                    curr = parents[curr];
                }
            }
        }
        return time;
    }


    /**
     * 如果 n 的子树没有苹果，那么 n 子树应该剪掉
     * 裁剪后每个叶子都有苹果，所以每条边都需要走 2 次
     * 2 * (节点数 - 1)
     * 注意编号小不一定为父节点 [[0,2],[1,2]]
     */
    private ArrayList<Integer>[] graph;
    private boolean[] visited;
    private List<Boolean> hasApple;
    private int del;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int appleCount = 0;
        for (Boolean isApple : hasApple) {
            if (isApple) {
                appleCount++;
            }
        }
        if (appleCount == 0) {
            return 0;
        } else if (appleCount == n) {
            return (n - 1) * 2;
        }
        this.graph = new ArrayList[n];
        this.visited = new boolean[n];
        this.hasApple = hasApple;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        dfs(0);
        return (n - del - 1) * 2;
    }

    private int dfs(int i) {
        int count = 0;
        visited[i] = true;
        if (hasApple.get(i)) {
            count++;
        }
        for (Integer to : graph[i]) {
            if (visited[to]) {
                continue;
            }
            count += dfs(to);
        }
        if (count == 0) {
            del++;
        }
        return count;
    }

}
