/**
 * 207. Course Schedule
 *
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1. 1 <= numCourses <= 10^5
 * 2. 0 <= prerequisites.length <= 5000
 * 3. prerequisites[i].length == 2
 * 4. 0 <= ai, bi < numCourses
 * 5. All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    // BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] depth = new int[numCourses];
        for (int[] pair : prerequisites) {
            edges[pair[1]].add(pair[0]);
            depth[pair[0]]++;
        }
        // 将所有入度为 0 的节点（没有前置课程）放入队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (depth[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            index++;
            for (int v : edges[u]) {
                depth[v]--;
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (depth[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return index == numCourses;
    }


    // DFS
    private List<Integer>[] edges;
    private int[] visited;
    private boolean cycle;

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges = new List[numCourses];
        visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] pair : prerequisites) {
            edges[pair[1]].add(pair[0]);
        }
        for (int i = 0; i < numCourses && !cycle; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return !cycle;
    }

    private void dfs(int u) {
        visited[u] = 1;
        for (int v : edges[u]) {
            if (visited[v] == 0) {
                dfs(v);
                if (cycle) {
                    return;
                }
            } else if (visited[v] == 1) {
                cycle = true;
                return;
            }
        }
        visited[u] = 2;
    }


}
