/**
 * 645. Set Mismatch
 *
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 *
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Note:
 * The given array size will in the range [2, 10000].
 * The given array's numbers won't have any order.
 */
public class SetMismatch {

    public int[] findErrorNums(int[] nums) {

        int[] table = new int[nums.length + 1];
        for (int num : nums) {
            table[num]++;
        }

        int[] ans = new int[2];
        for (int i = 1; i < table.length; i++) {
            if (table[i] == 2) {
                ans[0] = i;
            }
            if (table[i] == 0) {
                ans[1] = i;
            }
        }

        return ans;
    }

    /**
     * 使用元素值去当坐标
     */
    public int[] findErrorNums2(int[] nums) {

        int[] ans = new int[]{-1, 1};

        for (int n : nums) {
            int index = Math.abs(n) - 1;
            if (nums[index] < 0)
                ans[0] = Math.abs(n);
            else
                nums[index] *= -1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0)
                ans[1] = i + 1;
        }

        return ans;
    }

}
