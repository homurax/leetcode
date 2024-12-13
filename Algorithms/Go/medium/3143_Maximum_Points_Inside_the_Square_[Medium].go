/*
3143. Maximum Points Inside the Square

You are given a 2D array points and a string s where, points[i] represents the coordinates of point i, and s[i] represents the tag of point i.

A valid square is a square centered at the origin (0, 0), has edges parallel to the axes, and does not contain two points with the same tag.

Return the maximum number of points contained in a valid square.

Note:

A point is considered to be inside the square if it lies on or within the square's boundaries.
The side length of the square can be zero.

Example 1:

Input: points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"

Output: 2

Explanation:

The square of side length 4 covers two points points[0] and points[1].

Example 2:

Input: points = [[1,1],[-2,-2],[-2,2]], s = "abb"

Output: 1

Explanation:

The square of side length 2 covers one point, which is points[0].

Example 3:

Input: points = [[1,1],[-1,-1],[2,-2]], s = "ccd"

Output: 0

Explanation:

It's impossible to make any valid squares centered at the origin such that it covers only one point among points[0] and points[1].

Constraints:

1 <= s.length, points.length <= 10^5
points[i].length == 2
-10^9 <= points[i][0], points[i][1] <= 10^9
s.length == points.length
points consists of distinct coordinates.
s consists only of lowercase English letters.
*/

// 考虑次小距离的最小值
// 对于 (x1, y1) (x2, y2), 切比雪夫距离 = max(|x1 - x2|, |y1 - y2|)
// 正方形中心为原点 (0, 0), 所以 points[i] 到中心的 切比雪夫距离 = max(|xi|, |yi|)
// 假设对于标签 a, 切比雪夫距离最小的点距离为 minD_a1, 次小点的距离为 minD_a2, 则正方形的半边长一定 < minD_a2, 否则正方形内存在相同标签的点
// 推广到所有标签, 即正方形的半边长一定 < min(minD_tag2)
func maxPointsInsideSquare(points [][]int, s string) int {
	// 最小切比雪夫距离
	minD := [26]int{}
	for i := range minD {
		minD[i] = math.MaxInt
	}
	// 所有标签次小切比雪夫距离的最小值
	min2 := math.MaxInt
	for i, p := range points {
		tag := s[i] - 'a'
		d := max(abs(p[0]), abs(p[1]))
		if d < minD[tag] {
			// 当前 d 是 tag 中最小的
			// 则 minD[tag] 当作是是次小的
			min2 = min(min2, minD[tag])
			minD[tag] = d
		} else {
			// d 当作是次小的
			min2 = min(min2, d)
		}
	}
	ans := 0
	for _, d := range minD {
		if d < min2 {
			ans++
		}
	}
	return ans
}
