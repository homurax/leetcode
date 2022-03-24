/*
542. 01 Matrix


Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

1. m == mat.length
2. n == mat[i].length
3. 1 <= m, n <= 10^4
4. 1 <= m * n <= 10^4
5. mat[i][j] is either 0 or 1.
6. There is at least one 0 in mat.
*/

func updateMatrix(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	ans := make([][]int, m)
	for i := 0; i < m; i++ {
		ans[i] = make([]int, n)
		for j := 0; j < n; j++ {
			if mat[i][j] != 0 {
				ans[i][j] = 10000
				if i != 0 {
					ans[i][j] = min(ans[i][j], ans[i-1][j]+1)
				}
				if j != 0 {
					ans[i][j] = min(ans[i][j], ans[i][j-1]+1)
				}
			}
		}
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if ans[i][j] > 1 {
				if i+1 < m {
					ans[i][j] = min(ans[i][j], ans[i+1][j]+1)
				}
				if j+1 < n {
					ans[i][j] = min(ans[i][j], ans[i][j+1]+1)
				}
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
