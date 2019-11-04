/**
 * 999. Available Captures for Rook
 *
 * On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.
 *
 * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
 *
 * Return the number of pawns the rook can capture in one move.
 * Note:
 *
 *  1. board.length == board[i].length == 8
 *  2. board[i][j] is either 'R', '.', 'B', or 'p'
 *  3. There is exactly one cell with board[i][j] == 'R'
 *
 */
public class AvailableCapturesForRook {

    /**
     * R => 白车
     * . => 空方块
     * B => 白象
     * p => 黑卒
     * 找到车的位置后上下左右四个方向查找即可 遇到白象、黑卒停止
     */
    public int numRookCaptures(char[][] board) {

        int x = 0, y = 0, count = 0;

        // 先车
        label: for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break label;
                }
            }
        }

        // 左
        for (int i = y - 1; i >= 0; i--){
            if (board[x][i] == 'B') {
                break;
            } else if (board[x][i] == 'p') {
                count++;
                break;
            }
        }

        // 右
        for (int i = y + 1; i < 8; i++){
            if (board[x][i] == 'B') {
                break;
            } else if (board[x][i] == 'p') {
                count++;
                break;
            }
        }

        // 上
        for (int i = x - 1; i >= 0; i--){
            if(board[i][y] == 'B'){
                break;
            } else if(board[i][y] == 'p'){
                count++;
                break;
            }
        }

        // 下
        for (int i = x + 1; i < 8; i++){
            if(board[i][y] == 'B'){
                break;
            } else if(board[i][y] == 'p'){
                count++;
                break;
            }
        }

        return count;
    }
}
