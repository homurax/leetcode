/*
3122. Minimum Number of Operations to Satisfy Conditions

You are given a 2D matrix grid of size m x n. In one operation, you can change the value of any cell to any non-negative number. You need to perform some operations such that each cell grid[i][j] is:

Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
Return the minimum number of operations needed.

Example 1:

Input: grid = [[1,0,2],[1,0,2]]

Output: 0

Explanation:

All the cells in the matrix already satisfy the properties.

Example 2:

Input: grid = [[1,1,1],[0,0,0]]

Output: 3

Explanation:

The matrix becomes [[1,0,1],[1,0,1]] which satisfies the properties, by doing these 3 operations:

Change grid[1][0] to 1.
Change grid[0][1] to 0.
Change grid[1][2] to 1.

Example 3:

Input: grid = [[1],[2],[3]]

Output: 2

Explanation:

There is a single column. We can change the value to 1 in each cell using 2 operations.

Constraints:

1 <= n, m <= 1000
0 <= grid[i][j] <= 9
*/
func minimumOperations(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	cnt := make([][10]int, n)
	for _, row := range grid {
		for j, x := range row {
			cnt[j][x]++
		}
	}
	memo := make([][11]int, n)
	for i := range memo {
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(i, j int) (res int) {
		if i < 0 {
			return
		}
		p := &memo[i][j]
		if *p != -1 {
			return *p
		}
		for k, c := range cnt[i] {
			if k != j {
				res = max(res, dfs(i-1, k)+c)
			}
		}
		*p = res
		return
	}
	return m*n - dfs(n-1, 10)
}

func minimumOperations1(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f0, f1, pre := 0, 0, -1
	for i := 0; i < n; i++ {
		cnt := [10]int{}
		for _, row := range grid {
			cnt[row[i]]++
		}
		mx, mx2, x := -1, 0, -1
		for v := range cnt {
			res := 0
			if v != pre {
				res = f0
			} else {
				res = f1
			}
			res += cnt[v]
			if res > mx {
				mx2 = mx
				mx = res
				x = v
			} else if res > mx2 {
				mx2 = res
			}
		}
		f0, f1, pre = mx, mx2, x
	}
	return m*n - f0
}
