/*
3169. Count Days Without Meetings

You are given a positive integer days representing the total number of days an employee is available for work (starting from day 1). You are also given a 2D array meetings of size n where, meetings[i] = [start_i, end_i] represents the starting and ending days of meeting i (inclusive).

Return the count of days when the employee is available for work but no meetings are scheduled.

Note: The meetings may overlap.

Example 1:

Input: days = 10, meetings = [[5,7],[1,3],[9,10]]

Output: 2

Explanation:

There is no meeting scheduled on the 4th and 8th days.

Example 2:

Input: days = 5, meetings = [[2,4],[1,3]]

Output: 1

Explanation:

There is no meeting scheduled on the 5th day.

Example 3:

Input: days = 6, meetings = [[1,6]]

Output: 0

Explanation:

Meetings are scheduled for all working days.

Constraints:

1 <= days <= 10^9
1 <= meetings.length <= 10^5
meetings[i].length == 2
1 <= meetings[i][0] <= meetings[i][1] <= days
*/
func countDays(days int, meetings [][]int) int {
	slices.SortFunc(meetings, func(p, q []int) int { return p[0] - q[0] })
	start, end := 1, 0
	for _, p := range meetings {
		if p[0] > end { // 与上个区间不相交
			days -= end - start + 1 // 减去上个区间
			start = p[0]
		}
		// 相交或者不相交都需要更新 end
		end = max(end, p[1])
	}
	days -= end - start + 1 // 最后一个区间
	return days
}
