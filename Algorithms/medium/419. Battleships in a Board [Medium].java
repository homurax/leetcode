/**
 * 419. Battleships in a Board
 *
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 *
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 *
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 * This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 *
 * Follow up:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */
public class BattleshipsInABoard {


    /**
     * 修改了甲板值
     */
    public int countBattleships(char[][] board) {

        int row = board.length;
        int col = board[0].length;
        int ans = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    ans++;
                    board[i][j] = '.';
                    int m = i, n = j;
                    while (n + 1 < col && board[m][n + 1] == 'X') {
                        board[m][++n] = '.';
                    }
                    while (m + 1 < row && board[m + 1][n] == 'X') {
                        board[++m][n] = '.';
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 不修改甲板值
     * 计算有多少个甲板头就是有多少艘战舰
     */
    public int countBattleships2(char[][] board) {

        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    if ((j - 1 < 0 || board[i][j - 1] == '.')
                            && (i - 1 < 0 || board[i - 1][j] == '.')) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

}
