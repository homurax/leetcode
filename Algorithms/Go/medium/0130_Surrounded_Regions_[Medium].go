/*
130. Surrounded Regions


Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.



Example 1:


Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.


Example 2:

Input: board = [["X"]]
Output: [["X"]]


Constraints:

1. m == board.length
2. n == board[i].length
3. 1 <= m, n <= 200
4. board[i][j] is 'X' or 'O'.
*/

func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	var dfs func(board [][]byte, i int, j int)
	dfs = func(board [][]byte, i int, j int) {
		if i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O' {
			return
		}
		board[i][j] = 'A'
		dfs(board, i-1, j)
		dfs(board, i+1, j)
		dfs(board, i, j-1)
		dfs(board, i, j+1)
	}
	for i := 0; i < m; i++ {
		dfs(board, i, 0)
		dfs(board, i, n-1)
	}
	for i := 1; i < n-1; i++ {
		dfs(board, 0, i)
		dfs(board, m-1, i)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'A' {
				board[i][j] = 'O'
			} else if board[i][j] == 'O' {
				board[i][j] = 'X'
			}
		}
	}
}
