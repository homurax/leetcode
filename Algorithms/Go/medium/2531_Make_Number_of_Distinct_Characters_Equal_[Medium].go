/*
2531. Make Number of Distinct Characters Equal


You are given two 0-indexed strings word1 and word2.

A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].

Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.



Example 1:

Input: word1 = "ac", word2 = "b"
Output: false
Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.


Example 2:

Input: word1 = "abcc", word2 = "aab"
Output: true
Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.


Example 3:

Input: word1 = "abcde", word2 = "fghij"
Output: true
Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.


Constraints:

1. 1 <= word1.length, word2.length <= 10^5
2. word1 and word2 consist of only lowercase English letters.
*/

func isItPossible(word1 string, word2 string) bool {
	cntMap1 := make(map[rune]int)
	for _, v := range word1 {
		cntMap1[v]++
	}
	cntMap2 := make(map[rune]int)
	for _, v := range word2 {
		cntMap2[v]++
	}
	len1, len2 := len(cntMap1), len(cntMap2)
	for c1, cnt1 := range cntMap1 {
		for c2, cnt2 := range cntMap2 {
			if c1 == c2 {
				if len1 == len2 {
					return true
				}
			} else {
				x := len1 - b2i(cnt1 == 1) + b2i(cntMap1[c2] == 0)
				y := len2 - b2i(cnt2 == 1) + b2i(cntMap2[c1] == 0)
				if x == y {
					return true
				}
			}
		}
	}
	return false
}

func b2i(b bool) int {
	if b {
		return 1
	}
	return 0
}
