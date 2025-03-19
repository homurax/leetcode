/**
 * 3408. Design Task Manager
 *
 *
 * There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.
 *
 * Implement the TaskManager class:
 *
 * TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.
 *
 * void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId. It is guaranteed that taskId does not exist in the system.
 *
 * void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is guaranteed that taskId exists in the system.
 *
 * void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists in the system.
 *
 * int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the system. Return the userId associated with the executed task. If no tasks are available, return -1.
 *
 * Note that a user may be assigned multiple tasks.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
 * [[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]
 *
 * Output:
 * [null, null, null, 3, null, null, 5]
 *
 * Explanation
 *
 * TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three tasks for Users 1, 2, and 3.
 * taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.
 * taskManager.edit(102, 8); // Updates priority of task 102 to 8.
 * taskManager.execTop(); // return 3. Executes task 103 for User 3.
 * taskManager.rmv(101); // Removes task 101 from the system.
 * taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.
 * taskManager.execTop(); // return 5. Executes task 105 for User 5.
 *
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 10^5
 * 0 <= userId <= 10^5
 * 0 <= taskId <= 10^5
 * 0 <= priority <= 10^9
 * 0 <= newPriority <= 10^9
 * At most 2 * 105 calls will be made in total to add, edit, rmv, and execTop methods.
 * The input is generated such that taskId will be valid.
 */
public class DesignTaskManager {

    class TaskManager {

        // taskId -> (priority, userId)
        private Map<Integer, int[]> map = new HashMap<>();
        // priority taskId userId
        private PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);


        public TaskManager(List<List<Integer>> tasks) {
            for (List<Integer> task : tasks) {
                add(task.get(0), task.get(1), task.get(2));
            }
        }

        public void add(int userId, int taskId, int priority) {
            map.put(taskId, new int[]{priority, userId});
            pq.offer(new int[]{priority, taskId, userId});
        }

        // 懒修改
        public void edit(int taskId, int newPriority) {
            add(map.get(taskId)[1], taskId, newPriority);
        }

        // 懒删除
        public void rmv(int taskId) {
            map.get(taskId)[0] = -1;
        }

        public int execTop() {
            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                int priority = poll[0], taskId = poll[1], userId = poll[2];
                int[] p = map.get(taskId);
                if (p[0] == priority && p[1] == userId) {
                    rmv(taskId);
                    return userId;
                }
            }
            return -1;
        }

    }

}
