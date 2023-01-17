/*
1353. Maximum Number of Events That Can Be Attended


You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.

Return the maximum number of events you can attend.



Example 1:


Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.


Example 2:

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4


Constraints:

1. 1 <= events.length <= 10^5
2. events[i].length == 2
3. 1 <= startDayi <= endDayi <= 10^5
*/

func maxEvents(events [][]int) int {
	sort.Slice(events, func(i, j int) bool {
		return events[i][0] < events[j][0]
	})
	ans, n := 0, len(events)
	h := &IntHp{}
	heap.Init(h)
	for i, day := 0, events[0][0]; i < n || h.Len() > 0; day++ {
		for i < n && events[i][0] == day {
			heap.Push(h, events[i][1])
			i++
		}
		for h.Len() > 0 && h.peek() < day {
			heap.Pop(h)
		}
		if h.Len() > 0 {
			heap.Pop(h)
			ans++
		}
	}
	return ans
}

type IntHp []int

func (h IntHp) Len() int {
	return len(h)
}

func (h IntHp) Less(i, j int) bool {
	return h[i] < h[j]
}

func (h IntHp) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *IntHp) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *IntHp) Pop() interface{} {
	old := *h
	n := len(old)
	v := old[n-1]
	*h = old[0 : n-1]
	return v
}

func (h IntHp) peek() int {
	return h[0]
}
