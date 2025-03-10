/**
 * 794. Valid Tic-Tac-Toe State
 *
 *
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares ' '.
 * The first player always places 'X' characters, while the second player always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 *
 *
 * Example 1:
 *
 *
 * Input: board = ["O  ","   ","   "]
 * Output: false
 * Explanation: The first player always plays "X".
 *
 *
 * Example 2:
 *
 *
 * Input: board = ["XOX"," X ","   "]
 * Output: false
 * Explanation: Players take turns making moves.
 * Example 3:
 *
 *
 * Input: board = ["XOX","O O","XOX"]
 * Output: true
 *
 *
 * Constraints:
 *
 * board.length == 3
 * board[i].length == 3
 * board[i][j] is either 'X', 'O', or ' '.
 */
public class ValidTicTacToeState {

    // 0 <= cnt_X - cnt_O <= 1
    // X 胜, cnt_X - cnt_O == 1
    // O 胜, cnt_X == cnt_O
    public boolean validTicTacToe(String[] board) {
        char[][] cs = new char[3][3];
        int x = 0, o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'X') {
                    x++;
                } else if (c == 'O') {
                    o++;
                }
                cs[i][j] = c;
            }
        }
        boolean a = check(cs, 'X'), b = check(cs, 'O');
        if (o > x || x - o > 1) {
            return false;
        }
        if (a && x <= o) {
            return false;
        }
        if (b && o != x) {
            return false;
        }
        if (a && b) {
            return false;
        }
        return true;
    }

    private boolean check(char[][] cs, char c) {
        for (int i = 0; i < 3; i++) { // 横竖
            if (cs[i][0] == c && cs[i][1] == c && cs[i][2] == c) {
                return true;
            }
            if (cs[0][i] == c && cs[1][i] == c && cs[2][i] == c) {
                return true;
            }
        }
        boolean a = true, b = true;
        for (int i = 0; i < 3; i++) { // 斜
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    a &= cs[i][j] == c;
                }
                if (i + j == 2) {
                    b &= cs[i][j] == c;
                }
            }
        }
        return a || b;
    }

}
