/**
 * 594. Longest Harmonious Subsequence
 *
 * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
 *
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 *
 * Example 1:
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * Note: The length of the input array will not exceed 20,000.
 */
public class LongestHarmoniousSubsequence {


    public int findLHS(int[] nums) {

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num - 1))
                ans = Math.max(ans, map.get(num) + map.get(num - 1));
            if (map.containsKey(num + 1))
                ans = Math.max(ans, map.get(num) + map.get(num + 1));
        }
        return ans;
    }

    public int findLHS2(int[] nums) {

        Arrays.sort(nums);
        int ans = 0;
        int length = nums.length;
        int prevCount = 1;

        for (int i = 0; i < length; i++) {
            int count = 1;
            if (i > 0 && nums[i] - nums[i - 1] == 1) {
                while (i < length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                ans = Math.max(ans, count + prevCount);
                prevCount = count;
            } else {
                while (i < length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                prevCount = count;
            }
        }
        return ans;
    }

}
