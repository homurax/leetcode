/**
 * 915. Partition Array into Disjoint Intervals
 *
 *
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 *
 *
 * Example 1:
 *
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 *
 * Example 2:
 *
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 *
 *
 * Note:
 *
 * 1. 2 <= A.length <= 30000
 * 2. 0 <= A[i] <= 10^6
 * 3. It is guaranteed there is at least one way to partition A as described.
 */
public class PartitionArrayIntoDisjointIntervals {

    public int partitionDisjoint1(int[] A) {
        int n = A.length;
        int[] leftMax = new int[n];
        int[] rightMin = new int[n];
        int max = 0, min = 1000000;
        for (int i = 0; i < n; i++) {
            leftMax[i] = max = Math.max(max, A[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            rightMin[i] = min = Math.min(min, A[i]);
        }
        int idx = 0;
        while (idx < n - 1) {
            if (leftMax[idx] <= rightMin[idx + 1]) {
                break;
            }
            idx++;
        }
        return idx + 1;
    }

    public int partitionDisjoint(int[] A) {
        int[] rightMin = new int[A.length];
        /*int min = 1000000;
        for (int i = A.length - 1; i >= 0; i--) {
            rightMin[i] = min = Math.min(min, A[i]);
        }*/
        rightMin[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i - 1], A[i]);
        }
        int idx = 0, max = 0;
        while (idx < A.length - 1) {
            max = Math.max(max, A[idx]);
            if (max <= rightMin[idx + 1]) {
                break;
            }
            idx++;
        }
        return idx + 1;
    }

}
