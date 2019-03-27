/**
 * 985. Sum of Even Numbers After Queries
 *
 * We have an array A of integers, and an array queries of queries.
 *
 * For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.
 *
 * (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)
 *
 * Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
 * Output: [8,6,2,4]
 * Explanation:
 * At the beginning, the array is [1,2,3,4].
 * After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
 * After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
 * After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
 * After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * 1 <= queries.length <= 10000
 * -10000 <= queries[i][0] <= 10000
 * 0 <= queries[i][1] < A.length
 */
public class SumOfEvenNumbersAfterQueries {


    /**
     * Time Limit Exceeded
     * 开始这么写 结果超时了(捂脸  想了想求和部分属于重复计算
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            A[query[1]] += query[0];
            answer[i] = Arrays.stream(A).boxed().filter(x -> (x & 1) == 0).mapToInt(x -> x).sum();
        }
        return answer;
    }


    /**
     * 优化版 减少重复求和
     */
    public int[] sumEvenAfterQueries2(int[] A, int[][] queries) {

        int[] answer = new int[queries.length];
        int sum = 0;

        for (int i : A) {
            if ((i & 1) == 0)
                sum += i;
        }

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][1];
            int val = queries[i][0];
            int temp = A[index];
            A[index] += val;

            if ((temp & 1) == 0 && (val & 1) == 0) {
                sum += val;
            } else if ((temp & 1) == 0 && (val & 1) == 1) {
                sum -= temp;
            } else if ((temp & 1) == 1 && (val & 1) == 1) {
                sum += A[index];
            }

            answer[i] = sum;
        }
        return answer;
    }

    /**
     * 思路相同
     * 把计算A新值步骤后置 代码更加简洁
     */
    public int[] sumEvenAfterQueries3(int[] A, int[][] queries) {

        int sum = 0;
        int[] answer = new int[queries.length];

        for (int i : A) {
            if ((i & 1) == 0)
                sum += i;
        }

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][1];
            int val = queries[i][0];

            if ((A[index] & 1) == 0)
                sum -= A[index];

            A[index] += val;

            if ((A[index] & 1) == 0)
                sum += A[index];

            answer[i] = sum;
        }
        return answer;
    }

}
