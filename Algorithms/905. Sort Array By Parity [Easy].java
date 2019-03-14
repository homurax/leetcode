/**
 * 905. Sort Array By Parity
 *
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 *
 * You may return any answer array that satisfies this condition.
 *
 * Example 1:
 *
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 */
class Solution {

    /**
     * 题目要求其实就是将奇数与偶数分开 那么遍历一次即可
     * 偶数放前 奇数放后 通过操作两个角标实现
     * 时间与空间复杂度都是 O(N)
     */
    public int[] sortArrayByParity(int[] A) {

        int front = 0;
        int rear  = A.length;
        int[] result = new int[A.length];

        for (int i : A) {
            if ((i & 1) == 1)
                result[--rear] = i;
            else
                result[front++] = i;
        }

        return result;
    }

}

class Solution {
    
    /**
     * 流式计算
     * 没什么好说的 装箱又拆箱 时间效率较低
     * 流式运算写的爽
     */
    public int[] sortArrayByParity(int[] A) {

        //(a, b) -> Integer.compare(a & 1, b & 1)
        return Arrays.stream(A).boxed().sorted(Comparator.comparingInt(x -> x & 1)).mapToInt(i -> i).toArray();
    }

}

class Solution {
    
    /**
     * 不需要创建新数组
     * 时间复杂度O(N) 空间复杂度O(1)
     */
    public int[] sortArrayByParity(int[] A) {

        int front = 0;
        int rear  = A.length - 1;

        while (front < rear) {
            if ((A[front] & 1) > (A[rear] & 1)) {
                A[front] = A[front] ^ A[rear];
                A[rear] = A[front] ^ A[rear];
                A[front] = A[front] ^ A[rear];
            }
            if ((A[front] & 1) == 0)
                front++;
            if ((A[rear] & 1) == 1)
                rear--;
        }

        return A;
    }
    
}
