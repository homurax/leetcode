/*
1432. Max Difference You Can Get From Changing an Integer

You are given an integer num. You will apply the following steps exactly two times:

Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
The new integer cannot have any leading zeros, also the new integer cannot be 0.
Let a and b be the results of applying the operations to num the first and second times, respectively.

Return the max difference between a and b.

Example 1:

Input: num = 555
Output: 888
Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888

Example 2:

Input: num = 9
Output: 8
Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8

Constraints:

1 <= num <= 10^8
*/
func maxDiff(num int) int {
	s := strconv.Itoa(num)
	ma, mi := s, s
	for _, c := range s {
		if c != '9' {
			ma = strings.ReplaceAll(s, string(c), "9")
			break
		}
	}
	for i, c := range s {
		if i == 0 {
			if c != '1' {
				mi = strings.ReplaceAll(s, string(c), "1")
				break
			}
		} else {
			if c != '0' && c != '1' {
				mi = strings.ReplaceAll(s, string(c), "0")
				break
			}
		}
	}
	m1, _ := strconv.Atoi(ma)
	m2, _ := strconv.Atoi(mi)
	return m1 - m2
}
