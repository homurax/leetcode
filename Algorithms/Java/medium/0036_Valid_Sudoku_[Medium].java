/**
 * 36. Valid Sudoku
 *
 *
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 * Example 1:
 *
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *
 * Constraints:
 *
 * 1. board.length == 9
 * 2. board[i].length == 9
 * 3. board[i][j] is a digit or '.'.
 */
public class ValidSudoku {

    // 通过 boxIndex 来一次遍历
    // boxIndex 代表从左到右 从上到下的 9 个 9x9 box
    public boolean isValidSudoku0(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][] boxes = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int boxIndex = (i / 3) * 3 + j / 3;
                    if (++rows[i][num] == 2 || ++columns[j][num] == 2 || ++boxes[boxIndex][num] == 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // array 中每位数字记录改数字在 三个度量上的出现次数
    // 从高到低每 9 位分配给 box/j/i 来记录状态
    public boolean isValidSudoku(char[][] board) {
        int[] mux = new int[9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '1';
                int boxIndex = (i / 3) * 3 + j / 3;
                int mux1 = 0x01 << num;
                int mux2 = 0x01 << 9 << num;
                int mux3 = 0x01 << 9 << 9 << num;
                if ((mux[i] & mux1) != mux1 && (mux[j] & mux2) != mux2 && (mux[boxIndex] & mux3) != mux3) {
                    mux[i] = mux[i] | mux1;
                    mux[j] = mux[j] | mux2;
                    mux[boxIndex] = mux[boxIndex] | mux3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

}
