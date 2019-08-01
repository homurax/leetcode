/**
 * 1090. Largest Values From Labels
 *
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 *
 * Then, we choose a subset S of these items, such that:
 *
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 *
 *
 *
 * Example 1:
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 *
 * Example 2:
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 *
 * Example 3:
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * Output: 16
 * Explanation: The subset chosen is the first and fourth item.
 *
 * Example 4:
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 *
 *
 * Note:
 *
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 */
public class LargestValuesFromLabels {


    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {

        if (num_wanted == 0 || use_limit == 0)
            return 0;

        int[][] pairs = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            pairs[i][0] = values[i];
            pairs[i][1] = labels[i];
        }
        Arrays.sort(pairs, (arr1, arr2) -> arr2[0] - arr1[0]);

        int ans = 0;
        int[] labelCount = new int[20001];
        for (int[] pair : pairs) {
            if (labelCount[pair[1]] < use_limit) {
                ans += pair[0];
                labelCount[pair[1]]++;
                if (--num_wanted == 0) {
                    return ans;
                }
            }
        }
        return ans;
    }


    /**
     * 20000 toBinary => 0100 1110 0010 0000
     */
    public int largestValsFromLabels2(int[] values, int[] labels, int num_wanted, int use_limit) {

        int maxLabel = 0;
        for (int i = 0; i < values.length; i++) {
            values[i] = (values[i] << 16) | (labels[i]);
            maxLabel = Math.max(maxLabel, labels[i]);
        }
        Arrays.sort(values);

        int ans = 0;
        int[] counts = new int[maxLabel + 1];
        for (int i = values.length - 1; i >= 0; i--) {
            if (counts[values[i] & 0xFFFF]++ < use_limit) {
                ans += values[i] >>> 16;
                if (--num_wanted == 0) {
                    break;
                }
            }
        }
        return ans;
    }
}
