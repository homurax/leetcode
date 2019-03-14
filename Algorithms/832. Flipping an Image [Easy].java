/**
 * 832. Flipping an Image
 *
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 *
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 *
 * Example 1:
 *
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * Example 2:
 *
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * Notes:
 *
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 */
<<<<<<< HEAD
public class FlippingAnImage {

    /**
     * 小技巧
     *
     * 奇数长度存在一个中位数 偶数长度则为两个
     *
     * length / 2           不会遍历到奇数时中位数
     * (length + 1) / 2     均会遍历到，偶数时为第一中位数截至
     */

    /**
     * 双指针思想 首尾交换 并与1做异或操作 即0 1互换
     * 循环中没有遍历到中位数（当length为奇数时） 中位数单独处理与1做异或即可 不需要交换
     */
=======
 
/**
 * 小技巧
 *
 * 奇数长度存在一个中位数 偶数长度则为两个
 *
 * length / 2           不会遍历到奇数时中位数
 * (length + 1) / 2     均会遍历到，偶数时为第一中位数截至
 */
class Solution {

	/**
	 * 双指针思想 首尾交换 并与1做异或操作 即0 1互换
	 * 循环中没有遍历到中位数（当length为奇数时） 中位数单独处理与1做异或即可 不需要交换
	 */
>>>>>>> 3f0efc2... add 832. Flipping an Image
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] num : A) {
            int rear = num.length - 1;
            for (int i = 0; i < num.length/2; i++) {
                num[i] = (num[i] ^ 1) ^ (num[rear] ^ 1);
                num[rear] = num[i] ^ (num[rear] ^ 1);
                num[i] = num[i] ^ num[rear];
                rear--;
            }
            if ((num.length & 1) == 1) {
                num[num.length/2] = num[num.length/2] ^ 1;
            }
        }
        return A;
    }
<<<<<<< HEAD
=======
}

class Solution {
>>>>>>> 3f0efc2... add 832. Flipping an Image

    /**
     * 与第一个没有太大差别  循环中遍历到了中位数（当length为奇数时）
     * 1. length - 1 - i 即为第一个中的所定义的 rear
     * 2. 由于中位数时 i == length - 1 - i  所以交换的部分中使用了中间变量
     */
<<<<<<< HEAD
    public int[][] flipAndInvertImage2(int[][] A) {
=======
    public int[][] flipAndInvertImage(int[][] A) {
>>>>>>> 3f0efc2... add 832. Flipping an Image

        int length = A[0].length;
        for (int[] row : A) {
            for (int i = 0; i < (length + 1) / 2; i++) {
                int tmp = row[i] ^ 1;
                row[i] = row[length - 1 - i] ^ 1;
                row[length - 1 - i] = tmp;
            }
        }
        return A;
    }

}
