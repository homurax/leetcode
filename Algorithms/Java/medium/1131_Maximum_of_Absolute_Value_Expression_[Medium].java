/**
 * 1131. Maximum of Absolute Value Expression
 *
 *
 * Given two arrays of integers with equal lengths, return the maximum value of:
 *
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 *
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 *
 * Example 2:
 *
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 *
 *
 * Constraints:
 *
 * 1. 2 <= arr1.length == arr2.length <= 40000
 * 2. -10^6 <= arr1[i], arr2[i] <= 10^6
 */
public class MaximumOfAbsoluteValueExpression {

    /**
     * 绝对值展开
     *
     * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
     *
     * (arr1[i] + arr2[i] + i) - (arr1[j] + arr2[j] + j)
     * (arr1[i] + arr2[i] - i) - (arr1[j] + arr2[j] - j)
     * (arr1[i] - arr2[i] + i) - (arr1[j] - arr2[j] + j)
     * (arr1[i] - arr2[i] - i) - (arr1[j] - arr2[j] - j)
     * -(arr1[i] + arr2[i] + i) + (arr1[j] + arr2[j] + j)
     * -(arr1[i] + arr2[i] - i) + (arr1[j] + arr2[j] - j)
     * -(arr1[i] - arr2[i] + i) + (arr1[j] - arr2[j] + j)
     * -(arr1[i] - arr2[i] - i) + (arr1[j] - arr2[j] - j)
     *
     * A = arr1[i] + arr2[i] + i
     * B = arr1[i] + arr2[i] - i
     * C = arr1[i] - arr2[i] + i
     * D = arr1[i] - arr2[i] - i
     *
     * max(max(A) - min(A), max(B) - min(B), max(C) - min(C), max(D) - min(D))
     */
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int min_a = Integer.MAX_VALUE, min_b = Integer.MAX_VALUE, min_c = Integer.MAX_VALUE, min_d = Integer.MAX_VALUE;
        int max_a = Integer.MIN_VALUE, max_b = Integer.MIN_VALUE, max_c = Integer.MIN_VALUE, max_d = Integer.MIN_VALUE;
        for (int i = 0; i < arr1.length; i++) {
            max_a = Math.max(max_a, arr1[i] + arr2[i] + i);
            min_a = Math.min(min_a, arr1[i] + arr2[i] + i);
            max_b = Math.max(max_b, arr1[i] + arr2[i] - i);
            min_b = Math.min(min_b, arr1[i] + arr2[i] - i);
            max_c = Math.max(max_c, arr1[i] - arr2[i] + i);
            min_c = Math.min(min_c, arr1[i] - arr2[i] + i);
            max_d = Math.max(max_d, arr1[i] - arr2[i] - i);
            min_d = Math.min(min_d, arr1[i] - arr2[i] - i);
        }
        return Math.max(Math.max(max_a - min_a, max_b - min_b), Math.max(max_c - min_c, max_d - min_d));
    }

}
