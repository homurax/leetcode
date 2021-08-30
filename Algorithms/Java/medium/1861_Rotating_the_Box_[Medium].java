/**
 * 1861. Rotating the Box
 *
 *
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 *
 *
 * Example 1:
 *
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 *
 *
 * Example 2:
 *
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 *
 *
 * Example 3:
 *
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 *
 *
 * Constraints:
 *
 * 1. m == box.length
 * 2. n == box[i].length
 * 3. 1 <= m, n <= 500
 * 4. box[i][j] is either '#', '*', or '.'.
 */
public class RotatingTheBox {

    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] ans = new char[n][m];
        for (int i = 0; i < m; i++) {
            char[] row = box[i];
            int objIdx = n;
            int colIdx = m - i - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (row[j] == '#') { // stone
                    if (j < objIdx - 1) { // drop
                        ans[--objIdx][colIdx] = '#';
                        ans[j][colIdx] = '.';
                    } else {
                        ans[j][colIdx] = '#';
                        objIdx = j;
                    }
                } else { // obstacle or empty
                    if (row[j] == '*') {
                        objIdx = j;
                    }
                    ans[j][colIdx] = row[j];
                }
            }
        }
        return ans;
    }


    public char[][] rotateTheBox2(char[][] box) {
        char[][] ans = new char[box[0].length][box.length];
        for (int i = 0; i < box.length; i++) {
            helper(box[box.length - 1 - i], ans, i);
        }
        return ans;
    }

    // rowIdx 当作写指针
    private void helper(char[] box, char[][] ans, int i) {
        int rowIdx = ans.length - 1;
        for (int j = box.length - 1; j >= 0; j--) {
            if (box[j] == '#') {
                ans[rowIdx--][i] = '#';
            } else if (box[j] == '*') {
                while (rowIdx > j) {
                    ans[rowIdx--][i] = '.';
                }
                ans[rowIdx--][i] = '*';
            }
        }
        while (rowIdx >= 0) {
            ans[rowIdx--][i] = '.';
        }
    }


}
