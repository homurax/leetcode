/**
 * 896. Monotonic Array
 *
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 *
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 *
 * Return true if and only if the given array A is monotonic.
 *
 * Example 1:
 *
 * Input: [1,2,2,3]
 * Output: true
 *
 * Example 2:
 *
 * Input: [6,5,4,4]
 * Output: true
 *
 * Example 3:
 *
 * Input: [1,3,2]
 * Output: false
 *
 * Example 4:
 *
 * Input: [1,2,4,5]
 * Output: true
 *
 * Example 5:
 *
 * Input: [1,1,1]
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 */
public class MonotonicArray {

    /**
     * 遍历两次
     */
    public boolean isMonotonic(int[] A) {

        boolean increasing = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                increasing = false;
                break;
            }
        }
        boolean decreasing = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                decreasing = false;
                break;
            }
        }

        return increasing || decreasing;
    }


    public boolean isMonotonic2(int[] A) {

        int store = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int compare = Integer.compare(A[i], A[i + 1]);
            if (compare != 0) {
                if (compare != store && store != 0) {
                    return false;
                }
                store = compare;
            }
        }
        return true;
    }
}
