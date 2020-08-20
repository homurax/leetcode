/**
 * 1409. Queries on a Permutation With Key
 *
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to i=queries.length-1) according to the following rules:
 *
 * In the beginning, you have the permutation P=[1,2,3,...,m].
 * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
 * Return an array containing the result for the given queries.
 *
 *
 * Example 1:
 *
 * Input: queries = [3,1,2,1], m = 5
 * Output: [2,1,2,1]
 * Explanation: The queries are processed as follow:
 * For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is 2, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5].
 * For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5].
 * For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is 2, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5].
 * For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5].
 * Therefore, the array containing the result is [2,1,2,1].
 *
 * Example 2:
 *
 * Input: queries = [4,1,2,2], m = 4
 * Output: [3,1,2,0]
 *
 * Example 3:
 *
 * Input: queries = [7,5,5,8,3], m = 8
 * Output: [6,5,0,7,5]
 *
 *
 * Constraints:
 *
 * 1 <= m <= 10^3
 * 1 <= queries.length <= m
 * 1 <= queries[i] <= m
 */
public class QueriesOnAPermutationWithKey {

    public int[] processQueries(int[] queries, int m) {
        int[] ans = new int[queries.length];
        LinkedList<Integer> P = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            P.add(i);
        }
        for (int i = 0; i < queries.length; i++) {
            ans[i] = P.indexOf(queries[i]);
            P.remove(ans[i]);
            P.push(queries[i]);
        }
        return ans;
    }




    private int[] tree;

    public int[] processQueries2(int[] queries, int m) {
        int n = queries.length;
        tree = new int[n + m + 1];
        int[] pos = new int[m + 1];
        for (int num = 1; num <= m; num++) {
            pos[num] = n + num;
            update(pos[num], 1);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int q = queries[i];
            ans[i] = sum(pos[q]) - 1;
            update(pos[q], -1);
            pos[q] = n - i;
            update(pos[q], 1);
        }
        return ans;
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    private void update(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += lowbit(index);
        }
    }

    private int sum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowbit(index);
        }
        return sum;
    }
}
