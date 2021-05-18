/**
 * 842. Split Array into Fibonacci Sequence
 *
 *
 * Given a string num of digits, such as num = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 *
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 *
 * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 * F.length >= 3;
 * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 *
 * Return any Fibonacci-like sequence split from num, or return [] if it cannot be done.
 *
 * Example 1:
 *
 * Input: num = "123456579"
 * Output: [123,456,579]
 *
 * Example 2:
 *
 * Input: num = "11235813"
 * Output: [1,1,2,3,5,8,13]
 *
 * Example 3:
 *
 * Input: num = "112358130"
 * Output: []
 * Explanation: The task is impossible.
 *
 * Example 4:
 *
 * Input: num = "0123"
 * Output: []
 * Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
 *
 * Example 5:
 *
 * Input: num = "1101111"
 * Output: [110, 1, 111]
 * Explanation: The output [11, 0, 11, 11] would also be accepted.
 * Note:
 *
 * 1. 1 <= num.length <= 200
 * 2. num contains only digits.
 */
public class SplitArrayIntoFibonacciSequence {

    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        backtrack(num.toCharArray(), new ArrayList<>(), 0);
        return ans;
    }

    public boolean backtrack(char[] digits, List<Integer> ans, int index) {
        if (index == digits.length && ans.size() >= 3) {
            return true;
        }
        long num = 0;
        for (int i = index; i < digits.length; i++) {
            if (digits[index] == '0' && i > index) {
                break;
            }
            num = num * 10 + digits[i] - '0';
            if (num > Integer.MAX_VALUE) {
                return false;
            }
            if (ans.size() > 2 && num > ans.get(ans.size() - 1) + ans.get(ans.size() - 2)) {
                break;
            }
            if (ans.size() < 2 || num == ans.get(ans.size() - 1) + ans.get(ans.size() - 2)) {
                ans.add((int) num);
                if (backtrack(digits, ans, i + 1)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }

}
