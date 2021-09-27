/**
 * 378. Kth Smallest Element in a Sorted Matrix
 *
 *
 * Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *
 * Constraints:
 *
 * 1. n == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= n <= 300
 * 4. -10^9 <= matrix[i][j] <= 10^9
 * 5. All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 6. 1 <= k <= n^2
 */
public class KthSmallestElementInASortedMatrix {

    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        if (k == 1) {
            return matrix[0][0];
        }
        if (k == n * n) {
            return matrix[n - 1][n - 1];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int row = 0; row < n; row++) {
            // row[0], row, col
            pq.offer(new int[]{matrix[row][0], row, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] curr = pq.remove();
            int row = curr[1], col = curr[2];
            if (col != n - 1) {
                pq.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }
        }
        return pq.remove()[0];
    }

    // 二分
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if (k == 1) {
            return matrix[0][0];
        }
        if (k == n * n) {
            return matrix[n - 1][n - 1];
        }
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int i = n - 1, j = 0;
            int count = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) { // 向右走
                    count += i + 1; // 当前列
                    j++;
                } else { // 向上走
                    i--;
                }
            }
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
