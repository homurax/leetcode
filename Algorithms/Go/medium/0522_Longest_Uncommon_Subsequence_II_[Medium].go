/*
522. Longest Uncommon Subsequence II

Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.

An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.

A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.

For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).

Example 1:

Input: strs = ["aba","cdc","eae"]
Output: 3

Example 2:

Input: strs = ["aaa","aaa","aa"]
Output: -1

Constraints:

2 <= strs.length <= 50
1 <= strs[i].length <= 10
strs[i] consists of lowercase English letters.
*/
func findLUSlength(strs []string) int {
	var isSubSeq func(string, string) bool
	isSubSeq = func(s, t string) bool {
		i := 0
		for _, c := range t {
			if s[i] == byte(c) {
				i++
				if i == len(s) {
					return true
				}
			}
		}
		return false
	}

	slices.SortFunc(strs, func(s, t string) int { return len(t) - len(s) })
next:
	for i, s := range strs {
		for j, t := range strs {
			if j != i && isSubSeq(s, t) {
				continue next
			}
		}
		return len(strs[i])
	}
	return -1
}
