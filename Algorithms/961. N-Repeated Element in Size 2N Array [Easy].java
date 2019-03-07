/**
 * 961. N-Repeated Element in Size 2N Array
 *
 * In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
 *
 * Return the element repeated N times.
 *
 * Example 1:
 * Input: [1,2,3,3]
 * Output: 3
 *
 * Example 2:
 * Input: [2,1,2,5,3,2]
 * Output: 2
 *
 * Example 3:
 * Input: [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 * Note:
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length is even
 */
public class Solution {

    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        return A[A.length / 2] == A[A.length - 1] ? A[A.length / 2] : A[A.length / 2 - 1];
    }
}

public class Solution {

    public int repeatedNTimes(int[] A) {

        Set<Integer> set = new HashSet<>();
        for (int i : A) {
            if (!set.add(i))
                return i;
        }
        return 0;
    }
}

public class Solution {

    /**
     * 从重复元素开始长度为4的序列将具有两个重复元素
     * 将元素与距离为1 2 3 的元素比较
     */
    public int repeatedNTimes3(int[] A) {

        for (int k = 1; k <= 3; k++)
            for (int i = 0; i < A.length - k; i++)
                if (A[i] == A[i+k])
                    return A[i];
        return 0;
    }
}