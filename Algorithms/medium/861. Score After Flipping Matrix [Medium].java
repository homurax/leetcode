/**
 * 861. Score After Flipping Matrix
 *
 * We have a two dimensional matrix A where each value is 0 or 1.
 *
 * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
 *
 * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
 *
 * Return the highest possible score.
 *
 * Example 1:
 *
 * Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * Output: 39
 *
 * Explanation:
 * Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 *
 * Note:
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] is 0 or 1.
 */
public class ScoreAfterFlippingMatrix {

    /**
     *
     * 0 0 1 1      1 1 0 0      1 1 1 0      1 1 1 1
     * 1 0 1 0      1 0 1 0      1 0 0 0      1 0 0 1
     * 1 1 0 0      1 1 0 0      1 1 1 0      1 1 1 1
     *
     * 数尽可能大所以先反转行 保证第一列为1  A[i][0] = 1
     * 之后从第二列开始 第N列中0多则列反转 否则不需要反转
     */
    public static int matrixScore(int[][] A) {

        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < A[i].length; j++) {
                    A[i][j] = A[i][j] ^ 1;
                }
            }
        }
        for (int i = 1; i < A[0].length; i++) {
            int count = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 1) {
                    count++;
                } else {
                    count--;
                }
            }
            if (count < 0) {
                for (int j = 0; j < A.length; j++) {
                    A[j][i] = A[j][i] ^ 1;
                }
            }
        }

        int sum = 0;
        /*for (int i = 0; i < A[0].length; i++) {
            int coefficient = 0;
            for (int j = 0; j < A.length; j++) {
                coefficient += A[j][i];
            }
            sum += ((int) Math.pow(2, A[0].length - i - 1)) * coefficient;
        }*/

        for (int i = 0; i < A.length; i++) {
            int temp = 0;
            for (int j = 0; j < A[i].length; j++) {
                temp = temp * 2 + A[i][j];
            }
            sum += temp;
        }

        return sum;
    }


}
