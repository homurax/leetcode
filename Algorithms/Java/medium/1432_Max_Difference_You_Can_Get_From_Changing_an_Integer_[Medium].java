/**
 * 1432. Max Difference You Can Get From Changing an Integer
 *
 *
 * You are given an integer num. You will apply the following steps exactly two times:
 *
 * Pick a digit x (0 <= x <= 9).
 * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
 * Replace all the occurrences of x in the decimal representation of num by y.
 * The new integer cannot have any leading zeros, also the new integer cannot be 0.
 * Let a and b be the results of applying the operations to num the first and second times, respectively.
 *
 * Return the max difference between a and b.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 555
 * Output: 888
 * Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
 * The second time pick x = 5 and y = 1 and store the new integer in b.
 * We have now a = 999 and b = 111 and max difference = 888
 *
 *
 * Example 2:
 *
 * Input: num = 9
 * Output: 8
 * Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
 * The second time pick x = 9 and y = 1 and store the new integer in b.
 * We have now a = 9 and b = 1 and max difference = 8
 *
 *
 * Constraints:
 *
 * 1 <= num <= 10^8
 */
public class MaxDifferenceYouCanGetFromChangingAnInteger {

    // 最大值:
    // 找到第一个 nums[i] != 9, replace(num, nums[i], 9)
    // 最小值:
    // 如果 nums[0] 不是 1, 那么 replace(num, nums[0], 1) 即可
    // 如果 nums[0] 是 1, 则找到第一个 nums[i] != 0 && nums[i] != nums[0], replace(num, nums[i], 0)
    public int maxDiff(int num) {
        StringBuilder sbMax = new StringBuilder(Integer.toString(num));
        StringBuilder sbMin = new StringBuilder(Integer.toString(num));

        for (int i = 0; i < sbMax.length(); i++) {
            if (sbMax.charAt(i) != '9') {
                replace(sbMax, sbMax.charAt(i), '9');
                break;
            }
        }
        for (int i = 0; i < sbMin.length(); ++i) {
            char digit = sbMin.charAt(i);
            if (i == 0) {
                if (digit != '1') {
                    replace(sbMin, digit, '1');
                    break;
                }
            } else {
                if (digit != '0' && digit != sbMin.charAt(0)) {
                    replace(sbMin, digit, '0');
                    break;
                }
            }
        }
        return Integer.parseInt(sbMax.toString()) - Integer.parseInt(sbMin.toString());
    }

    private void replace(StringBuilder sb, char source, char target) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == source) {
                sb.setCharAt(i, target);
            }
        }
    }

}
