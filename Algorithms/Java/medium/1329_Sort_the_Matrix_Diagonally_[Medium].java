/**
 * 1329. Sort the Matrix Diagonally
 *
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class SortTheMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int left = 0; left < n; left++) {
            int up = 0;
            int right = Math.min(left + m - 1, n - 1);
            int down = right - left;
            sort(mat, up, left, down, right);
        }

        for (int up = 0; up < m; up++) {
            int left = 0;
            int down = Math.min(up + n - 1, m - 1);
            int right = down - up;
            sort(mat, up, left, down, right);
        }
        return mat;
    }

    private void sort(int[][] a, int up, int left, int down, int right) {
        if (left > right) {
            return;
        }
        int temp = a[up][left];
        int x1 = up, i = left, x2 = down, j = right;
        while (i != j) {
            while (a[x2][j] >= temp && j > i) {
                x2--;
                j--;
            }
            while (a[x1][i] <= temp && j > i) {
                x1++;
                i++;
            }
            if (i < j) {
                swap(a, x1, i, x2, j);
            }
        }
        swap(a, up, left, x1, i);
        sort(a, up, left, x1 - 1, i - 1);
        sort(a, x1 + 1, i + 1, down, right);
    }

    private void swap(int[][] a, int up, int left, int down, int right) {
        int temp = a[up][left];
        a[up][left] = a[down][right];
        a[down][right] = temp;
    }
}
