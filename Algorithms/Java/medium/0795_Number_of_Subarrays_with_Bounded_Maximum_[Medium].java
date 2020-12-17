/**
 * 795. Number of Subarrays with Bounded Maximum
 *
 *
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 *
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 *
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 *
 * Note:
 *
 * 1. L, R  and A[i] will be an integer in the range [0, 10^9].
 * 2. The length of A will be in the range of [1, 50000].
 */
public class NumberOfSubarraysWithBoundedMaximum {

    /**
     *            /  0, (A[i] < L)
     * mark(A[i]) -  1, (L <= A[i] <= R)
     *            \  2, (A[i] > R)
     *
     * mark(A) -> [0, 0, 1, 2, 2, 1, 0, 2, 0]
     * 拆分为 [0, 0, 1] [1, 0] [0]
     * ans = sum(每个只包含 0 或 1 的数组中，至少包含一个 1 的子数组数量)
     * count(num) -> 所有元素都小于等于 num 的子数组数量。
     * ans = count(R) - count(L-1)
     */
    public int numSubarrayBoundedMax1(int[] A, int L, int R) {
        return count(A, R) - count(A, L - 1);
    }

    private int count(int[] A, int bound) {
        int ans = 0, cur = 0;
        for (int num : A) {
            cur = num <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }

    public int numSubarrayBoundedMax2(int[] A, int L, int R) {
        int left = -1, right = -1, ans = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > R) {
                left = i;
                right = i;
                continue;
            }
            if (A[i] >= L) {
                right = i;
            }
            ans += right - left;
        }
        return ans;
    }

}
