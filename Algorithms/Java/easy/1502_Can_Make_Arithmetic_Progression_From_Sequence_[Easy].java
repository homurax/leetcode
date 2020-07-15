/**
 * 1502. Can Make Arithmetic Progression From Sequence
 *
 * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
 *
 * Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
 *
 *
 * Example 1:
 *
 * Input: arr = [3,5,1]
 * Output: true
 * Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
 *
 * Example 2:
 *
 * Input: arr = [1,2,4]
 * Output: false
 * Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 1000
 * -10^6 <= arr[i] <= 10^6
 */
public class CanMakeArithmeticProgressionFromSequence {


    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] * 2 != arr[i - 1] + arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean canMakeArithmeticProgression2(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        double sum = 0;
        for (int i : arr) {
            sum += i;
            if (i > max) { max = i; }
            if (i < min) { min = i; }
        }
        return sum == (double) arr.length * (min + max) / 2;
    }

}
