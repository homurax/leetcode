/*
3588. Find Maximum Area of a Triangle


You are given a 2D array coords of size n x 2, representing the coordinates of n points in an infinite Cartesian plane.

Find twice the maximum area of a triangle with its corners at any three elements from coords, such that at least one side of this triangle is parallel to the x-axis or y-axis. Formally, if the maximum area of such a triangle is A, return 2 * A.

If no such triangle exists, return -1.

Note that a triangle cannot have zero area.



Example 1:

Input: coords = [[1,1],[1,2],[3,2],[3,3]]

Output: 2

Explanation:



The triangle shown in the image has a base 1 and height 2. Hence its area is 1/2 * base * height = 1.


Example 2:

Input: coords = [[1,1],[2,2],[3,3]]

Output: -1

Explanation:

The only possible triangle has corners (1, 1), (2, 2), and (3, 3). None of its sides are parallel to the x-axis or the y-axis.



Constraints:

1 <= n == coords.length <= 10^5
1 <= coords[i][0], coords[i][1] <= 10^6
All coords[i] are unique.
*/
// 对于确定的 x，找最长的边（x 的上下） = maxY[x] - minY[x]
// 最大的高（x 边的左右两侧找顶点） = max(maxX - x, x - minX)
func maxArea(coords [][]int) int64 {
	ans := 0
	calc := func() {
		minX, maxX := math.MaxInt, 0
		minY := map[int]int{}
		maxY := map[int]int{}
		for _, p := range coords {
			x, y := p[0], p[1]
			minX = min(minX, x)
			maxX = max(maxX, x)
			maxY[x] = max(maxY[x], y)
			mn, ok := minY[x]
			if !ok {
				minY[x] = y
			} else {
				minY[x] = min(mn, y)
			}
		}

		for x, y := range minY {
			ans = max(ans, (maxY[x]-y)*max(maxX-x, x-minX))
		}
	}
	calc()
	for _, p := range coords {
		p[0], p[1] = p[1], p[0]
	}
	calc()
	if ans == 0 {
		ans = -1
	}
	return int64(ans)
}
