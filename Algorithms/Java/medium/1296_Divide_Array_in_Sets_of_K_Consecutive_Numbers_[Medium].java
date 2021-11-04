/**
 * 1296. Divide Array in Sets of K Consecutive Numbers
 *
 *
 * Given an array of integers nums and a positive integer k, find whether it is possible to divide this array into sets of k consecutive numbers.
 *
 * Return true if it is possible. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 *
 * Example 3:
 *
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 *
 * Example 4:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^9
 *
 *
 * Note: This question is the same as 846: https://leetcode.com/problems/hand-of-straights/
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k > 0) {
            return false;
        }
        Arrays.sort(nums);
        int[] buckets = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (i % k != 0 && nums[i] - nums[i - 1] > 1) {
                return false;
            }
            buckets[nums[i] % k]++;
        }
        for (int count : buckets) {
            if (count != buckets[0]) {
                return false;
            }
        }
        return true;
    }


    // TreeMap
    public boolean isPossibleDivide2(int[] nums, int k) {
        if (nums.length % k > 0) {
            return false;
        }
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            treeMap.merge(num, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            Integer count = entry.getValue();
            if (count > 0) {
                Integer num = entry.getKey();
                for (int i = num + 1; i < num + k; i++) {
                    if (treeMap.containsKey(i) && treeMap.get(i) >= count) {
                        treeMap.put(i, treeMap.get(i) - count);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide1(int[] nums, int k) {
        if (nums.length % k > 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0, prev = -1; i < nums.length; i = prev, prev = -1) {
            int temp = nums[i], len = 1;
            nums[i] = -1;
            while (len < k && ++i < nums.length) {
                if (nums[i] == -1) {
                    continue;
                }
                if (nums[i] - temp > 1) {
                    return false;
                } else if (nums[i] - temp == 1) {
                    temp = nums[i];
                    nums[i] = -1;
                    len++;
                } else if (prev == -1) {
                    prev = i;
                }
            }
            if (len < k) {
                return false;
            }
            if (prev == -1) {
                prev = i + 1;
            }
        }
        return true;
    }


}
