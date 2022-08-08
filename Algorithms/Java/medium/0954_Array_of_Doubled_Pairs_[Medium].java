/**
 * 954. Array of Doubled Pairs
 *
 *
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,3,6]
 * Output: false
 *
 *
 * Example 2:
 *
 * Input: arr = [2,1,2,6]
 * Output: false
 *
 *
 * Example 3:
 *
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 *
 *
 * Constraints:
 *
 * 1. 2 <= arr.length <= 3 * 10^4
 * 2. arr.length is even.
 * 3. -10^5 <= arr[i] <= 10^5
 */
public class ArrayOfDoubledPairs {

    public boolean canReorderDoubled(int[] arr) {
        int offset = 100010 * 2;
        int[] cnt = new int[offset * 2];
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (++cnt[num + offset] == 1) {
                list.add(num);
            }
        }
        list.sort(Comparator.comparingInt(Math::abs));
        for (int num : list) {
            if (cnt[num + offset] > cnt[num * 2 + offset]) {
                return false;
            }
            cnt[num * 2 + offset] -= cnt[num + offset];
        }
        return true;
    }

}
