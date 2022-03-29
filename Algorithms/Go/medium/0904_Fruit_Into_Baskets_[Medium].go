/*
904. Fruit Into Baskets


You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.



Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.

Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


Constraints:

1. 1 <= fruits.length <= 10^5
2. 0 <= fruits[i] < fruits.length
*/

func totalFruit(fruits []int) int {
	ans, n := 0, len(fruits)
	l, r := 0, -1
	for i := 1; i < n; i++ {
		if fruits[i] != fruits[i-1] {
			if r >= 0 && fruits[i] != fruits[r] { // 出现第三种type
				if i-l > ans {
					ans = i - l
				}
				l = r + 1
			}
			r = i - 1
		}
	}
	if n-l > ans {
		ans = n - l
	}
	return ans
}

func totalFruit1(fruits []int) int {
	ans, l, n := 0, 0, len(fruits)
	freq := make(map[int]int)
	for r := 0; r < n; r++ {
		freq[fruits[r]]++
		for len(freq) > 2 {
			freq[fruits[l]]--
			if freq[fruits[l]] == 0 {
				delete(freq, fruits[l])
			}
			l++
		}
		if r-l+1 > ans {
			ans = r - l + 1
		}
	}
	return ans
}
