/*
3408. Design Task Manager

There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.

Implement the TaskManager class:

TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.

void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId. It is guaranteed that taskId does not exist in the system.

void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is guaranteed that taskId exists in the system.

void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists in the system.

int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the system. Return the userId associated with the executed task. If no tasks are available, return -1.

Note that a user may be assigned multiple tasks.

Example 1:

Input:
["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
[[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]

Output:
[null, null, null, 3, null, null, 5]

# Explanation

TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three tasks for Users 1, 2, and 3.
taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.
taskManager.edit(102, 8); // Updates priority of task 102 to 8.
taskManager.execTop(); // return 3. Executes task 103 for User 3.
taskManager.rmv(101); // Removes task 101 from the system.
taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.
taskManager.execTop(); // return 5. Executes task 105 for User 5.

Constraints:

1 <= tasks.length <= 10^5
0 <= userId <= 10^5
0 <= taskId <= 10^5
0 <= priority <= 10^9
0 <= newPriority <= 10^9
At most 2 * 105 calls will be made in total to add, edit, rmv, and execTop methods.
The input is generated such that taskId will be valid.
*/
type pair struct{ priority, userId int }

type TaskManager struct {
	h  *hp          // (priority, taskId, userId)
	mp map[int]pair // taskId -> (priority, userId)
}

func Constructor(tasks [][]int) TaskManager {
	h := hp{}
	mp := map[int]pair{}
	for _, task := range tasks {
		userId, taskId, priority := task[0], task[1], task[2]
		mp[taskId] = pair{priority, userId}
		h = append(h, tuple{priority, taskId, userId})
	}
	heap.Init(&h)
	return TaskManager{&h, mp}
}

func (tm *TaskManager) Add(userId, taskId, priority int) {
	tm.mp[taskId] = pair{priority, userId}
	heap.Push(tm.h, tuple{priority, taskId, userId})
}

func (tm *TaskManager) Edit(taskId, newPriority int) {
	// 懒修改
	tm.Add(tm.mp[taskId].userId, taskId, newPriority)
}

func (tm *TaskManager) Rmv(taskId int) {
	// 懒删除
	tm.mp[taskId] = pair{-1, -1}
}

func (tm *TaskManager) ExecTop() int {
	for tm.h.Len() > 0 {
		top := heap.Pop(tm.h).(tuple)
		priority, taskId, userId := top.priority, top.taskId, top.userId
		if tm.mp[taskId] == (pair{priority, userId}) {
			tm.Rmv(taskId)
			return userId
		}
	}
	return -1
}

type tuple struct{ priority, taskId, userId int }
type hp []tuple

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	return cmp.Or(h[i].priority-h[j].priority, h[i].taskId-h[j].taskId) > 0
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
