/*
3290. Maximum Multiplication Score

You are given an integer array a of size 4 and another integer array b of size at least 4.

You need to choose 4 indices i0, i1, i2, and i3 from the array b such that i0 < i1 < i2 < i3. Your score will be equal to the value a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3].

Return the maximum score you can achieve.

Example 1:

Input: a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]

Output: 26

Explanation:
We can choose the indices 0, 1, 2, and 5. The score will be 3 * 2 + 2 * (-6) + 5 * 4 + 6 * 2 = 26.

Example 2:

Input: a = [-1,4,5,-2], b = [-5,-1,-3,-2,-4]

Output: -1

Explanation:
We can choose the indices 0, 1, 3, and 4. The score will be (-1) * (-5) + 4 * (-1) + 5 * (-2) + (-2) * (-4) = -1.

Constraints:

a.length == 4
4 <= b.length <= 10^5
-105 <= a[i], b[i] <= 10^5
*/
func maxScore(a, b []int) int64 {
	n := len(b)
	memo := make([][4]int64, n)
	for i := range memo {
		for j := range memo[i] {
			memo[i][j] = math.MinInt64
		}
	}
	var dfs func(int, int) int64
	dfs = func(i, j int) int64 {
		if j < 0 {
			return 0
		}
		if i < 0 { // j >= 0
			return math.MinInt64 / 2 // 防溢出
		}
		p := &memo[i][j]
		if *p == math.MinInt64 {
			*p = max(dfs(i-1, j), dfs(i-1, j-1)+int64(a[j])*int64(b[i]))
		}
		return *p
	}
	return dfs(n-1, 3)
}

func maxScore(a, b []int) int64 {
	n := len(b)
	f := make([][5]int64, n+1)
	for j := 1; j < 5; j++ {
		f[0][j] = math.MinInt64 / 2
	}
	for i, v := range b {
		for j, x := range a {
			f[i+1][j+1] = max(f[i][j+1], f[i][j]+int64(x)*int64(v))
		}
	}
	return f[n][4]
}

func maxScore(a, b []int) int64 {
	f := [5]int64{}
	for j := 1; j < 5; j++ {
		f[j] = math.MinInt64 / 2
	}
	for _, v := range b {
		for j := 3; j >= 0; j-- {
			f[j+1] = max(f[j+1], f[j]+int64(a[j])*int64(v))
		}
	}
	return f[4]
}
