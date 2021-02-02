/**
 * 743. Network Delay Time
 *
 *
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 *
 * Example 2:
 *
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 *
 * Example 3:
 *
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= n <= 100
 * 2. 1 <= times.length <= 6000
 * 3. times[i].length == 3
 * 4. 1 <= ui, vi <= n
 * 5. ui != vi
 * 6. 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {

    // dfs
    public int networkDelayTime1(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int u = time[0], v = time[1], t = time[2];
            if (!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            graph.get(u).add(new int[]{t, v});
        }
        for (int node: graph.keySet()) {
            graph.get(node).sort(Comparator.comparingInt(a -> a[0]));
        }
        int[] records = new int[n];
        Arrays.fill(records, Integer.MAX_VALUE);
        dfs(graph, records, k, 0);
        int ans = 0;
        for (int time : records) {
            if (time == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, time);
        }
        return ans;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int[] records, int node, int time) {
        if (time > records[node - 1]) {
            return;
        }
        records[node - 1] = time;
        if (graph.containsKey(node)) {
            for (int[] tv : graph.get(node)) {
                int t = tv[0], v = tv[1];
                dfs(graph, records, v, time + t);
            }
        }
    }


    // Dijkstra 堆优化
    public int networkDelayTime2(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.offer(new int[]{0, k});
        Map<Integer, Integer> dist = new HashMap<>();
        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int time = info[0], node = info[1];
            if (dist.containsKey(node)) {
                continue;
            }
            dist.put(node, time);
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int node2 = edge[0], time2 = edge[1];
                    if (!dist.containsKey(node2)) {
                        heap.offer(new int[]{time + time2, node2});
                    }
                }
            }
        }
        if (dist.size() != n) {
            return -1;
        }
        int ans = 0;
        for (int time : dist.values()) {
            ans = Math.max(ans, time);
        }
        return ans;
    }



    // Dijkstra 堆优化
    public int networkDelayTime3(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] t : times) {
            graph.computeIfAbsent(t[0], node -> new ArrayList<>()).add(new int[]{t[1], t[2]});
        }

        int[] timeRecord = new int[n + 1];
        Arrays.fill(timeRecord, 0x3f3f3f3f);
        timeRecord[k] = timeRecord[0] = 0;
        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> timeRecord[o]));
        queue.offer(k);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            List<int[]> list = graph.getOrDefault(node, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                if (visited[next]) {
                    continue;
                }
                timeRecord[next] = Math.min(timeRecord[next], timeRecord[node] + arr[1]);
                queue.offer(next);
            }
        }
        int ans = Arrays.stream(timeRecord).max().getAsInt();
        return ans == 0x3f3f3f3f ? -1 : ans;
    }

    // SPFA 邻接表
    public int networkDelayTime4(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] t : times) {
            graph.computeIfAbsent(t[0], node -> new ArrayList<>()).add(new int[]{t[1], t[2]});
        }

        int[] timeRecord = new int[n + 1];
        Arrays.fill(timeRecord, 0x3f3f3f3f);
        timeRecord[k] = timeRecord[0] = 0;
        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            // 可以重复入队
            visited[node] = false;
            List<int[]> list = graph.getOrDefault(node, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                // 需要更新距离
                if (timeRecord[next] == 0x3f3f3f3f || timeRecord[next] > timeRecord[node] + arr[1]) {
                    timeRecord[next] = timeRecord[node] + arr[1];
                    // 不需要再次入队
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        int ans = Arrays.stream(timeRecord).max().getAsInt();
        return ans == 0x3f3f3f3f ? -1 : ans;
    }


    // SPFA 邻接矩阵
    public int networkDelayTime5(int[][] times, int n, int k) {
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = (i == j) ? 0 : -1;
            }
        }
        for (int[] t : times) {
            graph[t[0]][t[1]] = t[2];
        }

        int[] timeRecord = new int[n + 1];
        Arrays.fill(timeRecord, 0x3f3f3f3f);
        timeRecord[0] = timeRecord[k] = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        visited[k] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            visited[node] = false;
            for (int next = 1; next <= n; next++) {
                int time = graph[node][next];
                if (time != -1 && (timeRecord[next] == 0x3f3f3f3f || timeRecord[next] > timeRecord[node] + time)) {
                    timeRecord[next] = timeRecord[node] + time;
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        int ans = Arrays.stream(timeRecord).max().getAsInt();
        return ans == 0x3f3f3f3f ? -1 : ans;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] record = new int[n + 1];
        Arrays.fill(record, Integer.MAX_VALUE);
        record[k] = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean modify = false;
            for (int[] time : times) {
                int from = time[0], to = time[1], cost = time[2];
                if (record[from] == Integer.MAX_VALUE) {
                    continue;
                }
                if (record[to] > record[from] + cost) {
                    record[to] = record[from] + cost;
                    modify = true;
                }
            }
            if (!modify) {
                break;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, record[i]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
