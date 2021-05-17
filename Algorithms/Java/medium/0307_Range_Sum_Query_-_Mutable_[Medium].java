/**
 * 307. Range Sum Query - Mutable
 *
 *
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 3 * 10^4
 * 2. -100 <= nums[i] <= 100
 * 3. 0 <= index < nums.length
 * 4. -100 <= val <= 100
 * 5. 0 <= left <= right < nums.length
 * 6. t most 3 * 10^4 calls will be made to update and sumRange.
 */
public class RangeSumQueryMutable {

    class NumArray {

        private int[] nums;
        private int sum;

        public NumArray(int[] nums) {
            this.nums = nums;
            this.sum = sum(0, this.nums.length - 1);
        }

        public void update(int index, int val) {
            sum += val - nums[index];
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            if ((right - left) > nums.length / 2) {
                return sum - sum(0, left - 1) - sum(right + 1, nums.length - 1);
            }
            return sum(left, right);
        }

        private int sum(int left, int right) {
            if (right < left) {
                return 0;
            }
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }
            return sum;
        }

    }

}
