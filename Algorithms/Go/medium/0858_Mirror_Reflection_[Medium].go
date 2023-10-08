/*
858. Mirror Reflection


There is a special square room with mirrors on each of the four walls. Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.

The square room has walls of length p and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.

Given the two integers p and q, return the number of the receptor that the ray meets first.

The test cases are guaranteed so that the ray will meet a receptor eventually.



Example 1:


Input: p = 2, q = 1
Output: 2
Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.


Example 2:

Input: p = 3, q = 1
Output: 1


Constraints:

1 <= q <= p <= 1000
*/

/**
 * 对正方形镜像扩展
 *                       1      2
 *                       ┌───── ↗
 *                       │      │       │
 * x       0      x     0│      │x      │
 *  ┌──────┬──────┬───── ↗ ─────┤       │
 *  │      │      │      │      │  │    │
 * 2│      │      │      │      │  │    │ q2
 *  ├──────┼──────┼──────┼──────┘2 │ q1 │
 * 2│     1│     2│      │1        │    │
 *  │      │      │      │         │    │
 *  ↗ ─────┴──────┴──────┘
 * x       0      x       0
 *
 *   ────────────────────
 *            p1
 *   ───────────────────────────
 *              p2
 *
 *  q 为偶数：接收点必为 0
 *      q 为偶数时，q1 从左向右移动，只有 x 和 0 可以选择。
 *      由于 x 不是接收点，接收点必为 0。
 *  q 为奇数：接收点可以为 1 或 2
 *      q 为奇数时，q2 从左向右移动，可以选择 1 或 2。
 *  p 为偶数：接收点必为 2
 *      p 为偶数时，p2 从下向上移动，只有 x 和 2 可以选择。
 *      由于 x 不是接收点，接收点必为 2。
 *  p 为奇数：接收点可以为 0 或 1
 *      p 为奇数时，p1 从下向上移动，可以选择 0 或 1。
 *
 *  ----------------------------------------------
 *
 *  p 为偶数: 接收点 2
 *  q 为偶数: 接收点 0
 *  p、q 为奇数：接收点 1
 */
func mirrorReflection(p int, q int) int {
	// 对 p、q 均为偶数的情况化简
	for (q&1) == 0 && (p&1) == 0 {
		q >>= 1
		p >>= 1
	}
	if (p & 1) == 0 {
		return 2
	}
	if (q & 1) == 0 {
		return 0
	}
	return 1
}
