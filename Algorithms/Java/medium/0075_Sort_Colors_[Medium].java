/**
 * 75. Sort Colors
 *
 *
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Follow up:
 *
 * Could you solve this problem without using the library's sort function?
 * Could you come up with a one-pass algorithm using only O(1) constant space?
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Example 3:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1. n == nums.length
 * 2. 1 <= n <= 300
 * 3. nums[i] is 0, 1, or 2.
 */
public class SortColors {

    // 重写数组
    public void sortColors1(int[] nums) {
        int a = 0, b = 0, c = 0;
        for (int num : nums) {
            if (num == 0) a++;
            else if (num == 1) b++;
            else c++;
        }
        Arrays.fill(nums, 0, a, 0);
        Arrays.fill(nums, a, a + b, 1);
        Arrays.fill(nums, a + b, a + b + c, 2);
    }

    // 单指针移动
    public void sortColors2(int[] nums) {
        int write = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, write, i);
                write++;
            }
        }
        for (int i = write; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, write, i);
                write++;
            }
        }
    }

    private void swap(int[] nums, int write, int i) {
        int temp = nums[i];
        nums[i] = nums[write];
        nums[write] = temp;
    }


    // 双指针写 0 2
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        for (int i = 0; i <= p2; i++) {
            while (i <= p2 && nums[i] == 2) {
                nums[i] = nums[p2];
                nums[p2--] = 2;
            }
            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0++] = 0;
            }
        }
    }

}
