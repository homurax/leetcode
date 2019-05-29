/**
 * 367. Valid Perfect Square
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 14
 * Output: false
 */
public class ValidPerfectSquare {


    /**
     * 完全平方数是前n个连续奇数的和
     * 1 + 3 + 5 + 7 + 9 + … + (2n-1) = n^2
     */
    public boolean isPerfectSquare(int num) {

        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }


    /**
     * 用下二分法
     */
    public boolean isPerfectSquare2(int num) {

        if(num == 1) return true;

        long low = 1, high = num / 2;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long square = mid * mid;
            if (square > num) {
                high = --mid;
            } else if (square < num) {
                low = ++mid;
            } else {
                return true;
            }
        }

        return false;
    }


}
