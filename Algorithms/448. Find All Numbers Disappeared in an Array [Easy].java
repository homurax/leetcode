/**
 * 448. Find All Numbers Disappeared in an Array
 *
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 */
public class FindAllNumbersDisappearedInAnArray {

    /**
     * 超时了
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            ans.add(i);
        }
        for (int num : nums) {
            ans.remove((Integer) num);
        }
        return ans;
    }

    /**
     * 时间可以了 但是使用了额外的空间 => boolean[] booleans
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {

        List<Integer> ans = new ArrayList<>(nums.length);
        boolean[] booleans = new boolean[nums.length + 1];
        for (int num : nums) {
            booleans[num] = true;
        }
        for (int i = 1; i < booleans.length; i++) {
            if (!booleans[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 不超时 且不使用额外的空间
     */
    public List<Integer> findDisappearedNumbers3(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            while (val != nums[val - 1]) {
                int tmp = nums[val - 1];
                nums[val - 1] = val;
                val = tmp;
            }
        }

        List<Integer> ans = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
