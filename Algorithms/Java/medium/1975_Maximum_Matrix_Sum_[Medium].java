/**
 * 1975. Maximum Matrix Sum
 *
 *
 * You are given an n x n integer matrix. You can do the following operation any number of times:
 *
 * Choose any two adjacent elements of matrix and multiply each of them by -1.
 * Two elements are considered adjacent if and only if they share a border.
 *
 * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 *
 *
 * Example 2:
 *
 *
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can follow the following step to reach sum equals 16:
 * - Multiply the 2 last elements in the second row by -1.
 *
 *
 * Constraints:
 *
 * 1. n == matrix.length == matrix[i].length
 * 2. 2 <= n <= 250
 * 3. -10^5 <= matrix[i][j] <= 10^5
 */
public class MaximumMatrixSum {

    // 偶数个负数可以全变为正数
    // 奇数个负数将最小值当作负数
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int min = Integer.MAX_VALUE, count = 0;
        for (int[] ints : matrix) {
            for (int num : ints) {
                sum += Math.abs(num);
                if (num < 0) {
                    count++;
                }
                min = Math.min(min, Math.abs(num));
            }
        }
        if (count % 2 == 1) {
            sum -= 2L * min;
        }
        return sum;
    }

}
