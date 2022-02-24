/**
 * 210. Course Schedule II
 *
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 *
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1. 1 <= numCourses <= 2000
 * 2. 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * 3. prerequisites[i].length == 2
 * 4. 0 <= ai, bi < numCourses
 * 5. ai != bi
 * 6. All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {

    private List<Integer>[] edges;
    private int[] visited;
    private int[] ans;
    private boolean cycle = false;
    private int index;

    // DFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        index = numCourses - 1;
        edges = new List[numCourses];
        visited = new int[numCourses];
        ans = new int[numCourses];
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
        if (cycle) {
            return new int[0];
        }
        return ans;
    }

    private void dfs(int u) {
        visited[u] = 1;
        for (int v : edges[u]) { // 搜索学习 u 后可以学习的课程
            if (visited[v] == 0) {
                dfs(v);
                if (cycle) {
                    return;
                }
            } else if (visited[v] == 1) { // 成环
                cycle = true;
                return;
            }
        }
        visited[u] = 2;
        ans[index--] = u;
    }


    // BFS
    private int[] depth;

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        edges = new List[numCourses];
        ans = new int[numCourses];
        depth = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }
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
        while (!queue.isEmpty()) {
            int u = queue.poll();
            ans[index++] = u;
            for (int v : edges[u]) {
                depth[v]--;
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (depth[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (index != numCourses) {
            return new int[0];
        }
        return ans;
    }

}
