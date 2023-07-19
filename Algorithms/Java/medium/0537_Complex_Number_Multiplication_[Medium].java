/**
 * 537. Complex Number Multiplication
 *
 *
 * A complex number can be represented as a string on the form "real+imaginaryi" where:
 *
 * real is the real part and is an integer in the range [-100, 100].
 * imaginary is the imaginary part and is an integer in the range [-100, 100].
 * i2 == -1.
 * Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "1+1i", num2 = "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 *
 *
 * Example 2:
 *
 * Input: num1 = "1+-1i", num2 = "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 *
 *
 * Constraints:
 *
 * num1 and num2 are valid complex numbers.
 */
public class ComplexNumberMultiplication {


    // (a + bi)(c + di) = ac + adi + bci - bd = (ac - bd) + (ad + bc)i
    public String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("[+i]");
        String[] s2 = num2.split("[+i]");
        int a = Integer.parseInt(s1[0]);
        int b = Integer.parseInt(s1[1]);
        int c = Integer.parseInt(s2[0]);
        int d = Integer.parseInt(s2[1]);
        return (a * c - b * d) + "+" + (b * c + a * d) + "i";
    }

}
