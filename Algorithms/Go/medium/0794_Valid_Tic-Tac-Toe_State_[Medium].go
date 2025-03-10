/*
794. Valid Tic-Tac-Toe State

Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares ' '.
The first player always places 'X' characters, while the second player always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never filled ones.
The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.

Example 1:

Input: board = ["O  ","   ","   "]
Output: false
Explanation: The first player always plays "X".

Example 2:

Input: board = ["XOX"," X ","   "]
Output: false
Explanation: Players take turns making moves.
Example 3:

Input: board = ["XOX","O O","XOX"]
Output: true

Constraints:

board.length == 3
board[i].length == 3
board[i][j] is either 'X', 'O', or ' '.
*/
func validTicTacToe(board []string) bool {
	x, o := 0, 0
	for _, row := range board {
		x += strings.Count(row, "X")
		o += strings.Count(row, "O")
	}
	a, b := win(board, 'X'), win(board, 'O')
	if o > x || x-o > 1 {
		return false
	}
	if a && x <= o {
		return false
	}
	if b && o != x {
		return false
	}
	if a && b {
		return false
	}
	return true
}

func win(board []string, p byte) bool {
	for i := 0; i < 3; i++ {
		if board[i][0] == p && board[i][1] == p && board[i][2] == p ||
			board[0][i] == p && board[1][i] == p && board[2][i] == p {
			return true
		}
	}
	return board[0][0] == p && board[1][1] == p && board[2][2] == p ||
		board[0][2] == p && board[1][1] == p && board[2][0] == p
}
