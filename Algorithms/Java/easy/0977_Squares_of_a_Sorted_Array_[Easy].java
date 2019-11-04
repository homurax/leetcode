/**
 * 977. Squares of a Sorted Array
 *
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 *
 * Example 1:
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 *
 * Example 2:
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 *
 * Note:
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 */
public class Solution {

    public int[] sortedSquares(int[] A) {

        for (int i = 0; i < A.length; i++)
            A[i] = A[i] * A[i];
        Arrays.sort(A);
        return A;
    }
}

public class Solution {
    
    /**
     * 双指针法
     * 根据题设可知数组中负数部分按照平方递减 整数部分按照平方递增
     * 所以数组中负数部分由后向前遍历 整数部分右前向后遍历 两者比较取较小的 依次给新数组赋值 新数组即满足递增要求
     *
     * i 为最大负数位置 即负数平方中最小值
     * j 为最小非负数位置 即非负数评分中的最小值
     */
    public int[] sortedSquares2(int[] A) {

        int n = A.length;
        int j = 0;
        while (j < n && A[j] < 0)
            j++;
        int i = j-1;

        int[] ans = new int[n];
        int t = 0;

        while (i >= 0 && j < n) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }
        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < n) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }
}

public class Solution {

    /**
     * 与上解法异曲同工
     * i 为最小负数位置 即负数平方中最大值
     * j 为最大非负数位置 即非负数评分中的最大值
     * p 为新数组下标 从大到小(从后往前)赋值
     */
    public int[] sortedSquares3(int[] A) {

        int length = A.length;
        int i = 0, j = length - 1;
        int[] result = new int[length];

        for (int p = j - i; p >= 0; p--) {

            if (Math.abs(A[i]) > Math.abs(A[j])) {
                result[p] = A[i] * A[i];
                i++;
            } else {
                result[p] = A[j] * A[j];
                j--;
            }
        }

        return result;
    }
}