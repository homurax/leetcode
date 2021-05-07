/**
 * 74. Search a 2D Matrix
 *
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m, n <= 100
 * 4. -10^4 <= matrix[i][j], target <= 10^4
 */
public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (target >= row[0] && target <= row[row.length - 1]) {
                return Arrays.binarySearch(row, target) >= 0;
            }
        }
        return false;
    }

}
