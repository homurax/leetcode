/**
 * 78. Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        add(nums, new ArrayList<>(), 0);
        return this.ans;
    }

    private void add(int[] nums, List<Integer> list, int i) {
        if (nums.length == i) {
            this.ans.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[i]);
        add(nums, list, i + 1);
        list.remove(list.size() - 1);
        add(nums, list, i + 1);
    }
}
