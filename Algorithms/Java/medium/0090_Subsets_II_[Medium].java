/**
 * 90. Subsets II
 *
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10
 * 2. -10 <= nums[i] <= 10
 */
public class SubsetsII {


    /**
     * 对于当前遍历到的 num，如果之前已经出现过同样的数字 num'，且没有选择 num'
     * 此时包含 num 的子集，必然会出现在选择了 num' 的子集中
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            temp.clear();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    temp.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<>(temp));
            }
        }
        return ans;
    }

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), false);
        return ans;
    }

    private void dfs(int[] nums, int idx, List<Integer> list, boolean prev) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        dfs(nums, idx + 1, list, false);
        if (idx > 0 && nums[idx - 1] == nums[idx] && !prev) {
            return;
        }
        list.add(nums[idx]);
        dfs(nums, idx + 1, list, true);
        list.remove(list.size() - 1);
    }





    /**
     * hash
     */
    private List<List<Integer>> ans = new ArrayList<>();
    private Set<Long> set = new HashSet<>();
    private int[] hash = new int[21];

    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] nums, int idx, List<Integer> list) {
        if (idx == nums.length) {
            long h = 0L;
            for (int i : hash) {
                h = h * 10 + i;
            }
            if (set.add(h)) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        list.add(nums[idx]);
        hash[nums[idx] + 10]++;
        dfs(nums, idx + 1, list);
        hash[list.remove(list.size() - 1) + 10]--;
        dfs(nums, idx + 1, list);
    }

}
