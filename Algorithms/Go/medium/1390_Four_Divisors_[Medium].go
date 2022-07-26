/*
1390. Four Divisors


Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.


Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.


Example 2:

Input: nums = [21,21]
Output: 64


Example 3:

Input: nums = [1,2,3,4,5]
Output: 0


Constraints:

1. 1 <= nums.length <= 10^4
2. 1 <= nums[i] <= 10^5
*/

func sumFourDivisors(nums []int) int {
	ans := 0
	cache := make([]int, 100001)
	for _, num := range nums {
		if cache[num] > 0 {
			ans += cache[num]
			continue
		}
		count, sum := 0, 0
		for i := 1; i*i <= num; i++ {
			if num%i != 0 {
				continue
			}
			count++
			sum += i
			if i*i != num {
				count++
				sum += num / i
			}
			if count > 4 {
				break
			}
		}
		if count == 4 {
			cache[num] = sum
			ans += sum
		}
	}
	return ans
}
