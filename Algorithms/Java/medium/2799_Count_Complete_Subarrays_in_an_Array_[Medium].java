/**
 * 2799. Count Complete Subarrays in an Array
 *
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of an array complete if the following condition is satisfied:
 *
 * The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
 * Return the number of complete subarrays.
 *
 * A subarray is a contiguous non-empty part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1,2,2]
 * Output: 4
 * Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].
 *
 *
 * Example 2:
 *
 * Input: nums = [5,5,5,5]
 * Output: 10
 * Explanation: The array consists only of the integer 5, so any subarray is complete. The number of subarrays that we can choose is 10.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2000
 */
public class CountCompleteSubarraysInAnArray {

    public int countCompleteSubarrays(int[] nums) {
        int[] cnt = new int[2001];
        int diff = 0;
        for (int num : nums) {
            if (cnt[num]++ == 0) {
                diff++;
            }
        }
        Arrays.fill(cnt, 0);
        int ans = 0;
        int size = 0;
        int left = 0;
        for (int num : nums) {
            if (cnt[num]++ == 0) {
                size++;
            }
            while (size == diff) {
                if (--cnt[nums[left++]] == 0) {
                    size--;
                }
            }
            ans += left;
        }
        return ans;
    }

    public int countCompleteSubarrays1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int m = set.size();
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, left = 0;
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
            while (cnt.size() == m) {
                int x = nums[left++];
                if (cnt.merge(x, -1, Integer::sum) == 0) {
                    cnt.remove(x);
                }
            }
            ans += left;
        }
        return ans;
    }

}
