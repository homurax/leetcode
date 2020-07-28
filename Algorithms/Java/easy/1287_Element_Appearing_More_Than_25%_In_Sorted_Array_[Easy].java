/**
 * 1287. Element Appearing More Than 25% In Sorted Array
 *
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.
 *
 * Return that integer.
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class ElementAppearingMoreThan25InSortedArray {

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int span = n / 4;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[i + span]) {
                return arr[i];
            }
        }
        return -1;
    }

}
