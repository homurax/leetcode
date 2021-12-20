/**
 * 1986. Minimum Number of Work Sessions to Finish the Tasks
 *
 *
 * There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.
 *
 * You should finish the given tasks in a way that satisfies the following conditions:
 *
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
 *
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 *
 * Example 2:
 *
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 *
 * Example 3:
 *
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 *
 *
 * Constraints:
 *
 * 1. n == tasks.length
 * 2. 1 <= n <= 14
 * 3. 1 <= tasks[i] <= 10
 * 4. max(tasks[i]) <= sessionTime <= 15
 */
public class MinimumNumberOfWorkSessionsToFinishTheTasks {

    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length, limit = 1 << n;
        int[] dp = new int[limit];
        Arrays.fill(dp, n);
        for (int mask = 1; mask < limit; mask++) {
            int cost = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) > 0) {
                    cost += tasks[i];
                }
            }
            if (cost <= sessionTime) {
                dp[mask] = 1;
            }
        }
        for (int mask = 1; mask < limit; mask++) {
            if (dp[mask] == 1) {
                continue;
            }
            int split = mask >> 1;
            for (int subset = (mask - 1) & mask; subset > split; subset = (subset - 1) & mask) {
                dp[mask] = Math.min(dp[mask], dp[subset] + dp[mask ^ subset]);
            }
        }
        return dp[limit - 1];
    }


    public int minSessions1(int[] tasks, int sessionTime) {
        int n = tasks.length, limit = 1 << n;
        // 确定所有可选的状态
        boolean[] status = new boolean[limit];
        for (int mask = 1; mask < limit; mask++) {
            int cost = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) > 0) {
                    cost += tasks[i];
                }
            }
            if (cost <= sessionTime) {
                status[mask] = true;
            }
        }
        int[] dp = new int[limit];
        Arrays.fill(dp, 1, limit, n);
        for (int mask = 1; mask < limit; mask++) {
            // subset 是 mask 的一个子集
            for (int subset = mask; subset > 0; subset = (subset - 1) & mask) {
                if (status[subset]) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ subset] + 1);
                }
            }
        }
        return dp[limit - 1];
    }
    

}
