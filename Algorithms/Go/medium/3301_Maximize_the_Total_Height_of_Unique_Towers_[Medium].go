/*
3301. Maximize the Total Height of Unique Towers

You are given an array maximumHeight, where maximumHeight[i] denotes the maximum height the ith tower can be assigned.

Your task is to assign a height to each tower so that:

The height of the ith tower is a positive integer and does not exceed maximumHeight[i].
No two towers have the same height.
Return the maximum possible total sum of the tower heights. If it's not possible to assign heights, return -1.

Example 1:

Input: maximumHeight = [2,3,4,3]

Output: 10

Explanation:

We can assign heights in the following way: [1, 2, 4, 3].

Example 2:

Input: maximumHeight = [15,10]

Output: 25

Explanation:

We can assign heights in the following way: [15, 10].

Example 3:

Input: maximumHeight = [2,2,1]

Output: -1

Explanation:

It's impossible to assign positive heights to each index so that no two towers have the same height.

Constraints:

1 <= maximumHeight.length <= 10^5
1 <= maximumHeight[i] <= 10^9
*/
func maximumTotalSum(maximumHeight []int) int64 {
	slices.SortFunc(maximumHeight, func(a, b int) int { return b - a })
	ans := maximumHeight[0]
	for i := 1; i < len(maximumHeight); i++ {
		maximumHeight[i] = min(maximumHeight[i], maximumHeight[i-1]-1)
		if maximumHeight[i] <= 0 {
			return -1
		}
		ans += maximumHeight[i]
	}
	return int64(ans)
}
