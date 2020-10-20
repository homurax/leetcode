/**
 * 1481. Least Number of Unique Integers after K Removals
 *
 *
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.merge(num, 1, Integer::sum);
        }
        int total = countMap.size();
        List<Integer> list = new ArrayList<>(countMap.values());
        Collections.sort(list);
        for (Integer count : list) {
            if (k < count) break;
            k -= count;
            total--;
        }
        return total;
    }


    public int findLeastNumOfUniqueInts2(int[] arr, int k) {
        Arrays.sort(arr);
        int[] counts = new int[arr.length];
        counts[0] = 1;
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                counts[idx]++;
            } else {
                counts[++idx] = 1;
            }
        }
        int total = idx + 1;
        Arrays.sort(counts);
        // 注意 sort 后没有计数的 0 会提升
        // 有效计数部分变为 [len - total, len)
        for (int i = arr.length - total; i < arr.length; i++) {
            if (k < counts[i]) break;
            k -= counts[i];
            total--;
        }
        return total;
    }

}
