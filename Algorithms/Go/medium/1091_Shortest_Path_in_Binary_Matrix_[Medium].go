/*
1091. Shortest Path in Binary Matrix


Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


Constraints:

1. n == grid.length
2. n == grid[i].length
3. 1 <= n <= 100
4. grid[i][j] is 0 or 1
*/

func shortestPathBinaryMatrix(grid [][]int) int {
	if grid[0][0] == 1 {
		return -1
	}
	n := len(grid)
	if n == 1 {
		return 1
	}
	queue := []int{0}
	visited := make([]bool, n*n)
	visited[0] = true
	path := 1
	directions := [][]int{{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	for len(queue) > 0 {
		q := queue
		queue = []int{}
		for _, v := range q {
			x, y := v/n, v%n
			for _, d := range directions {
				a, b := x+d[0], y+d[1]
				if a >= 0 && a < n && b >= 0 && b < n && grid[a][b] == 0 && !visited[a*n+b] {
					if a == n-1 && b == n-1 {
						return path + 1
					}
					queue = append(queue, a*n+b)
					visited[a*n+b] = true
				}
			}
		}
		path++
	}
	return -1
}
