/**
 * 16. 3Sum Closest
 *
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 * Constraints:
 *
 * 1. 3 <= nums.length <= 10^3
 * 2. -10^3 <= nums[i] <= 10^3
 * 3. -10^4 <= target <= 10^4
 */
public class ThreeSumClosest {

    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 10000000;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum > target) {
                    //k--;
                    int k0 = k - 1;
                    while (j < k0 && nums[k0] == nums[k]) {
                        k0--;
                    }
                    k = k0;
                } else {
                    //j++;
                    int j0 = j + 1;
                    while (j0 < k && nums[j0] == nums[j]) {
                        j0++;
                    }
                    j = j0;
                }
            }
        }
        return ans;
    }



    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for (int i = 0; diff != 0 && i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                diff = search(i, nums, target, diff);
            }
        }
        return target - diff;
    }

    private int search(int index, int[] nums, int target, int diff) {
        int left = index + 1;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[index] + nums[left] + nums[right];
            if (Math.abs(target - sum) < Math.abs(diff)) {
                diff = target - sum;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return diff;
    }

}
