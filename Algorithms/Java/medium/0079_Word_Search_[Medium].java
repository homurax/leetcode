/**
 * 79. Word Search
 *
 *
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 *
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 *
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. m == board.length
 * 2. n = board[i].length
 * 3. 1 <= m, n <= 6
 * 4. 1 <= word.length <= 15
 * 5. board and word consists of only lowercase and uppercase English letters.
 *
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {


    private int m, n;
    private char[][] board;
    private char[] targets;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        this.board = board;
        this.targets = word.toCharArray();
        this.visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, word.length() - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int idx) {
        if (i < 0 || i >= m || j < 0 || j >= n || idx < 0) {
            return idx < 0;
        }
        if (visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        boolean flag = false;
        if (board[i][j] == targets[idx]) {
            flag = dfs(i + 1, j, idx - 1) || dfs(i - 1, j, idx - 1) || dfs(i, j + 1, idx - 1) || dfs(i, j - 1, idx - 1);
        }
        visited[i][j] = false;
        return flag;
    }

}
