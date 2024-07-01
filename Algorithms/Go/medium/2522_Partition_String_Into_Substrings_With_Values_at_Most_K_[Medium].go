/*
2522. Partition String Into Substrings With Values at Most K

You are given a string s consisting of digits from 1 to 9 and an integer k.

A partition of a string s is called good if:

Each digit of s is part of exactly one substring.
The value of each substring is less than or equal to k.
Return the minimum number of substrings in a good partition of s. If no good partition of s exists, return -1.

Note that:

The value of a string is its result when interpreted as an integer. For example, the value of "123" is 123 and the value of "1" is 1.
A substring is a contiguous sequence of characters within a string.

Example 1:

Input: s = "165462", k = 60
Output: 4
Explanation: We can partition the string into substrings "16", "54", "6", and "2". Each substring has a value less than or equal to k = 60.
It can be shown that we cannot partition the string into less than 4 substrings.

Example 2:

Input: s = "238182", k = 5
Output: -1
Explanation: There is no good partition for this string.

Constraints:

1 <= s.length <= 10^5
s[i] is a digit from '1' to '9'.
1 <= k <= 10^9
*/
func minimumPartition(s string, k int) int {
	ans, rst := 1, 0
	for _, c := range s {
		v := int(c - '0')
		if v > k {
			return -1
		}
		rst = rst*10 + v
		if rst > k {
			ans++
			rst = v
		}
	}
	return ans
}
