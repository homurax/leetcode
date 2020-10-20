/**
 * 59. Spiral Matrix II
 *
 *
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int x = 0, y = 0, flag = 0, val = 1;
        ans[x][y] = val++;
        while (val <= n * n) {
            flag %= 4;
            switch (flag) {
                case 0:
                    while (y + 1 < n && ans[x][y + 1] == 0) { // 右
                        ans[x][++y] = val++;
                    }
                    break;
                case 1:
                    while (x + 1 < n && ans[x + 1][y] == 0) { // 下
                        ans[++x][y] = val++;
                    }
                    break;
                case 2:
                    while (x - 1 >= 0 && ans[x - 1][y] == 0) { // 左
                        ans[--x][y] = val++;
                    }
                    break;
                case 3:
                    while (y - 1 >= 0 && ans[x][y - 1] == 0) { // 上
                        ans[x][--y] = val++;
                    }
                    break;
            }
            flag++;
        }
        return ans;
    }

}
