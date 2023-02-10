/*
5. Longest Palindromic Substring

Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.


Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1. 1 <= s.length <= 1000
2. s consist of only digits and English letters.
*/

func longestPalindrome1(s string) string {
	n := len(s)
	if n == 1 {
		return s
	}
	idx, maxLen := 0, 0
	for i := 0; i < n; i++ {
		l, r, length := i-1, i+1, 1
		for l >= 0 && s[l] == s[i] {
			l--
			length++
		}
		for r < n && s[r] == s[i] {
			r++
			length++
		}
		for l >= 0 && r < n && s[l] == s[r] {
			l--
			r++
			length += 2
		}
		if length > maxLen {
			maxLen = length
			idx = l
		}
	}
	return s[idx+1 : idx+maxLen+1]
}
