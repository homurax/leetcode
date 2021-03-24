/**
 * 775. Global and Local Inversions
 *
 *
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 *
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 *
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 *
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 *
 * Example 1:
 *
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 *
 * Example 2:
 *
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 * Note:
 *
 * 1. A will be a permutation of [0, 1, ..., A.length - 1].
 * 2. A will have length in range [1, 5000].
 * . The time limit for this problem has been reduced.
 */
public class GlobalAndLocalInversions {


    /**
     * 局部倒置也是全局倒置
     * 检查是否存在 A[i] > A[j], j - i > 1
     * 转化为检查 A[i] > min(A[i + 2:])
     */
    public boolean isIdealPermutation(int[] A) {
        int n = A.length;
        int min = n;
        for (int i = n - 1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if (A[i - 2] > min) {
                return false;
            }
        }
        return true;
    }


    /**
     * 如果排列数组为一个不存在非局部倒置的排列
     * 以 0 为例，如果 0 的下标大于等于 2 ，则必有 A[0] > A[2]，即存在非局部倒置
     * 0 的下标只能为 0 或者 1。如果 A[1] = 0，则 A[0] = 1，否则存在 A[0] > A[j]，即存在非局部倒置
     * 可进行数学归纳
     */
    public boolean isIdealPermutation2(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdealPermutation3(int[] A) {
        int sum = 0, currSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += i;
            currSum += A[i];
            if (currSum - sum > 1) {
                return false;
            }
        }
        return true;
    }

}
