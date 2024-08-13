/**
 * 640. Solve the Equation
 *
 *
 * Solve a given equation and return the value of 'x' in the form of a string "x=#value". The equation contains only '+', '-' operation, the variable 'x' and its coefficient. You should return "No solution" if there is no solution for the equation, or "Infinite solutions" if there are infinite solutions for the equation.
 *
 * If there is exactly one solution for the equation, we ensure that the value of 'x' is an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: equation = "x+5-3+x=6+x-2"
 * Output: "x=2"
 *
 *
 * Example 2:
 *
 * Input: equation = "x=x"
 * Output: "Infinite solutions"
 *
 *
 * Example 3:
 *
 * Input: equation = "2x=x"
 * Output: "x=0"
 *
 *
 * Constraints:
 *
 * 3 <= equation.length <= 1000
 * equation has exactly one '='.
 * equation consists of integers with an absolute value in the range [0, 100] without any leading zeros, and the variable 'x'.
 */
public class SolveTheEquation {

    // 遍历计算 x 的系数和数值累积
    // x 的系数 == 0 && num != 0 -> No solution
    // x 的系数 == 0 && num = 0 -> Infinite solutions
    // x 的系数 != 0 -> num / -x
    public String solveEquation(String equation) {
        int x = 0, num = 0;
        int n = equation.length();
        char[] cs = equation.toCharArray();
        for (int i = 0, op = 1; i < n; ) {
            if (cs[i] == '+') {
                op = 1;
                i++;
            } else if (cs[i] == '-') {
                op = -1;
                i++;
            } else if (cs[i] == '=') {
                op = 1;
                x *= -1;
                num *= -1;
                i++;
            } else {
                int j = i;
                while (j < n && cs[j] != '+' && cs[j] != '-' && cs[j] != '=') {
                    j++;
                }
                if (cs[j - 1] == 'x') {
                    x += (i < j - 1 ? Integer.parseInt(equation.substring(i, j - 1)) : 1) * op;
                } else {
                    num += Integer.parseInt(equation.substring(i, j)) * op;
                }
                i = j;
            }
        }
        if (x == 0) {
            return num == 0 ? "Infinite solutions" : "No solution";
        } else {
            return "x=" + (num / -x);
        }
    }

}
