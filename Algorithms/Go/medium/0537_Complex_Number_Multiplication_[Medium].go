/*
537. Complex Number Multiplication


A complex number can be represented as a string on the form "real+imaginaryi" where:

real is the real part and is an integer in the range [-100, 100].
imaginary is the imaginary part and is an integer in the range [-100, 100].
i2 == -1.
Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.



Example 1:

Input: num1 = "1+1i", num2 = "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.


Example 2:

Input: num1 = "1+-1i", num2 = "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.


Constraints:

num1 and num2 are valid complex numbers.
*/

func complexNumberMultiply(num1 string, num2 string) string {
	s1 := regexp.MustCompile(`[+i]`).Split(num1, -1)
	s2 := regexp.MustCompile(`[+i]`).Split(num2, -1)
	a, _ := strconv.Atoi(s1[0])
	b, _ := strconv.Atoi(s1[1])
	c, _ := strconv.Atoi(s2[0])
	d, _ := strconv.Atoi(s2[1])
	return strconv.Itoa(a*c-b*d) + "+" + strconv.Itoa(b*c+a*d) + "i"
}
