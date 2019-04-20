/**
 * 1005. Maximize Sum Of Array After K Negations
 *
 * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
 *
 * Return the largest possible sum of the array after modifying it in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [4,2,3], K = 1
 * Output: 5
 * Explanation: Choose indices (1,) and A becomes [4,-2,3].
 * Example 2:
 *
 * Input: A = [3,-1,0,2], K = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
 * Example 3:
 *
 * Input: A = [2,-3,-1,5,-4], K = 2
 * Output: 13
 * Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * 1 <= K <= 10000
 * -100 <= A[i] <= 100
 */
public class MaximizeSumOfArrayAfterKNegations {

    /**
     * 尽可能把负数置为正数 全部为正数时K仍不为0
     * K为偶数时不需要处理 K为奇数且MIN不为0时需要考虑把数组中最小正数置为负数
     */
    public int largestSumAfterKNegations(int[] A, int K) {

        Arrays.sort(A);
        int sum;
        int min = A[0];
        if (min >= 0) {
            sum = sum(A);
            if ((K & 1) == 1 && A[0] != 0) {
                sum -= A[0] * 2;
            }
        } else {
            for (int i = 0; i < A.length; i++) {
                if (A[i] < 0) {
                    if (K > 0) {
                        A[i] = -A[i];
                        K--;
                    } else {
                        break;
                    }
                } else if (A[i] >= 0 || K == 0) {
                    break;
                }
            }
            sum = sum(A);
            if (K > 0) {
                Arrays.sort(A);
                if ((K & 1) == 1 && A[0] != 0) {
                    sum -= A[0] * 2;
                }
            }
        }
        return sum;
    }
    private int sum(int[] A) {
        int sum = 0;
        for (int i : A) {
            sum += i;
        }
        return sum;
    }


    /**
     * 先打表 同时求和
     * 遍历负数部分，对SUM修正
     * 如果K仍不为0 则找到最小正数 修正SUM
     */
    public int largestSumAfterKNegations2(int[] A, int K) {

        int[] table = new int[201];
        int sum = 0;
        for (int i : A) {
            sum += i;
            table[i + 100]++;
        }

        int prev = 101;
        for (int i = 0; i < 100 && K > 0; i++) {
            int n = table[i];
            if (n > 0) {
                int val = 100 - i;
                prev = val;
                if (n > K) {
                    n = K;
                }
                sum += val * n * 2;
                K -= n;
            }
        }
        if ((K & 1) == 1) {
            prev += 100;
            for (int i = 100; i < prev; i++) {
                if (table[i] > 0) {
                    prev = i;
                    break;
                }
            }
            sum -= (prev - 100) * 2;
        }
        return sum;
    }

}
