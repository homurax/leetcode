/*
2768. Number of Black Blocks

You are given two integers m and n representing the dimensions of a 0-indexed m x n grid.

You are also given a 0-indexed 2D integer matrix coordinates, where coordinates[i] = [x, y] indicates that the cell with coordinates [x, y] is colored black. All cells in the grid that do not appear in coordinates are white.

A block is defined as a 2 x 2 submatrix of the grid. More formally, a block with cell [x, y] as its top-left corner where 0 <= x < m - 1 and 0 <= y < n - 1 contains the coordinates [x, y], [x + 1, y], [x, y + 1], and [x + 1, y + 1].

Return a 0-indexed integer array arr of size 5 such that arr[i] is the number of blocks that contains exactly i black cells.

Example 1:

Input: m = 3, n = 3, coordinates = [[0,0]]
Output: [3,1,0,0,0]
Explanation: The grid looks like this:

There is only 1 block with one black cell, and it is the block starting with cell [0,0].
The other 3 blocks start with cells [0,1], [1,0] and [1,1]. They all have zero black cells.
Thus, we return [3,1,0,0,0].

Example 2:

Input: m = 3, n = 3, coordinates = [[0,0],[1,1],[0,2]]
Output: [0,2,2,0,0]
Explanation: The grid looks like this:

There are 2 blocks with two black cells (the ones starting with cell coordinates [0,0] and [0,1]).
The other 2 blocks have starting cell coordinates of [1,0] and [1,1]. They both have 1 black cell.
Therefore, we return [0,2,2,0,0].

Constraints:

2 <= m <= 10^5
2 <= n <= 10^5
0 <= coordinates.length <= 10^4
coordinates[i].length == 2
0 <= coordinates[i][0] < m
0 <= coordinates[i][1] < n
It is guaranteed that coordinates contains pairwise distinct coordinates.
*/

// 统计 0~4 个黑格子的块, ans = (m - 1) * (n - 1) - sum(ans[1:])
// 对于黑格子 (x, y) 需要统计左端点为 (x - 1, y - 1), (x - 1, y), (x, y - 1), (x, y), 的块中有多少黑格子
func countBlackBlocks(m int, n int, coordinates [][]int) []int64 {
	black := map[int]int{}
	for _, p := range coordinates {
		black[p[0]*100000+p[1]]++
	}
	ans := make([]int64, 5)
	visited := map[int]int{}
	for _, p := range coordinates {
		x, y := p[0], p[1]
		for i := max(0, x-1); i <= x && i < m-1; i++ {
			for j := max(0, y-1); j <= y && j < n-1; j++ {
				if visited[i*100000+j] == 0 {
					visited[i*100000+j] = 1
					cnt := black[i*100000+j] +
						black[i*100000+j+1] +
						black[(i+1)*100000+j] +
						black[(i+1)*100000+j+1]
					ans[cnt]++
				}
			}
		}
	}
	ans[0] = int64((m-1)*(n-1) - len(visited))
	return ans
}
