/**
 * 240. Search a 2D Matrix II
 *
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 *
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= n, m <= 300
 * 4. -10^9 <= matrix[i][j] <= 10^9
 * 5. All the integers in each row are sorted in ascending order.
 * 6. All the integers in each column are sorted in ascending order.
 * 7. -10^9 <= target <= 10^9
 */
public class SearchA2DMatrixII {

    // 从右上角开始
    // matrix[i][j] > target 时，只需要考虑减少 j
    // matrix[i][j] < target 时，只需要考虑增加 i
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

}
