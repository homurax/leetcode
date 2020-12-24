/**
 * 870. Advantage Shuffle
 *
 *
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 *
 * Return any permutation of A that maximizes its advantage with respect to B.
 *
 *
 * Example 1:
 *
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 *
 * Example 2:
 *
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 *
 *
 * Note:
 *
 * 1. 1 <= A.length = B.length <= 10000
 * 2. 0 <= A[i] <= 10^9
 * 3. 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {

    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int len = A.length;
        long[] aux = new long[len];
        for (int i = 0; i < len; i++) {
            aux[i] = (long) B[i] * 10000 + i;
        }
        Arrays.sort(aux);
        int left = 0;
        int right = len - 1;
        for (int num : A) {
            if (num > aux[left] / 10000) {
                B[(int) (aux[left++] % 10000)] = num;
            } else {
                B[(int) (aux[right--] % 10000)] = num;
            }
        }
        return B;
    }

}
