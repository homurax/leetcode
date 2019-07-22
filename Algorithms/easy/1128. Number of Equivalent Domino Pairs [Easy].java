/**
 * 1128. Number of Equivalent Domino Pairs
 *
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 *
 *
 * Example 1:
 *
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class NumberOfEquivalentDominoPairs {

    public int numEquivDominoPairs(int[][] dominoes) {

        int ans = 0;
        int[] buckets = new int[100];
        for (int[] dominoe : dominoes) {
            int index;
            if (dominoe[0] < dominoe[1]) {
                index = dominoe[0] * 10 + dominoe[1];
            } else {
                index = dominoe[1] * 10 + dominoe[0];
            }
            if (++buckets[index] > 1) {
                ans += buckets[index] - 1;
            }
        }

        return ans;
    }
}
