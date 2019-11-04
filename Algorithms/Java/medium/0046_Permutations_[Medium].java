/**
 * 46. Permutations
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {

    private int length;

    private List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {

        this.length = nums.length;
        this.ans = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backtrack(list, 0);
        return this.ans;
    }

    private void backtrack(List<Integer> nums, int first) {

        if (first == this.length)
            this.ans.add(new ArrayList<>(nums));

        for (int i = first; i < this.length; i++) {
            Collections.swap(nums, first, i);
            backtrack(nums, first + 1);
            Collections.swap(nums, first, i);
        }
    }


    // ------------------------------------------------------------------


    private List<List<Integer>> result;

    public List<List<Integer>> permute2(int[] nums) {
        this.result = new LinkedList<>();
        dfs(nums, 0);
        return this.result;
    }

    private void dfs(int[] nums, int index) {

        if (index == nums.length - 1) {
            List<Integer> temp = new ArrayList<>(nums.length);
            for (Integer num : nums) {
                temp.add(num);
            }
            this.result.add(temp);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            dfs(nums, index + 1);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

}
