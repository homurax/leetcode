/**
2384. Largest Palindromic Number


You are given a string num consisting of digits only.

Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.

Notes:

You do not need to use all the digits of num, but you must use at least one digit.
The digits can be reordered.


Example 1:

Input: num = "444947137"
Output: "7449447"
Explanation:
Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
It can be shown that "7449447" is the largest palindromic integer that can be formed.

Example 2:

Input: num = "00009"
Output: "9"
Explanation:
It can be shown that "9" is the largest palindromic integer that can be formed.
Note that the integer returned should not contain leading zeroes.


Constraints:

1. 1 <= num.length <= 10^5
2. num consists of digits.
*/

func largestPalindromic(num string) string {
	cnt := ['9' + 1]int{}
	for _, v := range num {
		cnt[v]++
	}
	if cnt['0'] == len(num) {
		return "0"
	}
	var s []byte
	for i := byte('9'); i > '0' || i == '0' && len(s) > 0; i-- {
		s = append(s, strings.Repeat(string(i), cnt[i]/2)...)
	}
	j := len(s) - 1
	// 中心可以添加一个
	for i := byte('9'); i >= '0'; i-- {
		if cnt[i]&1 == 1 {
			s = append(s, i)
			break
		}
	}
	// 镜像
	for ; j >= 0; j-- {
		s = append(s, s[j])
	}
	return string(s)
}
