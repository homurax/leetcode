/*
467. Unique Substrings in Wraparound String


We define the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this:

"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
Given a string p, return the number of unique non-empty substrings of p are present in s.



Example 1:

Input: p = "a"
Output: 1
Explanation: Only the substring "a" of p is in s.
Example 2:

Input: p = "cac"
Output: 2
Explanation: There are two substrings ("a", "c") of p in s.
Example 3:

Input: p = "zab"
Output: 6
Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab") of p in s.


Constraints:

1. 1 <= p.length <= 10^5
2. p consists of lowercase English letters.
*/

func findSubstringInWraproundString(p string) int {
	l := 0
	var cnt [26]int
	for i := range p {
		if i > 0 && (p[i]-p[i-1]+26)%26 == 1 {
			l++
		} else {
			l = 1
		}
		if l > cnt[p[i]-'a'] {
			cnt[p[i]-'a'] = l
		}
	}
	sum := 0
	for _, v := range cnt {
		sum += v
	}
	return sum
}
