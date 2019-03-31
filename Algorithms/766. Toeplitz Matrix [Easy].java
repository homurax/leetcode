/**
 * 766. Toeplitz Matrix
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 *
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 *
 *
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,2,3,4],
 *   [5,1,2,3],
 *   [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 *
 * Example 2:
 *
 * Input:
 * matrix = [
 *   [1,2],
 *   [2,2]
 * ]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 *
 * Note:
 *
 * matrix will be a 2D array of integers.
 * matrix will have a number of rows and columns in range [1, 20].
 * matrix[i][j] will be integers in range [0, 99].
 *
 * Follow up:
 *
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 */
public class ToeplitzMatrix {


    /**
     * 对两个元素A(a, b) B(c, d)
     * 当满足 a-b = c-d 时 AB在同一对角线上
     */
    public boolean isToeplitzMatrix(int[][] matrix) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {
                if (!map.containsKey(i - j)) {
                    map.put(i - j, matrix[i][j]);
                } else if (map.get(i - j) != matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 与右下角比较 不同则return false
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix.length -1) break;
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == matrix[0].length - 1) break;
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }

        return true;
    }

}
