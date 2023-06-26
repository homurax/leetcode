/**
 * 2023. Number of Pairs of Strings With Concatenation Equal to Target
 *
 *
 * Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["777","7","77","77"], target = "7777"
 * Output: 4
 * Explanation: Valid pairs are:
 * - (0, 1): "777" + "7"
 * - (1, 0): "7" + "777"
 * - (2, 3): "77" + "77"
 * - (3, 2): "77" + "77"
 *
 *
 * Example 2:
 *
 * Input: nums = ["123","4","12","34"], target = "1234"
 * Output: 2
 * Explanation: Valid pairs are:
 * - (0, 1): "123" + "4"
 * - (2, 3): "12" + "34"
 *
 *
 * Example 3:
 *
 * Input: nums = ["1","1","1"], target = "11"
 * Output: 6
 * Explanation: Valid pairs are:
 * - (0, 1): "1" + "1"
 * - (1, 0): "1" + "1"
 * - (0, 2): "1" + "1"
 * - (2, 0): "1" + "1"
 * - (1, 2): "1" + "1"
 * - (2, 1): "1" + "1"
 *
 *
 * Constraints:
 *
 * 1. 2 <= nums.length <= 100
 * 2. 1 <= nums[i].length <= 100
 * 3. 2 <= target.length <= 100
 * 4. nums[i] and target consist of digits.
 * 5. nums[i] and target do not have leading zeros.
 */
public class NumberOfPairsOfStringsWithConcatenationEqualToTarget {

    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String str : nums) {
            cnt.merge(str, 1, Integer::sum);
        }
        int ans = 0;
        for (int i = 1; i < target.length(); i++) {
            String l = target.substring(0, i);
            String r = target.substring(i);
            if (!l.equals(r)) {
                ans += cnt.getOrDefault(l, 0) * cnt.getOrDefault(r, 0);
            } else {
                ans += cnt.getOrDefault(l, 0) * (cnt.getOrDefault(l, 0) - 1);
            }
        }
        return ans;
    }

}
