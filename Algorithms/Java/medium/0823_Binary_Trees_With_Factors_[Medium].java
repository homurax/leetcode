/**
 * 823. Binary Trees With Factors
 *
 *
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
 *
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 *
 * Example 2:
 *
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 1000
 * 2. 2 <= arr[i] <= 10^9
 * 3. All the values of arr are unique.
 */
public class BinaryTreesWithFactors {

    public int numFactoredBinaryTrees(int[] arr) {
        int MOD = 1_000_000_007, n = arr.length;
        Arrays.sort(arr);
        long[] count = new long[n];
        Arrays.fill(count, 1);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            index.put(arr[i], i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int r = arr[i] / arr[j];
                    if (index.containsKey(r)) {
                        count[i] = (count[i] + count[j] * count[index.get(r)]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (long x: count) {
            ans += x;
        }
        return (int) (ans % MOD);
    }

}
