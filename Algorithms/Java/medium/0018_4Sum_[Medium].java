/**
 * 18. 4Sum
 *
 *
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 20^0
 * 2. -10^9 <= nums[i] <= 10^9
 * 3. -10^9 <= target <= 10^9
 */
public class Sum4 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        for (int a = 0; a <= nums.length - 4; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b <= nums.length - 3; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) {
                    if (c > b + 1 && nums[c] == nums[c - 1]) {
                        c++;
                        continue;
                    }
                    if (nums[a] + nums[b] + nums[c] + nums[d] > target) {
                        d--;
                    } else if (nums[a] + nums[b] + nums[c] + nums[d] < target) {
                        c++;
                    } else {
                        ans.add(new ArrayList<>(Arrays.asList(nums[a], nums[b], nums[c], nums[d])));
                        c++;
                        d--;
                    }
                }
            }
        }
        return ans;
    }

}
