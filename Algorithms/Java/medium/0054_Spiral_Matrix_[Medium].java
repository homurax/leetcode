/**
 * 54. Spiral Matrix
 *
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * Constraints:
 *
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m, n <= 10
 * 4. -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; j++) { // 向右
                ans.add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; i++) { // 向下
                ans.add(matrix[i][right]);
            }
            if (left < right && top < bottom) {
                for (int j = right - 1; j > left; j--) { // 向左
                    ans.add(matrix[bottom][j]);
                }
                for (int i = bottom; i > top; i--) { // 向上
                    ans.add(matrix[i][left]);
                }
            }
            top++;
            right--;
            bottom--;
            left++;
        }
        return ans;
    }

}
