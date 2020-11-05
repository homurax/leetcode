/**
 * 621. Task Scheduler
 *
 *
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 *
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task: tasks) {
            count[task - 'A']++;
        }
        Arrays.sort(count);
        int max = count[25] - 1, idle = max * n;
        for (int i = 24; i >= 0 && count[i] > 0; i--) {
            idle -= Math.min(count[i], max);
        }
        return idle > 0 ? idle + tasks.length : tasks.length;
    }


    /**
     * 一个执行周期是 n + 1
     * 最少需要 (max - 1) * (n + 1) + 1
     * 这个 1 其实就是出现 max 次的 letter 的类数
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task: tasks) {
            count[task - 'A']++;
        }
        int max = 0;
        for (int i : count) {
            max = Math.max(max, i);
        }
        int maxCount = 0;
        for (int i : count) {
            if (i == max) {
                maxCount++;
            }
        }
        return Math.max(tasks.length, (max - 1) * (n + 1) + maxCount);
    }

}
