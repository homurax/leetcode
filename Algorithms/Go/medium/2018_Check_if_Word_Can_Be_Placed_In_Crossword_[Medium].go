/*
2018. Check if Word Can Be Placed In Crossword


You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.

A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:

It does not occupy a cell containing the character '#'.
The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
Given a string word, return true if word can be placed in board, or false otherwise.



Example 1:


Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
Output: true
Explanation: The word "abc" can be placed as shown above (top to bottom).


Example 2:


Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
Output: false
Explanation: It is impossible to place the word because there will always be a space/letter above or below it.


Example 3:


Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
Output: true
Explanation: The word "ca" can be placed as shown above (right to left).


Constraints:

m == board.length
n == board[i].length
1 <= m * n <= 2 * 10^5
board[i][j] will be ' ', '#', or a lowercase English letter.
1 <= word.length <= max(m, n)
word will contain only lowercase English letters.
*/
func placeWordInCrossword(board [][]byte, word string) bool {
	m, n, k := len(board), len(board[0]), len(word)
	for _, row := range board {
		for j := 0; j < n; j++ {
			if row[j] == '#' {
				continue
			}
			j0, ok1, ok2 := j, true, true
			for ; j < n && row[j] != '#'; j++ {
				if j-j0 >= k || row[j] != ' ' && row[j] != word[j-j0] {
					ok1 = false
				}
				if j-j0 >= k || row[j] != ' ' && row[j] != word[k-1-j+j0] {
					ok2 = false
				}
			}
			if (ok1 || ok2) && j-j0 == k {
				return true
			}
		}
	}
	for j := 0; j < n; j++ {
		for i := 0; i < m; i++ {
			if board[i][j] == '#' {
				continue
			}
			i0, ok1, ok2 := i, true, true
			for ; i < m && board[i][j] != '#'; i++ {
				if i-i0 >= k || board[i][j] != ' ' && board[i][j] != word[i-i0] {
					ok1 = false
				}
				if i-i0 >= k || board[i][j] != ' ' && board[i][j] != word[k-1-i+i0] {
					ok2 = false
				}
			}
			if (ok1 || ok2) && i-i0 == k {
				return true
			}
		}
	}
	return false
}
