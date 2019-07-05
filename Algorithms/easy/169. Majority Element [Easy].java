/**
 * 169. Majority Element
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int count = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > count) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 根据题设排序后中位必定为目标元素
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * 分治处理
     */
    public int majorityElement3(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }

    private int majorityElementRec(int[] nums, int low, int high) {

        if (low == high) {
            return nums[low];
        }

        int mid = (high + low)/2;
        int left = majorityElementRec(nums, low, mid);
        int right = majorityElementRec(nums, mid + 1, high);

        if (left == right) {
            return left;
        }

        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);

        return leftCount > rightCount ? left : right;
    }
    private int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * Boyer-Moore
     * 取基准数 遇到相同则count++ 不同则count--
     * 最后的基准数即目标数
     */
    public int majorityElement4(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
