/*
3. Longest Substring Without Repeating Characters


Given a string s, find the length of the longest
substring
 without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.


Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.


Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

1. 0 <= s.length <= 5 * 10^4
2. s consists of English letters, digits, symbols and spaces.
*/

func lengthOfLongestSubstring(s string) int {
	l, ans := 0, 0
	m := make(map[int32]int)
	for i, v := range s {
		if _, ok := m[v]; ok {
			if m[v]+1 > l {
				l = m[v] + 1
			}
		}
		m[v] = i
		if i-l+1 > ans {
			ans = i - l + 1
		}
	}
	return ans
}
