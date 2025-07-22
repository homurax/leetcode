/*
166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:

Input: numerator = 2, denominator = 1
Output: "2"

Example 3:

Input: numerator = 4, denominator = 333
Output: "0.(012)"

Constraints:

-2^31 <= numerator, denominator <= 2^31 - 1
denominator != 0
*/
func fractionToDecimal(numerator int, denominator int) string {
	if numerator%denominator == 0 { // 整除
		return strconv.Itoa(numerator / denominator)
	}
	s := []byte{}
	if numerator*denominator < 0 { // 考虑负数
		s = append(s, '-')
	}
	// 整数部分
	if numerator < 0 {
		numerator = -numerator
	}
	if denominator < 0 {
		denominator = -denominator
	}
	s = append(s, strconv.Itoa(numerator/denominator)...)
	s = append(s, '.')
	// 小数部分
	indexMap := map[int]int{}
	remainder := numerator % denominator
	for remainder != 0 && indexMap[remainder] == 0 {
		indexMap[remainder] = len(s)
		remainder *= 10
		s = append(s, '0'+byte(remainder/denominator))
		remainder %= denominator
	}
	if remainder > 0 { // 循环
		insertIndex := indexMap[remainder]
		s = append(s[:insertIndex], append([]byte{'('}, s[insertIndex:]...)...)
		s = append(s, ')')
	}
	return string(s)
}
