/*
3265. Count Almost Equal Pairs I

You are given an array nums consisting of positive integers.

We call two integers x and y in this problem almost equal if both integers can become equal after performing the following operation at most once:

Choose either x or y and swap any two digits within the chosen number.
Return the number of indices i and j in nums where i < j such that nums[i] and nums[j] are almost equal.

Note that it is allowed for an integer to have leading zeros after performing an operation.

Example 1:

Input: nums = [3,12,30,17,21]

Output: 2

Explanation:

The almost equal pairs of elements are:

3 and 30. By swapping 3 and 0 in 30, you get 3.
12 and 21. By swapping 1 and 2 in 12, you get 21.

Example 2:

Input: nums = [1,1,1,1,1]

Output: 10

Explanation:

Every two elements in the array are almost equal.

Example 3:

Input: nums = [123,231]

Output: 0

Explanation:

We cannot swap any two digits of 123 or 231 to reach the other.

Constraints:

2 <= nums.length <= 100
1 <= nums[i] <= 10^6
*/
var POW = [...]int{1, 10, 100, 1000, 10000, 100000, 1000000}

func countPairs(nums []int) int {
	ans := 0
	slices.Sort(nums)
	cnt := map[int]int{}
	arr := [7]int{}
	for _, num := range nums {
		set := map[int]struct{}{}
		set[num] = struct{}{}
		n := 0
		for t := num; t > 0; t /= 10 {
			arr[n] = t % 10
			n++
		}
		for i := 0; i < n; i++ {
			for j := i + 1; j < n; j++ {
				set[num+(arr[j]-arr[i])*(POW[i]-POW[j])] = struct{}{}
			}
		}
		for x, _ := range set {
			ans += cnt[x]
		}
		cnt[num]++
	}
	return ans
}
