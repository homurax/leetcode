/*
3001. Minimum Moves to Capture The Queen

There is a 1-indexed 8 x 8 chessboard containing 3 pieces.

You are given 6 integers a, b, c, d, e, and f where:

(a, b) denotes the position of the white rook.
(c, d) denotes the position of the white bishop.
(e, f) denotes the position of the black queen.
Given that you can only move the white pieces, return the minimum number of moves required to capture the black queen.

Note that:

Rooks can move any number of squares either vertically or horizontally, but cannot jump over other pieces.
Bishops can move any number of squares diagonally, but cannot jump over other pieces.
A rook or a bishop can capture the queen if it is located in a square that they can move to.
The queen does not move.

Example 1:

Input: a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
Output: 2
Explanation: We can capture the black queen in two moves by moving the white rook to (1, 3) then to (2, 3).
It is impossible to capture the black queen in less than two moves since it is not being attacked by any of the pieces at the beginning.

Example 2:

Input: a = 5, b = 3, c = 3, d = 4, e = 5, f = 2
Output: 1
Explanation: We can capture the black queen in a single move by doing one of the following:
- Move the white rook to (5, 2).
- Move the white bishop to (5, 2).

Constraints:

1 <= a, b, c, d, e, f <= 8
No two pieces are on the same square.
*/
func inBetween(l, m, r int) bool {
	return min(l, r) < m && m < max(l, r)
}

// 只会为 2 次或者 1 次
// 1 次: 车直接捕获（象不在路径上）、象直接捕获（车不在路径上）
// 2 次: 上述存在路径阻碍的情况下移动阻碍的一方、或者车移动两次
func minMovesToCaptureTheQueen(a, b, c, d, e, f int) int {
	if a == e && (c != e || !inBetween(b, d, f)) || // 车直接捕获到皇后（同一行）
		b == f && (d != f || !inBetween(a, c, e)) || // 车直接捕获到皇后（同一列）
		c+d == e+f && (a+b != e+f || !inBetween(c, a, e)) || // 象直接捕获到皇后
		c-d == e-f && (a-b != e-f || !inBetween(c, a, e)) {
		return 1
	}
	return 2
}
