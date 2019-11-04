/**
 * 504. Base 7
 *
 * Given an integer, return its base 7 string representation.
 *
 * Example 1:
 * Input: 100
 * Output: "202"
 *
 * Example 2:
 * Input: -7
 * Output: "-10"
 * Note: The input will be in range of [-1e7, 1e7].
 */
public class Base7 {


    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }


    public String convertToBase72(int num) {
        if (num < 0) {
            return '-' + convertToBase7(-num);
        }
        if (num < 7) {
            return num + "";
        }
        return convertToBase72(num / 7) + num % 7;
    }
}
