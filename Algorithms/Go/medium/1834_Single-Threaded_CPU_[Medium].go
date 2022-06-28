/*
1834. Single-Threaded CPU


You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.



Example 1:

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows:
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
Example 2:

Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.


Constraints:

1. tasks.length == n
2. 1 <= n <= 10^5
3. 1 <= enqueueTimei, processingTimei <= 10^9
*/

func getOrder(tasks [][]int) []int {
	n := len(tasks)
	ans := make([]int, n)
	temp := make([][3]int, n)
	for i := 0; i < n; i++ {
		temp[i] = [3]int{tasks[i][0], tasks[i][1], i}
	}
	sort.Slice(temp, func(i, j int) bool {
		return temp[i][0] < temp[j][0]
	})

	t := temp[0][0]
	r, w := 0, 0
	h := &TaskHeap{}
	heap.Init(h)
	for w < n {
		for r < n && temp[r][0] <= t {
			heap.Push(h, temp[r])
			r++
		}
		if h.Len() == 0 {
			t = temp[r][0]
		} else {
			task := heap.Pop(h).([3]int)
			ans[w] = task[2]
			t += task[1]
			w++
		}
	}
	return ans
}

type TaskHeap [][3]int

func (h TaskHeap) Len() int { return len(h) }

func (h TaskHeap) Less(i, j int) bool {
	if h[i][1] != h[j][1] {
		return h[i][1] < h[j][1]
	} else {
		return h[i][2] < h[j][2]
	}
}
func (h TaskHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }

func (h *TaskHeap) Push(x interface{}) {
	*h = append(*h, x.([3]int))
}

func (h *TaskHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
