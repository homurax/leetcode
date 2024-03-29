/*
1711. Count Good Meals


A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.

You can pick any two different foods to make a good meal.

Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the ith item of food, return the number of different good meals you can make from this list modulo 109 + 7.

Note that items with different indices are considered different even if they have the same deliciousness value.



Example 1:

Input: deliciousness = [1,3,5,7,9]
Output: 4
Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.


Example 2:

Input: deliciousness = [1,1,1,3,3,3,7]
Output: 15
Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.


Constraints:

1. 1 <= deliciousness.length <= 10^5
2. 0 <= deliciousness[i] <= 2^20
*/

func countPairs1(deliciousness []int) int {
	ans, m := 0, 1<<22
	count := make(map[int]int)
	for _, i := range deliciousness {
		count[i]++
	}
	for x := range count {
		for i := 1; i < m; i <<= 1 {
			y := i - x
			if _, ok := count[y]; ok {
				if x == y {
					ans += count[x] * (count[x] - 1)
				} else {
					ans += count[x] * count[y]
				}
			}
		}
	}
	ans >>= 1
	return ans % 1_000_000_007
}
