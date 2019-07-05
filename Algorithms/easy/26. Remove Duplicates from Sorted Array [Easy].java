/**
 * 26. Remove Duplicates from Sorted Array
 *
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 * Given nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 *
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 *
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 *
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 *
 * Confused why the returned value is an integer but your answer is an array?
 *
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 *
 * Internally you can think of this:
 *
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 *
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class RemoveDuplicatesFromSortedArray {


    public int removeDuplicates(int[] nums) {

        int index = 0;
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!temp.contains(num)) {
                temp.add(num);
                nums[index++] = num;
            }
        }
        return index;
    }


    /**
     * 数组已排序 记录上一个储存元素即可
     */
    public int removeDuplicates2(int[] nums) {

        if (nums.length == 0) return 0;

        int curr = nums[0], index = 1;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (i < length && nums[i] == curr)
                i++;
            if (i < length)
                nums[index++] = curr = nums[i];
        }

        return index;
    }


    public int removeDuplicates3(int[] nums) {

        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

}
