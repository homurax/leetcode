/**
 * 1504. Count Submatrices With All Ones
 *
 *
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,0,1],
 *               [1,1,0],
 *               [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 *
 *
 * Example 2:
 *
 * Input: mat = [[0,1,1,0],
 *               [0,1,1,1],
 *               [1,1,1,0]]
 * Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3.
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2.
 * There are 2 rectangles of side 3x1.
 * There is 1 rectangle of side 3x2.
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 *
 *
 * Example 3:
 *
 * Input: mat = [[1,1,1,1,1,1]]
 * Output: 21
 * Example 4:
 *
 * Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1. 1 <= rows <= 150
 * 2. 1 <= columns <= 150
 * 3. 0 <= mat[i][j] <= 1
 */
public class CountSubmatricesWithAllOnes {

    // row[i][j] 代表 (i,j) 向左延伸连续 1 的个数
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] row = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    row[i][j] = mat[i][j];
                } else if (mat[i][j] == 1) {
                    row[i][j] = row[i][j - 1] + 1;
                } else {
                    row[i][j] = 0;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = row[i][j];
                for (int r = i; r >= 0 && len != 0; r--) {
                    len = Math.min(len, row[r][j]);
                    count += len;
                }
            }
        }
        return count;
    }


}
