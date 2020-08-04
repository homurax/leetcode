/**
 * 697. Degree of an Array
 *
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation:
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 *
 *
 * Constraints:
 *
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {
        int degree = 0;
        for (int num : nums) {
            degree = Math.max(degree, num);
        }
        int[] start = new int[degree + 1];
        int[] count = new int[degree + 1];
        degree = 0;
        int index = 0;
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            if (count[num] == 0) {
                start[num] = index;
            }
            count[num]++;
            index++;
            if (count[num] < degree) {
                continue;
            }
            int length = index - start[num];
            if (count[num] > degree) {
                degree = count[num];
                ans = length;
            } else if (length < ans) {
                ans = length;
            }
        }
        return ans;
    }

}
