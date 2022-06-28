/**
 * 1834. Single-Threaded CPU
 *
 *
 * You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the ith task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 *
 *
 * Example 2:
 *
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 *
 *
 * Constraints:
 *
 * 1. tasks.length == n
 * 2. 1 <= n <= 10^5
 * 3. 1 <= enqueueTimei, processingTimei <= 10^9
 */
public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] temp = new int[n][3];
        for (int i = 0; i < n; i++) {
            temp[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }
        Arrays.sort(temp, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        int t = temp[0][0];
        int r = 0, w = 0;
        int[] ans = new int[n];
        while (w < n) {
            while (r < n && temp[r][0] <= t) {
                pq.add(temp[r++]);
            }
            if (pq.isEmpty()) {
                t = temp[r][0];
            } else {
                int[] task = pq.poll();
                ans[w++] = task[2];
                t += task[1];
            }
        }
        return ans;
    }


    public int[] getOrder1(int[][] tasks) {
        int n = tasks.length;
        int[] ans = new int[n];

        int[][] temp = new int[n][3];
        for (int i = 0; i < n; i++) {
            temp[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }
        Arrays.sort(temp, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[2], b[2]);
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        int t = temp[0][0];
        int w = 0, r = 1;
        pq.offer(temp[0]);
        while (!pq.isEmpty()) {
            int[] task = pq.remove();
            ans[w++] = task[2];
            t += task[1];
            while (r < n && temp[r][0] <= t) {
                pq.offer(temp[r++]);
            }
            if (pq.isEmpty() && r < n) {
                pq.offer(temp[r]);
                t = temp[r][0];
                r++;
            }
        }
        return ans;
    }
}
