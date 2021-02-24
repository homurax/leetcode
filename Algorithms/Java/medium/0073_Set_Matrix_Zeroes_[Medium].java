/**
 * 73. Set Matrix Zeroes
 *
 *
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 *  Example 2:
 *
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 * Constraints:
 *
 * 1. m == matrix.length
 * 2. n == matrix[0].length
 * 3. 1 <= m, n <= 200
 * 4. -2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class SetMatrixZeroes {

    // 记录需要被置零的行列 需要 O(m + n) 的额外空间
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // row: [0, m - 1], col: [m, m + n - 1]
        int[] record = new int[m + n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    record[i] = 1;
                    record[m + j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (record[i] == 1) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int i = m; i < m + n; i++) {
            if (record[i] == 1) {
                for (int[] row : matrix) {
                    row[i - m] = 0;
                }
            }
        }
    }


    // 每行每列的第一个元素当作标记数组 matrix[0][0] 需要区分
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean rowFlag = false, colFlag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        rowFlag = true;
                    }
                    if (j == 0) {
                        colFlag = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowFlag) {
            Arrays.fill(matrix[0], 0);
        }
        if (colFlag) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
