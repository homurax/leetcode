/*
2165. Smallest Value of the Rearranged Number


You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.

Return the rearranged number with minimal value.

Note that the sign of the number does not change after rearranging the digits.



Example 1:

Input: num = 310
Output: 103
Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310.
The arrangement with the smallest value that does not contain any leading zeros is 103.


Example 2:

Input: num = -7605
Output: -7650
Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
The arrangement with the smallest value that does not contain any leading zeros is -7650.


Constraints:

-10^15 <= num <= 10^15
*/
func smallestNumber1(num int64) int64 {
	s := []byte(strconv.FormatInt(num, 10))
	if num <= 0 {
		t := s[1:]
		sort.Slice(t, func(i, j int) bool { return t[i] > t[j] })
		ans, _ := strconv.ParseInt(string(s), 10, 64)
		return ans
	}
	sort.Slice(s, func(i, j int) bool { return s[i] < s[j] })
	i := 0
	for s[i] == '0' {
		i++
	}
	s[i], s[0] = s[0], s[i]
	ans, _ := strconv.ParseInt(string(s), 10, 64)
	return ans
}
