/**
 * 698. Partition to K Equal Sum Subsets
 *
 *
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k > 0) {
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        int last = nums.length - 1;
        if (nums[last] > target) {
            return false;
        }
        while (last >= 0 && nums[last] == target) {
            k--;
            last--;
        }
        return search(new int[k], last, nums, target);
    }

    private boolean search(int[] groups, int last, int[] nums, int target) {
        if (last < 0) {
            return true;
        }
        int num = nums[last--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + num <= target) {
                groups[i] += num;
                if (search(groups, last, nums, target)) {
                    return true;
                }
                groups[i] -= num;
            }
            // i 桶如果回退到清空 就不需要在 i+1 桶中再尝试一边 可以剪枝
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }



}
