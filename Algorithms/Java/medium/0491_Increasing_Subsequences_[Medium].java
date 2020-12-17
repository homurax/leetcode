/**
 * 491. Increasing Subsequences
 *
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.
 *
 *
 *
 * Example:
 *
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 *
 * Constraints:
 *
 * 1. The length of the given array will not exceed 15.
 * 2. The range of integer in the given array is [-100,100].
 * 3. The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
public class IncreasingSubsequences {

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0, Integer.MIN_VALUE);
        return ans;
    }

    private void dfs(int[] nums, int index, int last) {
        if (index == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }
        if (nums[index] >= last) {
            temp.add(nums[index]);
            dfs(nums, index + 1, nums[index]);
            temp.remove(temp.size() - 1);
        }
        if (nums[index] != last) {
            dfs(nums, index + 1, last);
        }
    }

}
