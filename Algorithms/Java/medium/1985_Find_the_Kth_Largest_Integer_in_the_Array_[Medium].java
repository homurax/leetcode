/**
 * 1985. Find the Kth Largest Integer in the Array
 *
 *
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.
 *
 * Return the string that represents the kth largest integer in nums.
 *
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["3","6","7","10"], k = 4
 * Output: "3"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
 * The 4th largest integer in nums is "3".
 *
 * Example 2:
 *
 * Input: nums = ["2","21","12","1"], k = 3
 * Output: "2"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
 * The 3rd largest integer in nums is "2".
 *
 * Example 3:
 *
 * Input: nums = ["0","0"], k = 2
 * Output: "0"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["0","0"].
 * The 2nd largest integer in nums is "0".
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= nums.length <= 10^4
 * 2. 1 <= nums[i].length <= 100
 * 3. nums[i] consists of only digits.
 * 4. nums[i] will not have any leading zeros.
 */
public class FindTheKthLargestIntegerInTheArray {

    // 考虑 nums[i] 长度，自定排序
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (s1, s2) -> s1.length() == s2.length() ? s2.compareTo(s1) : s2.length() - s1.length());
        return nums[k - 1];
    }

}
