/**
 * 922. Sort Array By Parity II
 *
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 *
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 *
 * You may return any answer array that satisfies this condition.
 *
 *
 * Example 1:
 *
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 *
 * Note:
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class SortArrayByParityII {


    /**
     * 偶数容量数组 一半奇数一般偶数 要求下标i与对应值同奇偶
     * 即偶奇偶奇...的顺序
     */
    public int[] sortArrayByParityII(int[] A) {

        int[] result = new int[A.length];
        int index = 0;
        for (int i : A) {
            if ((i & 1) == 0) {
                result[index] = i;
                index += 2;
            }
        }

        index = 1;
        for (int i : A) {
            if ((i & 1) == 1) {
                result[index] = i;
                index += 2;
            }
        }
        return result;
    }


    /**
     * for中检查每一个偶数索引对应值是否为偶数
     * 为奇数则从第一个奇数位开始向后检查奇数索引为直到找到偶数
     * 交换
     */
    public int[] sortArrayByParityII2(int[] A) {

        int j = 1;
        for (int i = 0; i < A.length; i += 2) {
            if ((A[i] & 1) == 1) {
                while ((A[j] & 1) == 1)
                    j += 2;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }


}
