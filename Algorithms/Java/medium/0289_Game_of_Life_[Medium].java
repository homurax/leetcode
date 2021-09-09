/**
 * 289. Game of Life
 *
 *
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 *
 *
 * Example 1:
 *
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 *
 * Example 2:
 *
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 *
 *
 * Constraints:
 *
 * 1. m == board.length
 * 2. n == board[i].length
 * 3. 1 <= m, n <= 25
 * 4. board[i][j] is 0 or 1.
 *
 *
 * Follow up:
 *
 * 1. Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
 * 2. In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */
public class GameOfLife {


    // diff 数组记录变化
    public void gameOfLife1(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] diff = new int[m * n];
        int[] directions = new int[]{-1, 0, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int x : directions) {
                    for (int y : directions) {
                        if (x == 0 && y == 0) {
                            continue;
                        }
                        if (i + x >= 0 && i + x < m && j + y >= 0 && j + y < n) {
                            count += board[i + x][j + y] == 1 ? 1 : 0;
                        }
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    diff[i * n + j] = -1;
                } else if (board[i][j] == 0 && count == 3) {
                    diff[i * n + j] = 1;
                }
            }
        }

        for (int idx = 0; idx < diff.length; idx++) {
            board[idx / n][idx % n] += diff[idx];
        }
    }


    /**
     * 使用复合状态
     *
     *  2: 0 -> 1
     * -1: 1 -> 0
     *
     * 注意 count live 时就是 Math.abs(board[r][c]) == 1
     */
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[] directions = new int[]{-1, 0, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int x : directions) {
                    for (int y : directions) {
                        if (x == 0 && y == 0) {
                            continue;
                        }
                        int r = i + x, c = j + y;
                        if (r >= 0 && r < m && c >= 0 && c < n && Math.abs(board[r][c]) == 1) {
                            count++;
                        }
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    board[i][j] = -1;
                } else if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

}
