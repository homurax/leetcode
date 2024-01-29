/*
720. Longest Word in Dictionary


Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.

Note that the word should be built from left to right with each additional character being added to the end of a previous word.



Example 1:

Input: words = ["w","wo","wor","worl","world"]
Output: "world"
Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".


Example 2:

Input: words = ["a","banana","app","appl","ap","apply","apple"]
Output: "apple"
Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 30
words[i] consists of lowercase English letters.
*/

func longestWord(words []string) string {
	ma := make(map[string]bool)
	for _, word := range words {
		ma[word] = true
	}
	ans := ""
	for _, word := range words {
		m, n := len(word), len(ans)
		if m < n || (m == n && word > ans) {
			continue
		}
		ok := true
		for i := 1; i <= m; i++ {
			if !ma[word[:i]] {
				ok = false
				break
			}
		}
		if ok {
			ans = word
		}
	}
	return ans
}
