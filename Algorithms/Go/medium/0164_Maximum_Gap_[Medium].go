/*
164. Maximum Gap

Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:

Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

Example 2:

Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
*/

// 基数排序
// 分为十个桶（0 ~ 9），所有数字按照最小位（个位数）依次排序归入，将所有桶中数据串联
// 所有数字按照十位数依次排序归入，将所有桶中数据串联
// 知道处理完所有数字中的最高位，将所有桶中数据串联即为排序结果
func maximumGap(nums []int) int {
	n := len(nums)
	if n < 2 {
		return 0
	}
	temp := make([]int, n)
	divisor, maxVal := 1, 0
	for _, num := range nums {
		if num > maxVal {
			maxVal = num
		}
	}
	for maxVal >= divisor {
		buckets := make([]int, 10)
		for _, num := range nums {
			buckets[(num/divisor)%10]++
		}
		for i := 1; i < 10; i++ { // 累加较小的余数代表当前余数对应本轮排序后的位置
			buckets[i] += buckets[i-1]
		}
		for i := n - 1; i >= 0; i-- { // 桶中同样余数的位置是从大变小的 所以 nums 倒序遍历
			digit := (nums[i] / divisor) % 10
			temp[buckets[digit]-1] = nums[i]
			buckets[digit]--
		}
		copy(nums, temp)
		divisor *= 10
	}
	ans := 0
	for i := 1; i < n; i++ {
		ans = max(ans, nums[i]-nums[i-1])
	}
	return ans
}
