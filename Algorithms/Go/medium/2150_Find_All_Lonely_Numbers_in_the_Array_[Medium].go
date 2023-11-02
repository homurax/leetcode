/*
2150. Find All Lonely Numbers in the Array


You are given an integer array nums. A number x is lonely when it appears only once, and no adjacent numbers (i.e. x + 1 and x - 1) appear in the array.

Return all lonely numbers in nums. You may return the answer in any order.



Example 1:

Input: nums = [10,6,5,8]
Output: [10,8]
Explanation:
- 10 is a lonely number since it appears exactly once and 9 and 11 does not appear in nums.
- 8 is a lonely number since it appears exactly once and 7 and 9 does not appear in nums.
- 5 is not a lonely number since 6 appears in nums and vice versa.
Hence, the lonely numbers in nums are [10, 8].
Note that [8, 10] may also be returned.


Example 2:

Input: nums = [1,3,5,3]
Output: [1,5]
Explanation:
- 1 is a lonely number since it appears exactly once and 0 and 2 does not appear in nums.
- 5 is a lonely number since it appears exactly once and 4 and 6 does not appear in nums.
- 3 is not a lonely number since it appears twice.
Hence, the lonely numbers in nums are [1, 5].
Note that [5, 1] may also be returned.


Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^6
*/

func findLonely(nums []int) []int {
	var ans []int
	sort.Ints(nums)
	n := len(nums)
	for i, v := range nums {
		if i+1 < n && v == nums[i+1] {
			continue
		}
		if i+1 < n && v+1 == nums[i+1] {
			continue
		}
		if i > 0 && (nums[i-1] == v || nums[i-1]+1 == v) {
			continue
		}
		ans = append(ans, v)
	}
	return ans
}
