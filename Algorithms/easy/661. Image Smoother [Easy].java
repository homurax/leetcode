/**
 * 661. Image Smoother
 *
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 * Example 1:
 * Input:
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * Output:
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 * Note:
 * The value in the given matrix is in the range of [0, 255].
 * The length and width of the given matrix are in the range of [1, 150].
 */
public class ImageSmoother {

    public int[][] imageSmoother(int[][] M) {

        int a = M.length;
        int b = M[0].length;
        int[][] ans = new int[a][b];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                ans[i][j] = smoother(M, i, j);
            }
        }
        return ans;
    }
    private int smoother(int[][] M, int i, int j) {

        int sum = M[i][j];
        int count = 1;
        int a = M.length;
        int b = M[i].length;

        if (i - 1 >= 0) {
            count++;
            sum += M[i - 1][j]; // 上
            if (j - 1 >= 0) { count++; sum += M[i - 1][j - 1]; } // 左上
            if (j + 1 < b) { count++; sum += M[i - 1][j + 1]; } // 右上
        }
        if (i + 1 < a) {
            count++;
            sum += M[i + 1][j]; // 下
            if (j - 1 >= 0) { count++; sum += M[i + 1][j - 1]; } // 左下
            if (j + 1 < b) { count++; sum += M[i + 1][j + 1]; } // 右下
        }

        if (j - 1 >= 0) { count++; sum += M[i][j - 1]; } // 左
        if (j + 1 < b) {  count++; sum += M[i][j + 1]; } // 右

        return sum / count;
    }


    public int[][] imageSmoother2(int[][] M) {

        int R = M.length;
        int C = M[0].length;
        int[][] ans = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                int count = 0;
                for (int i = r - 1; i <= r + 1; i++) {
                    for (int j = c - 1; j <= c + 1; j++) {
                        if (0 <= i && i < R
                                && 0 <= j && j < C) {
                            ans[r][c] += M[i][j];
                            count++;
                        }
                    }
                }
                ans[r][c] /= count;

            }
        }
        return ans;
    }
}
