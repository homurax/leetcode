/*
2718. Sum of Matrix After Queries

You are given an integer n and a 0-indexed 2D array queries where queries[i] = [typei, indexi, vali].

Initially, there is a 0-indexed n x n matrix filled with 0's. For each query, you must apply one of the following changes:

if typei == 0, set the values in the row with indexi to vali, overwriting any previous values.
if typei == 1, set the values in the column with indexi to vali, overwriting any previous values.
Return the sum of integers in the matrix after all queries are applied.

Example 1:

Input: n = 3, queries = [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]
Output: 23
Explanation: The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 23.
Example 2:

Input: n = 3, queries = [[0,0,4],[0,1,2],[1,0,1],[0,2,3],[1,2,1]]
Output: 17
Explanation: The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 17.

Constraints:

1 <= n <= 10^4
1 <= queries.length <= 5 * 10^4
queries[i].length == 3
0 <= typei <= 1
0 <= indexi < n
0 <= vali <= 10^5
*/
// 正难则反
// 正向从前向后遍历需要考虑被覆盖时的先减后加
// 反向遍历记录被访问过的行列, 如果已经访问过则直接跳过该行列
// 对于可以访问的行来说, 并不是这一行 n 个位置都可以填值, 如果此时已经有 x 列访问过, 则只可增加 (n - x) * val
// 列同理
func matrixSumQueries(n int, queries [][]int) int64 {
	ans := 0
	visited := [2]map[int]bool{{}, {}}
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		tp, idx, val := q[0], q[1], q[2]
		if !visited[tp][idx] { // 该行/列未访问
			// 考虑之前的列/行已经占用的位置
			ans += (n - len(visited[tp^1])) * val
			visited[tp][idx] = true
		}
	}
	return int64(ans)
}
