/**
 * 219. Contains Duplicate II
 *
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class ContainsDuplicateII {


    public boolean containsNearbyDuplicate(int[] nums, int k) {

        if (nums.length == 0) return false;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (nums[i] == nums[j])
                    return true;
            }
        }

        return false;
    }



    public boolean containsNearbyDuplicate2(int[] nums, int k) {

        if (nums.length == 0) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                if (map.get(num) + k >= i) {
                    return true;
                } else {
                    map.put(num, i);
                }
            } else {
                map.put(num, i);
            }
        }

        return false;
    }


    /**
     * 滑窗
     */
    public boolean containsNearbyDuplicate3(int[] nums, int k) {

        if (nums.length == 0) return false;

        HashSet<Integer> set = new HashSet<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (set.size() > k) {
                set.remove(nums[i - k - 1]);
            }

            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }


}
