/**
 * 47. Permutations II
 *
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 8
 * 2. -10 <= nums[i] <= 10
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], ans, 0, new ArrayList<>());
        return ans;
    }

    private void backtrack(int[] nums, boolean[] visited, List<List<Integer>> ans, int idx, List<Integer> temp) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            temp.add(nums[i]);
            visited[i] = true;
            backtrack(nums, visited, ans, idx + 1, temp);
            visited[i] = false;
            temp.remove(idx);
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, ans);
        return ans;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            ans.add(path);
        } else {
            boolean[] visit = new boolean[21];
            for (int i = index; i < nums.length; i++) {
                if (!visit[nums[i] + 10]) {
                    visit[nums[i] + 10] = true;
                    swap(nums, i, index);
                    backtrack(nums, index + 1, ans);
                    swap(nums, index, i);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
