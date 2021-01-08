/**
 * 962. Maximum Width Ramp
 *
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 *
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 *
 * Example 2:
 *
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 *
 *
 * Note:
 *
 * 1. 2 <= A.length <= 50000
 * 2. 0 <= A[i] <= 50000
 */
public class MaximumWidthRamp {

    public int maxWidthRamp1(int[] A) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty() || A[i] < A[stack.peek()]) {
                stack.push(i);
            }
        }
        int ans = 0;
        for (int j = A.length - 1; j > ans; j--) {
            while (!stack.isEmpty() && A[j] >= A[stack.peek()]) {
                ans = Math.max(ans, j - stack.pop());
            }
        }
        return ans;
    }

    public int maxWidthRamp(int[] A) {
        int min = Integer.MAX_VALUE;
        int[] minArr = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            min = Math.min(min, A[i]);
            minArr[i] = min;
        }
        int ans = 0;
        for (int i = A.length - 1, j = A.length - 1; i >= 0; i--) {
            while (i < j && minArr[i] > A[j]) {
                j--;
            }
            ans = Math.max(ans, j - i);
        }
        return ans;
    }

}
