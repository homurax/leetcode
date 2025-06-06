/*
2550. Count Collisions of Monkeys on a Polygon

There is a regular convex polygon with n vertices. The vertices are labeled from 0 to n - 1 in a clockwise direction, and each vertex has exactly one monkey. The following figure shows a convex polygon of 6 vertices.

Simultaneously, each monkey moves to a neighboring vertex. A collision happens if at least two monkeys reside on the same vertex after the movement or intersect on an edge.

Return the number of ways the monkeys can move so that at least one collision happens. Since the answer may be very large, return it modulo 109 + 7.

Example 1:

Input: n = 3

Output: 6

Explanation:

There are 8 total possible movements.
Two ways such that they collide at some point are:

Monkey 1 moves in a clockwise direction; monkey 2 moves in an anticlockwise direction; monkey 3 moves in a clockwise direction. Monkeys 1 and 2 collide.
Monkey 1 moves in an anticlockwise direction; monkey 2 moves in an anticlockwise direction; monkey 3 moves in a clockwise direction. Monkeys 1 and 3 collide.

Example 2:

Input: n = 4

Output: 14

Constraints:

3 <= n <= 10^9
*/

// 正难则反
// ans = 用所有的可能 - 不碰撞的方案
// 每个点可以向两个方向移动，即 2^n
// 不碰撞只有 2 种方案，即朝相同方向移动，逆时针、顺时针
// ans = 2^n - 2
// 使用快速幂计算
const mod = 1_000_000_007

func monkeyMove(n int) int {
	return (pow(2, n) - 2 + mod) % mod
}

func pow(x, n int) int {
	rst := 1
	for ; n > 0; n /= 2 {
		if n%2 > 0 {
			rst = rst * x % mod
		}
		x = x * x % mod
	}
	return rst
}
