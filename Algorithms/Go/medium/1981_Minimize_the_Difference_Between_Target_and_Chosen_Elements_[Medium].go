/*
1981. Minimize the Difference Between Target and Chosen Elements


You are given an m x n integer matrix mat and an integer target.

Choose one integer from each row in the matrix such that the absolute difference between target and the sum of the chosen elements is minimized.

Return the minimum absolute difference.

The absolute difference between two numbers a and b is the absolute value of a - b.



Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
Output: 0
Explanation: One possible choice is to:
- Choose 1 from the first row.
- Choose 5 from the second row.
- Choose 7 from the third row.
The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.

Example 2:


Input: mat = [[1],[2],[3]], target = 100
Output: 94
Explanation: The best possible choice is to:
- Choose 1 from the first row.
- Choose 2 from the second row.
- Choose 3 from the third row.
The sum of the chosen elements is 6, and the absolute difference is 94.

Example 3:


Input: mat = [[1,2,9,8,7]], target = 6
Output: 1
Explanation: The best choice is to choose 7 from the first row.
The absolute difference is 1.


Constraints:

1. m == mat.length
2. n == mat[i].length
3. 1 <= m, n <= 70
4. 1 <= mat[i][j] <= 70
5. 1 <= target <= 800
*/

func minimizeTheDifference(mat [][]int, target int) int {
	dp := make([]bool, min(len(mat)*70, target*2)+1)
	dp[0] = true
	minSum, maxSum := 0, 0
	for _, row := range mat {
		mi, mx := row[0], row[0]
		for _, v := range row[1:] {
			mi = min(mi, v)
			mx = max(mx, v)
		}
		minSum += mi
		maxSum = min(maxSum+mx, target*2)
		for i := maxSum; i >= 0; i-- {
			dp[i] = false
			for _, v := range row {
				if v <= i && dp[i-v] {
					dp[i] = true
					break
				}
			}
		}
	}
	ans := abs(minSum - target)
	for i, v := range dp {
		if v {
			ans = min(ans, abs(i-target))
		}
	}
	return ans
}
