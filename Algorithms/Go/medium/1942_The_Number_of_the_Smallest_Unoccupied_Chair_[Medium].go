/*
1942. The Number of the Smallest Unoccupied Chair


There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.

For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.

You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.

Return the chair number that the friend numbered targetFriend will sit on.



Example 1:

Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
Output: 1
Explanation:
- Friend 0 arrives at time 1 and sits on chair 0.
- Friend 1 arrives at time 2 and sits on chair 1.
- Friend 1 leaves at time 3 and chair 1 becomes empty.
- Friend 0 leaves at time 4 and chair 0 becomes empty.
- Friend 2 arrives at time 4 and sits on chair 0.
Since friend 1 sat on chair 1, we return 1.


Example 2:

Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
Output: 2
Explanation:
- Friend 1 arrives at time 1 and sits on chair 0.
- Friend 2 arrives at time 2 and sits on chair 1.
- Friend 0 arrives at time 3 and sits on chair 2.
- Friend 1 leaves at time 5 and chair 0 becomes empty.
- Friend 2 leaves at time 6 and chair 1 becomes empty.
- Friend 0 leaves at time 10 and chair 2 becomes empty.
Since friend 0 sat on chair 2, we return 2.


Constraints:

1. n == times.length
2. 2 <= n <= 10^4
3. times[i].length == 2
4. 1 <= arrivali < leavingi <= 105
5. 0 <= targetFriend <= n - 1
6. Each arrivali time is distinct.
*/

// records[time][0] 代表 time 时刻到来的
// records[time][1] 代表 time 时刻离开的
func smallestChair(times [][]int, targetFriend int) int {
	records := make([][2][]int, 1e5+1)
	for i, time := range times {
		arrival, leaving := time[0], time[1]
		records[arrival][0] = append(records[arrival][0], i)
		records[leaving][1] = append(records[leaving][1], i)
	}

	n := len(times)
	chairs := hp1{make([]int, n)}
	for i := range chairs.IntSlice {
		chairs.IntSlice[i] = i
	}
	belong := make([]int, n)
	for _, record := range records {
		// 释放离开人的椅子
		for _, friend := range record[1] {
			heap.Push(&chairs, belong[friend])
		}
		// 分配到达人的椅子
		for _, friend := range record[0] {
			belong[friend] = heap.Pop(&chairs).(int)
			if friend == targetFriend {
				return belong[friend]
			}
		}
	}
	return 0
}

type hp1 struct{ sort.IntSlice }

func (h *hp1) Push(v interface{}) {
	h.IntSlice = append(h.IntSlice, v.(int))
}

func (h *hp1) Pop() interface{} {
	old := h.IntSlice
	n := len(old)
	v := old[n-1]
	h.IntSlice = old[:n-1]
	return v
}
