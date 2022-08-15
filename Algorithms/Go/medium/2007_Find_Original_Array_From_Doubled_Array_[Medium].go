/*
2007. Find Original Array From Doubled Array


An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.



Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.


Constraints:

1. 1 <= changed.length <= 10^5
2. 0 <= changed[i] <= 10^5
*/

func findOriginalArray(changed []int) []int {
	n := len(changed)
	if n%2 != 0 {
		return []int{}
	}
	cnt := make([]int, 100001)
	for _, v := range changed {
		cnt[v]++
	}
	l := n / 2
	r := 0
	ans := make([]int, l)
	for i := 0; i < 100001; i++ {
		for cnt[i] > 0 {
			cnt[i]--
			if i*2 > 100000 || cnt[i*2] == 0 {
				return []int{}
			}
			cnt[i*2]--
			ans[r] = i
			if r++; r == l {
				break
			}
		}
	}
	return ans
}

func findOriginalArray1(changed []int) []int {
	sort.Ints(changed)
	cnt := map[int]int{}
	var ans []int
	for _, v := range changed {
		if cnt[v] == 0 {
			cnt[v*2]++
			ans = append(ans, v)
		} else {
			cnt[v]--
			if cnt[v] == 0 {
				delete(cnt, v)
			}
		}
	}
	if len(cnt) != 0 {
		return []int{}
	}
	return ans
}
