/**
 * 179. Largest Number
 *
 *
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 *
 *
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 100
 * 2. 0 <= nums[i] <= 10^9
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        int k = 0, len = sb.length();
        while (k < len - 1 && sb.charAt(k) == '0') {
            k++;
        }
        return sb.substring(k);
    }

}
