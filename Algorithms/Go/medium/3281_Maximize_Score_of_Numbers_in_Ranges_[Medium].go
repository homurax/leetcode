/*
3281. Maximize Score of Numbers in Ranges

You are given an array of integers start and an integer d, representing n intervals [start[i], start[i] + d].

You are asked to choose n integers where the ith integer must belong to the ith interval. The score of the chosen integers is defined as the minimum absolute difference between any two integers that have been chosen.

Return the maximum possible score of the chosen integers.

Example 1:

Input: start = [6,0,3], d = 2

Output: 4

Explanation:

The maximum possible score can be obtained by choosing integers: 8, 0, and 4. The score of these chosen integers is min(|8 - 0|, |8 - 4|, |0 - 4|) which equals 4.

Example 2:

Input: start = [2,6,13,13], d = 5

Output: 5

Explanation:

The maximum possible score can be obtained by choosing integers: 2, 7, 13, and 18. The score of these chosen integers is min(|2 - 7|, |2 - 13|, |2 - 18|, |7 - 13|, |7 - 18|, |13 - 18|) which equals 5.

Constraints:

2 <= start.length <= 10^5
0 <= start[i] <= 10^9
0 <= d <= 10^9
*/

// 最大化最小值 -> 二分
// 单调性: start[i] 左端点排序, 假设 区间1 选择 x, 区间二至少选择 x + ans 才能使 ans 成力, ans 越大越可能选取数不在区间内
// 贪心的考虑 第一个数选择的越小越有利 start[0]
// xi = max(xi-1 + ans, start[i])
func maxPossibleScore(start []int, d int) int {
	slices.Sort(start)
	n := len(start)
	// 二分最小的不满足要求的 score+1, 最终得到的答案就是最大的满足要求的 score
	return sort.Search((start[n-1]+d-start[0])/(n-1), func(score int) bool {
		score++
		x := math.MinInt
		for _, s := range start {
			x = max(x+score, s)
			if x > s+d {
				return true
			}
		}
		return false
	})
}
