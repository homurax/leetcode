/**
 * 166. Fraction to Recurring Decimal
 *
 *
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 *
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 *
 *
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 *
 *
 * Example 3:
 *
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 *
 *
 * Constraints:
 *
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */
public class FractionToRecurringDecimal {

    // 模拟除法
    // 两个数相除要么是有限位小数，要么是无限循环小数，不可能是无限不循环小数
    // 无限不循环小数是无理数，不能化成分数形式
    // 除的过程中在不断对余数的补零，如果出现了已经出现过的余数，说明产生了循环小数
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator;
        if (a % b == 0) { // 整除
            return String.valueOf(a / b);
        }
        StringBuilder sb = new StringBuilder();
        if (a * b < 0) { // 考虑负数
            sb.append('-');
        }
        a = Math.abs(a);
        b = Math.abs(b);
        // 整数部分
        sb.append(a / b).append(".");
        a %= b;
        Map<Long, Integer> map = new HashMap<>();
        while (a != 0) {
            // 当前余数位置
            map.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;
            if (map.containsKey(a)) { // 循环部分
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }

}
