/*
1975. Maximum Matrix Sum


You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.



Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.


Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.


Constraints:

1. n == matrix.length == matrix[i].length
2. 2 <= n <= 250
3. -10^5 <= matrix[i][j] <= 10^5
*/

func maxMatrixSum(matrix [][]int) int64 {
	var sum int64
	min, count := 100000, 0
	for _, row := range matrix {
		for _, num := range row {
			if num < 0 {
				count++
				num = -num
			}
			if num < min {
				min = num
			}
			sum += int64(num)
		}
	}
	if count%2 == 1 {
		sum -= int64(min * 2)
	}
	return sum
}
