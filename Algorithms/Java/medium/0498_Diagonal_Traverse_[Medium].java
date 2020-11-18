/**
 * 498. Diagonal Traverse
 *
 *
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 *
 *
 * Example:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * Output:  [1,2,4,7,5,3,6,8,9]
 *
 * Explanation:
 *
 *
 * Note:
 *
 * The total number of elements of the given matrix will not exceed 10,000.
 */
public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int m = matrix.length, n = matrix[0].length, scan = m + n - 1;
        int[] ans = new int[m * n];
        int index = 0;

        for (int i = 0; i < scan; i++) {
            if ((i & 1) == 0) { // up
                int row = i < m ? i : m - 1;
                int column = i < m ? 0 : i - (m - 1);
                while (row >= 0 && column < n) {
                    ans[index++] = matrix[row--][column++];
                }
            } else { // down
                int row = i < n ? 0 : i - (n - 1);
                int column = i < n ? i : n - 1;
                while (row < m && column >= 0) {
                    ans[index++] = matrix[row++][column--];
                }
            }
        }
        return ans;
    }


}
