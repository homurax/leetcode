/*
907. Sum of Subarray Minimums


Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.



Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.

Example 2:

Input: arr = [11,81,94,43,3]
Output: 444


Constraints:

1. 1 <= arr.length <= 3 * 10^4
2. 1 <= arr[i] <= 3 * 10^4
*/

func sumSubarrayMins(arr []int) int {
	ans := 0
	arr = append(arr, -1)
	stack := []int{-1} // 哨兵
	for r, num := range arr {
		for len(stack) > 1 && arr[stack[len(stack)-1]] >= num {
			i := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			ans += arr[i] * (i - stack[len(stack)-1]) * (r - i)
		}
		stack = append(stack, r)
	}
	return ans % (1e9 + 7)
}
