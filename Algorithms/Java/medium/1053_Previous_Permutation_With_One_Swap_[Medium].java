/**
 * 1053. Previous Permutation With One Swap
 *
 *
 * Given an array of positive integers arr (not necessarily distinct), return the lexicographically largest permutation that is smaller than arr, that can be made with exactly one swap (A swap exchanges the positions of two numbers arr[i] and arr[j]). If it cannot be done, then return the same array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,2,1]
 * Output: [3,1,2]
 * Explanation: Swapping 2 and 1.
 *
 * Example 2:
 *
 * Input: arr = [1,1,5]
 * Output: [1,1,5]
 * Explanation: This is already the smallest permutation.
 *
 * Example 3:
 *
 * Input: arr = [1,9,4,6,7]
 * Output: [1,7,4,6,9]
 * Explanation: Swapping 9 and 7.
 *
 * Example 4:
 *
 * Input: arr = [3,1,1,3]
 * Output: [1,3,1,3]
 * Explanation: Swapping 1 and 3.
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 10^4
 * 2. 1 <= arr[i] <= 10^4
 */
public class PreviousPermutationWithOneSwap {

    /**
     * 为了让交换后字典序小于 A：
     *      需要交换的 i,j -> A[i] > A[j], (j > i)
     * 为了交换后是小于 A 的最大字典序：
     *      字典序最大 -> i,j 尽量靠后，找最大的 A[j], (i < j < n)
     *      最大的 A[j] 如果有多个，取最靠前的
     */
    public int[] prevPermOpt1(int[] A) {
        int n = A.length;
        for (int l = n - 1; l >= 0; l--) {
            int idx = -1;
            for (int r = n - 1; r > l; r--) {
                if (A[l] > A[r]) {
                    if (idx == -1 || A[r] >= A[idx]) {
                        idx = r;
                    }
                }
            }
            if (idx != -1) {
                A[l] ^= A[idx];
                A[idx] ^= A[l];
                A[l] ^= A[idx];
                return A;
            }
        }
        return A;
    }

}
