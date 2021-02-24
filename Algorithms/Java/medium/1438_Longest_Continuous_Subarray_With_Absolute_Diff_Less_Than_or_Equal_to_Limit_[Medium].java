/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 *
 *
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 *
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 *
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^9
 * 3. 0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public int longestSubarray1(int[] nums, int limit) {
        int ans = 0, n = nums.length;
        for (int start = 0; start < n; start++) {
            int len = 1, min = nums[start], max = nums[start];
            for (int i = start + 1; i < n; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                } else if (nums[i] < min) {
                    min = nums[i];
                }
                if (max - min <= limit) {
                    len++;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, len);
            if (ans > n - start) { // 不剪枝必 TLE
                break;
            }
        }
        return ans;
    }

    // 利用 TreeMap 有序性 确定右端点找左端点
    public int longestSubarray2(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 0, left = 0, right = 0;
        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    // 维护单调队列
    public int longestSubarray3(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<>();
        Deque<Integer> queMin = new LinkedList<>();
        int n = nums.length;
        int ans = 0, left = 0, right = 0;
        while (right < n) {
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    public int longestSubarray(int[] nums, int limit) {
        int ans = 0;
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        while (right < nums.length && left <= right) {
            min = Math.min(min, nums[right]);
            max = Math.max(max, nums[right]);
            if (max - min <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
            } else {
                if (ans > nums.length - right + 1) {
                    break;
                }
                int k = right;
                int preMax = max = nums[right];
                int preMin = min = nums[right];
                while (max - min <= limit) {
                    preMax = max;
                    preMin = min;
                    max = Math.max(max, nums[k]);
                    min = Math.min(min, nums[k]);
                    k--;
                }
                left = k + 2;
                max = preMax;
                min = preMin;
            }
        }
        return ans;
    }
}
