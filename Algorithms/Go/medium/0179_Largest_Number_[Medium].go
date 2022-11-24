/*
179. Largest Number


Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.



Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"


Constraints:

1. 1 <= nums.length <= 100
2. 0 <= nums[i] <= 10^9
*/

func largestNumber(nums []int) string {
	n := len(nums)
	ss := make([]string, n)
	for i, v := range nums {
		ss[i] = strconv.Itoa(v)
	}
	sort.Slice(ss, func(i, j int) bool {
		return ss[i]+ss[j] > ss[j]+ss[i]
	})
	var sb strings.Builder
	for _, v := range ss {
		sb.WriteString(v)
	}
	l := sb.Len()
	ans := sb.String()
	k := 0
	for k < l-1 && ans[k] == '0' {
		k++
	}
	return ans[k:]
}
