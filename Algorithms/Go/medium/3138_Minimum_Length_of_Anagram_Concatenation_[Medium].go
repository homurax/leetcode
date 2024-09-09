/*
3138. Minimum Length of Anagram Concatenation

You are given a string s, which is known to be a concatenation of anagrams of some string t.

Return the minimum possible length of the string t.

An anagram is formed by rearranging the letters of a string. For example, "aab", "aba", and, "baa" are anagrams of "aab".

Example 1:

Input: s = "abba"

Output: 2

Explanation:

One possible string t could be "ba".

Example 2:

Input: s = "cdef"

Output: 4

Explanation:

One possible string t could be "cdef", notice that t can be equal to s.

Constraints:

1 <= s.length <= 10^5
s consist only of lowercase English letters.
*/
func minAnagramLength(s string) int {
	n := len(s)
next:
	for k := 1; k <= n/2; k++ {
		if n%k > 0 {
			continue
		}
		cnt0 := [26]int{}
		for _, c := range s[:k] {
			cnt0[c-'a']++
		}
		for i := k * 2; i <= len(s); i += k {
			cnt := [26]int{}
			for _, c := range s[i-k : i] {
				cnt[c-'a']++
			}
			if cnt != cnt0 {
				continue next
			}
		}
		return k
	}
	return n
}
